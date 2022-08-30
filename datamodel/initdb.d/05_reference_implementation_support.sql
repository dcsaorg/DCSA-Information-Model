-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Implementation specific SQL for the reference implementation.
BEGIN;

-- DDT-1058
ALTER TABLE dcsa_im_v3_0.shipment_event ADD document_reference varchar(100) NOT NULL;


-- Aggregated table containing all events
DROP VIEW IF EXISTS dcsa_im_v3_0.aggregated_events CASCADE;
CREATE VIEW dcsa_im_v3_0.aggregated_events AS
 SELECT transport_event.event_id,
    'TRANSPORT' AS event_type,
    'TC_ID' AS link_type,
    transport_event.event_classifier_code,
    transport_event.transport_event_type_code,
    NULL::text AS shipment_event_type_code,
    NULL::text AS document_type_code,
    NULL::text AS equipment_event_type_code,
    transport_event.event_date_time,
    transport_event.event_created_date_time,
    transport_event.transport_call_id,
    transport_event.delay_reason_code,
    transport_event.change_remark,
    NULL::text AS remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    NULL::uuid AS document_id,
    NULL::text AS document_reference,
    NULL::text AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::uuid as event_location_id,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::uuid as vessel_position_id,
    NULL::uuid as publisher_id
   FROM dcsa_im_v3_0.transport_event
UNION ALL
 SELECT shipment_event.event_id,
    'SHIPMENT' AS event_type,
    shipment_event.document_type_code AS link_type,
    shipment_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    shipment_event.shipment_event_type_code,
    shipment_event.document_type_code,
    NULL::text AS equipment_event_type_code,
    shipment_event.event_date_time,
    shipment_event.event_created_date_time,
    NULL::uuid AS transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    shipment_event.document_id AS document_id,
    shipment_event.document_reference AS document_reference,
    shipment_event.reason AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::uuid as event_location_id,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::uuid as vessel_position_id,
    NULL::uuid as publisher_id
   FROM dcsa_im_v3_0.shipment_event
UNION ALL
 SELECT equipment_event.event_id,
    'EQUIPMENT' AS event_type,
    'TC_ID' AS link_type,
    equipment_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    NULL::text AS shipment_event_type_code,
    NULL::text AS document_type_code,
    equipment_event.equipment_event_type_code,
    equipment_event.event_date_time,
    equipment_event.event_created_date_time,
    equipment_event.transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS remark,
    equipment_event.equipment_reference,
    equipment_event.empty_indicator_code,
    NULL::uuid AS document_id,
    NULL::text AS document_reference,
    NULL::text AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::uuid as event_location_id,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::uuid as vessel_position_id,
    NULL::uuid as publisher_id
   FROM dcsa_im_v3_0.equipment_event
UNION ALL
 SELECT operations_event.event_id,
    'OPERATIONS' AS event_type,
    'TC_ID' AS link_type,
    operations_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    NULL::text AS shipment_event_type_code,
    NULL::text AS document_type_code,
    NULL::text AS equipment_event_type_code,
    operations_event.event_date_time,
    operations_event.event_created_date_time,
    operations_event.transport_call_id,
    operations_event.delay_reason_code,
    NULL::text AS change_remark,
    operations_event.remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    NULL::uuid AS document_id,
    NULL::text AS document_reference,
    NULL::text AS reason,
    operations_event.operations_event_type_code,
    operations_event.publisher_role,
    operations_event.event_location_id,
    operations_event.port_call_phase_type_code,
    operations_event.port_call_service_type_code,
    operations_event.facility_type_code,
    operations_event.vessel_position_id,
    operations_event.publisher_id
   FROM dcsa_im_v3_0.operations_event;

/* View to assist with the GET /events endpoint.  It provide the following information:
 *
 * It provides a link_type and a document_id (for ShipmentEvent) or transport_call_id (other events).
 * These can be used for JOIN'ing between aggregated_events using something like:
 *     FROM aggregated_events ae
 *     JOIN event_document_reference edr ON (ae.link_type = edr.link_type AND (
 *                   ae.transport_call_id = edr.transport_call_id
 *                OR ae.document_id = edr.document_id
 *     )
 *
 * Additionally, this view provides the following columns:
 *   * document_reference_type (enum value to be used in the documentReferences payload)
 *   * document_reference_value (the actual reference to the document to be used in the documentReferences payload)
 *   * carrier_booking_request_reference (used for query parameters)
 *   * carrier_booking_reference (used for query parameters)
 *   * transport_document_reference (used for query parameters)
 *
 * The query parameter based columns are technical redundant with document_reference_value (+ a filter on the relevant
 * type).  However, they are easier to use / reason about in case multiple query parameters are used.  As an example,
 * given the query parameters:
 *     carrierBookingReference=X&transportDocumentReference=Y
 * When we have them as separate columns, this can trivially be translated into:
 *     "WHERE carrier_booking_reference = 'X' AND transport_document_reference = 'Y'"
 *
 * However, the equivalent query using document_reference_value + document_reference_type would be considerably more
 * complex to write and even harder to convince people that it was correct.
 *
 */
DROP VIEW IF EXISTS dcsa_im_v3_0.event_document_reference CASCADE;
CREATE VIEW dcsa_im_v3_0.event_document_reference AS (
            -- For Transport Call based events
            SELECT DISTINCT tc.id AS transport_call_id,
                            null::uuid AS document_id,
                            'TC_ID' AS link_type,
                            'CBR' AS document_reference_type,
                            b.carrier_booking_request_reference AS document_reference_value,
                            b.carrier_booking_request_reference AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.booking b
            JOIN dcsa_im_v3_0.shipment s ON s.booking_id = b.id
            JOIN dcsa_im_v3_0.shipment_transport st ON st.shipment_id = s.id
            JOIN (SELECT DISTINCT tc.id, t.id AS transport_id
                         FROM dcsa_im_v3_0.transport_call tc
                         JOIN dcsa_im_v3_0.transport t ON t.load_transport_call_id = tc.id
                              OR t.discharge_transport_call_id = tc.id
                         ) tc ON tc.transport_id = st.transport_id
        UNION ALL
            SELECT DISTINCT tc.id AS transport_call_id,
                            null::uuid AS document_id,
                            'TC_ID' AS link_type,
                            'BKG' AS document_reference_type,
                            s.carrier_booking_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            s.carrier_booking_reference AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipment s
            JOIN dcsa_im_v3_0.shipment_transport st ON st.shipment_id = s.id
            JOIN (SELECT DISTINCT tc.id, t.id AS transport_id
                  FROM dcsa_im_v3_0.transport_call tc
                  JOIN dcsa_im_v3_0.transport t ON t.load_transport_call_id = tc.id
                       OR t.discharge_transport_call_id = tc.id
            ) tc ON tc.transport_id = st.transport_id
        UNION ALL
            SELECT DISTINCT tc.id AS transport_call_id,
                            null::uuid AS document_id,
                            'TC_ID' AS link_type,
                            'SHI' AS document_reference_type,
                            si.shipping_instruction_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipping_instruction si
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = si.id
            JOIN dcsa_im_v3_0.shipment_transport st ON st.shipment_id = ci.shipment_id
            JOIN (SELECT DISTINCT tc.id, t.id AS transport_id
                  FROM dcsa_im_v3_0.transport_call tc
                  JOIN dcsa_im_v3_0.transport t ON t.load_transport_call_id = tc.id
                       OR t.discharge_transport_call_id = tc.id
            ) tc ON tc.transport_id = st.transport_id
        UNION ALL
            SELECT DISTINCT tc.id AS transport_call_id,
                            null::uuid AS document_id,
                            'TC_ID' AS link_type,
                            'TRD' AS document_reference_type,
                            td.transport_document_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            td.transport_document_reference AS transport_document_reference
            FROM dcsa_im_v3_0.transport_document td
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = td.shipping_instruction_id
            JOIN dcsa_im_v3_0.shipment_transport st ON st.shipment_id = ci.shipment_id
            JOIN (SELECT DISTINCT tc.id, t.id AS transport_id
                  FROM dcsa_im_v3_0.transport_call tc
                  JOIN dcsa_im_v3_0.transport t ON t.load_transport_call_id = tc.id
                       OR t.discharge_transport_call_id = tc.id
            ) tc ON tc.transport_id = st.transport_id
    ) UNION ALL (
            -- For CBR related ShipmentEvents
            -- DISTINCT by definition
            SELECT          NULL::uuid as transport_call_id,
                            b.id AS document_id,
                            'CBR' AS link_type,
                            'CBR' AS document_reference_type,
                            b.carrier_booking_request_reference AS document_reference_value,
                            b.carrier_booking_request_reference AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.booking b
        UNION ALL
            -- DISTINCT. It is a 1:N relation but all the shipments will have unique CBRs
            SELECT          NULL::uuid as transport_call_id,
                            b.id AS document_id,
                            'CBR' AS link_type,
                            'BKG' AS document_reference_type,
                            s.carrier_booking_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            s.carrier_booking_reference AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.booking b
            JOIN dcsa_im_v3_0.shipment s ON b.id = s.booking_id
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            b.id AS document_id,
                            'CBR' AS link_type,
                            'SHI' AS document_reference_type,
                            si.shipping_instruction_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.booking b
            JOIN dcsa_im_v3_0.shipment s ON b.id = s.booking_id
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipment_id = s.id
            JOIN dcsa_im_v3_0.shipping_instruction si ON si.id = ci.shipping_instruction_id
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            b.id AS document_id,
                            'CBR' AS link_type,
                            'TRD' AS document_reference_type,
                            td.transport_document_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            td.transport_document_reference AS transport_document_reference
            FROM dcsa_im_v3_0.booking b
            JOIN dcsa_im_v3_0.shipment s ON b.id = s.booking_id
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipment_id = s.id
            JOIN dcsa_im_v3_0.transport_document td ON td.shipping_instruction_id = ci.shipping_instruction_id
    ) UNION ALL (
            -- For BKG related ShipmentEvents
            -- DISTINCT - all the shipments are associated with exactly on booking.
            SELECT          NULL::uuid as transport_call_id,
                            s.id AS document_id,
                            'BKG' AS link_type,
                            'CBR' AS document_reference_type,
                            b.carrier_booking_request_reference AS document_reference_value,
                            b.carrier_booking_request_reference AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipment s
            JOIN dcsa_im_v3_0.booking b ON s.booking_id = b.id
        UNION ALL
            -- DISTINCT by definition
            SELECT          NULL::uuid as transport_call_id,
                            s.id AS document_id,
                            'BKG' AS link_type,
                            'BKG' AS document_reference_type,
                            s.carrier_booking_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            s.carrier_booking_reference AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipment s
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            s.id AS document_id,
                            'BKG' AS link_type,
                            'SHI' AS document_reference_type,
                            si.shipping_instruction_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipment s
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipment_id = s.id
            JOIN dcsa_im_v3_0.shipping_instruction si ON si.id = ci.shipping_instruction_id
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            s.id AS document_id,
                            'BKG' AS link_type,
                            'TRD' AS document_reference_type,
                            td.transport_document_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            td.transport_document_reference AS transport_document_reference
            FROM dcsa_im_v3_0.shipment s
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipment_id = s.id
            JOIN dcsa_im_v3_0.transport_document td ON td.shipping_instruction_id = ci.shipping_instruction_id
    ) UNION ALL (
            -- For SHI related ShipmentEvents
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            si.id AS document_id,
                            'SHI' AS link_type,
                            'CBR' AS document_reference_type,
                            b.carrier_booking_request_reference AS document_reference_value,
                            b.carrier_booking_request_reference AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipping_instruction si
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = si.id
            JOIN dcsa_im_v3_0.shipment s ON s.id = ci.shipment_id
            JOIN dcsa_im_v3_0.booking b ON s.booking_id = b.id
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            si.id AS document_id,
                            'SHI' AS link_type,
                            'BKG' AS document_reference_type,
                            s.carrier_booking_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            s.carrier_booking_reference AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipping_instruction si
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = si.id
            JOIN dcsa_im_v3_0.shipment s ON s.id = ci.shipment_id
        UNION ALL
            -- DISTINCT by definition
            SELECT          NULL::uuid as transport_call_id,
                            si.id AS document_id,
                            'SHI' AS link_type,
                            'SHI' AS document_reference_type,
                            si.shipping_instruction_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.shipping_instruction si
        UNION ALL
            -- DISTINCT due to 1:1 relation
            SELECT          NULL::uuid as transport_call_id,
                            si.id AS document_id,
                            'SHI' AS link_type,
                            'TRD' AS document_reference_type,
                            td.transport_document_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            td.transport_document_reference AS transport_document_reference
            FROM dcsa_im_v3_0.shipping_instruction si
            JOIN dcsa_im_v3_0.transport_document td ON td.shipping_instruction_id = si.id
    ) UNION ALL (
            -- For TRD related ShipmentEvents
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            td.id AS document_id,
                            'TRD' AS link_type,
                            'CBR' AS document_reference_type,
                            b.carrier_booking_request_reference AS document_reference_value,
                            b.carrier_booking_request_reference AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.transport_document td
            JOIN dcsa_im_v3_0.shipping_instruction si ON si.id = td.shipping_instruction_id
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = si.id
            JOIN dcsa_im_v3_0.shipment s ON s.id = ci.shipment_id
            JOIN dcsa_im_v3_0.booking b ON s.booking_id = b.id
        UNION ALL
            SELECT DISTINCT NULL::uuid as transport_call_id,
                            td.id AS document_id,
                            'TRD' AS link_type,
                            'BKG' AS document_reference_type,
                            s.carrier_booking_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            s.carrier_booking_reference AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.transport_document td
            JOIN dcsa_im_v3_0.shipping_instruction si ON si.id = td.shipping_instruction_id
            JOIN dcsa_im_v3_0.consignment_item ci ON ci.shipping_instruction_id = si.id
            JOIN dcsa_im_v3_0.shipment s ON s.id = ci.shipment_id
        UNION ALL
            -- DISTINCT due to 1:1 relation
            SELECT          NULL::uuid as transport_call_id,
                            td.id AS document_id,
                            'TRD' AS link_type,
                            'SHI' AS document_reference_type,
                            si.shipping_instruction_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            NULL::text AS transport_document_reference
            FROM dcsa_im_v3_0.transport_document td
            JOIN dcsa_im_v3_0.shipping_instruction si ON si.id = td.shipping_instruction_id
        UNION ALL
            -- DISTINCT by definition
            SELECT          NULL::uuid as transport_call_id,
                            td.id AS document_id,
                            'TRD' AS link_type,
                            'TRD' AS document_reference_type,
                            td.transport_document_reference AS document_reference_value,
                            NULL::text AS carrier_booking_request_reference,
                            NULL::text AS carrier_booking_reference,
                            td.transport_document_reference AS transport_document_reference
            FROM dcsa_im_v3_0.transport_document td
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
     subscription_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
     callback_url text NOT NULL,
     carrier_booking_reference varchar(35),
     transport_document_id varchar(20),
     transport_document_type text,
     equipment_reference varchar(15),
     transport_call_reference varchar(100) NULL,
     signature_method varchar(20) NOT NULL,
     secret bytea NOT NULL,
     transport_document_reference text NULL,
     carrier_service_code varchar(5) NULL,
     carrier_voyage_number varchar(50) NULL,
     vessel_imo_number varchar(7) NULL,
    -- Retry state
     retry_after timestamp with time zone NULL,
     retry_count int DEFAULT 0 NOT NULL,
     last_bundle_size int NULL,
     accumulated_retry_delay bigint NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_event_types CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_event_types (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    event_type text,

    PRIMARY KEY (subscription_id, event_type)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_transport_document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_transport_document_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    transport_document_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_document_type (transport_document_type_code),
    PRIMARY KEY (subscription_id, transport_document_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_shipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_shipment_event_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    shipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type (shipment_event_type_code),
    PRIMARY KEY (subscription_id, shipment_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_transport_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_transport_event_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    transport_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_event_type (transport_event_type_code),
    PRIMARY KEY (subscription_id, transport_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_equipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_equipment_event_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    equipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.equipment_event_type (equipment_event_type_code),
    PRIMARY KEY (subscription_id, equipment_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_operations_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_operations_event_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type (operations_event_type_code),
    PRIMARY KEY (subscription_id, operations_event_type_code)
);


DROP TABLE IF EXISTS dcsa_im_v3_0.unmapped_event_queue CASCADE;
CREATE TABLE dcsa_im_v3_0.unmapped_event_queue (
    event_id uuid PRIMARY KEY,
    enqueued_at_date_time timestamp with time zone NOT NULL default now()
);

DROP TABLE IF EXISTS dcsa_im_v3_0.pending_event_queue CASCADE;
CREATE TABLE dcsa_im_v3_0.pending_event_queue (
    delivery_id uuid PRIMARY KEY default uuid_generate_v4(),
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    event_id uuid NOT NULL,
    -- TODO: Consider moving the payload OR the state to its own table as updating state will duplicate the row
    -- temporarily in the database (which means that the payload will cause bloat as long as it is on the
    -- same row).
    payload TEXT NOT NULL,
    enqueued_at_date_time timestamp with time zone NOT NULL default now(),
    -- State and status
    last_attempt_date_time timestamp with time zone NULL,
    last_error_message text NULL,
    retry_count int DEFAULT 0 NOT NULL,

    UNIQUE (subscription_id, event_id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.notification_endpoint CASCADE;
CREATE TABLE dcsa_im_v3_0.notification_endpoint (
    endpoint_id uuid PRIMARY KEY default uuid_generate_v4(),
    subscription_id varchar(100) NULL, -- NO Foreign key (the IDs are external)
    secret bytea NOT NULL,
    -- Optional metadata about the endpoint useful for knowing what it is used for.
    endpoint_reference varchar(100) NULL,
    -- If true, then the endpoint is managed automatically via the application itself (via configuration)
    -- If false, it is created outside configuration.
    managed_endpoint boolean NOT NULL DEFAULT false,
    subscription_url varchar(500) NULL,

    CHECK (NOT managed_endpoint OR subscription_url IS NOT NULL)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.pending_email_notification CASCADE;
CREATE TABLE dcsa_im_v3_0.pending_email_notification (
    id uuid PRIMARY KEY default uuid_generate_v4(),
    event_id uuid NOT NULL,
    template_name text NOT NULL,
    enqueued_at_date_time timestamp with time zone NOT NULL default now(),
    UNIQUE (event_id, template_name)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.pending_email_notification_dead CASCADE;
CREATE TABLE dcsa_im_v3_0.pending_email_notification_dead (
    id uuid PRIMARY KEY,
    event_id uuid NOT NULL,
    template_name text NOT NULL,
    enqueued_at_date_time timestamp with time zone NOT NULL,
    last_failed_at_date_time timestamp with time zone NOT NULL default now(),
    failure_reason_type varchar(200),
    failure_reason_message text
);


-- Only used by UI support to assist the UI
DROP TABLE IF EXISTS dcsa_im_v3_0.port_timezone CASCADE;
CREATE TABLE dcsa_im_v3_0.port_timezone (
    un_location_code char(5) PRIMARY KEY REFERENCES dcsa_im_v3_0.un_location (un_location_code),
    iana_timezone text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call_jit_port_visit CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call_jit_port_visit (
    port_visit_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    transport_call_id uuid NOT NULL UNIQUE REFERENCES dcsa_im_v3_0.transport_call(id),
    UNIQUE (port_visit_id, transport_call_id)
);

DROP VIEW IF EXISTS dcsa_im_v3_0.jit_port_visit CASCADE;
CREATE VIEW dcsa_im_v3_0.jit_port_visit AS
    SELECT port_visit_id FROM dcsa_im_v3_0.transport_call_jit_port_visit
    WHERE port_visit_id = transport_call_id;

DROP TABLE IF EXISTS dcsa_im_v3_0.negotiation_cycle CASCADE;
CREATE TABLE dcsa_im_v3_0.negotiation_cycle (
     cycle_key text PRIMARY KEY,
     cycle_name text NOT NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.timestamp_definition CASCADE;
CREATE TABLE dcsa_im_v3_0.timestamp_definition (
    timestamp_id text PRIMARY KEY,
    timestamp_type_name text NOT NULL UNIQUE,
    event_classifier_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.event_classifier(event_classifier_code),
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type(operations_event_type_code),
    port_call_phase_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_phase_type(port_call_phase_type_code),
    port_call_service_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_service_type(port_call_service_type_code),
    facility_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.facility_type(facility_type_code),
    port_call_phase varchar(100) NULL,
    event_location_requirement varchar(10) NOT NULL CHECK (event_location_requirement IN ('EXCLUDED', 'OPTIONAL', 'REQUIRED')),
    is_terminal_needed boolean NOT NULL,
    vessel_position_requirement varchar(10) NOT NULL CHECK (event_location_requirement IN ('EXCLUDED', 'OPTIONAL', 'REQUIRED')),
    is_miles_to_destination_relevant boolean NOT NULL,
    provided_in_standard text NOT NULL,
    accept_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(timestamp_id) INITIALLY DEFERRED,
    reject_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(timestamp_id) INITIALLY DEFERRED,
    negotiation_cycle varchar(50) NOT NULL REFERENCES dcsa_im_v3_0.negotiation_cycle(cycle_key) INITIALLY DEFERRED
);

DROP TABLE IF EXISTS dcsa_im_v3_0.publisher_pattern CASCADE;
CREATE TABLE dcsa_im_v3_0.publisher_pattern (
    pattern_id text PRIMARY KEY,
    publisher_role varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code),
    primary_receiver varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.timestamp_definition_publisher_pattern CASCADE;
CREATE TABLE dcsa_im_v3_0.timestamp_definition_publisher_pattern (
    timestamp_id text REFERENCES dcsa_im_v3_0.timestamp_definition (timestamp_id),
    pattern_id text REFERENCES dcsa_im_v3_0.publisher_pattern (pattern_id),
    UNIQUE (timestamp_id, pattern_id)
);

-- Ideally, this would be inlined of the operations_event table as a field.  However, operations_event
-- is an official entity and the timestamp_definition is not.  Lets not "contaminate" official IM
-- with unofficial attributes where we can avoid it.
DROP TABLE IF EXISTS dcsa_im_v3_0.ops_event_timestamp_definition CASCADE;
CREATE TABLE dcsa_im_v3_0.ops_event_timestamp_definition (
    event_id uuid PRIMARY KEY REFERENCES dcsa_im_v3_0.operations_event (event_id),
    timestamp_definition text NOT NULL REFERENCES dcsa_im_v3_0.timestamp_definition (timestamp_id)
);
CREATE INDEX ON dcsa_im_v3_0.ops_event_timestamp_definition (timestamp_definition);

-- Only used by UI support to assist the UI
DROP VIEW IF EXISTS dcsa_im_v3_0.ui_timestamp_info CASCADE;
CREATE OR REPLACE VIEW dcsa_im_v3_0.ui_timestamp_info AS
    SELECT operations_event.event_id,
       (CASE WHEN pending_event.retry_count IS NULL THEN 'DELIVERY_FINISHED'
             WHEN pending_event.retry_count > 0 THEN 'ATTEMPTED_DELIVERY'
             ELSE 'PENDING_DELIVERY'
           END) AS event_delivery_status,
       ops_event_timestamp_definition.timestamp_definition,
       pending_event.enqueued_at_date_time,
       pending_event.last_attempt_date_time,
       pending_event.last_error_message,
       pending_event.retry_count,
       operations_event.transport_call_id
        FROM dcsa_im_v3_0.operations_event
                 LEFT JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (ops_event_timestamp_definition.event_id = operations_event.event_id)
                 LEFT JOIN (SELECT unmapped_event_queue.event_id,
                                   unmapped_event_queue.enqueued_at_date_time,
                                   null AS last_attempt_date_time,
                                   null AS last_error_message,
                                   0 AS retry_count
                            FROM dcsa_im_v3_0.unmapped_event_queue
                            UNION
                            SELECT pending_event_queue.event_id,
                                   pending_event_queue.enqueued_at_date_time,
                                   pending_event_queue.last_attempt_date_time,
                                   pending_event_queue.last_error_message,
                                   pending_event_queue.retry_count
                            FROM dcsa_im_v3_0.pending_event_queue
        ) AS pending_event ON (pending_event.event_id = operations_event.event_id);

CREATE INDEX ON dcsa_im_v3_0.operations_event (event_created_date_time);
CREATE INDEX ON dcsa_im_v3_0.operations_event (transport_call_id);

-- Only used by UI support to assist the UI
DROP VIEW IF EXISTS dcsa_im_v3_0.jit_port_visit_ui_context CASCADE;
CREATE OR REPLACE VIEW dcsa_im_v3_0.jit_port_visit_ui_context AS
    SELECT jit_port_visit.port_visit_id,  -- port call visit
           latest_change.event_created_date_time AS latest_event_created_date_time,
           latest_eta_berth.event_date_time AS eta_berth_date_time,
           latest_atd_berth.event_date_time AS atd_berth_date_time,
           -- We use created for omits because it makes it easier for the UI to tell whether the OMIT is the latest
           -- timestamp (via tc.latest_event_created_date_time == tc.omit_created_date_time).
           -- The event_created_date_time timestamp is also useful for marking anything before that date time as
           -- obsolete.
           latest_omit.event_created_date_time AS omit_created_date_time,
           latest_eta_berth.vessel_draft AS vessel_draft,
           latest_eta_berth.miles_remaining_to_destination AS miles_remaining_to_destination
           FROM dcsa_im_v3_0.jit_port_visit
      LEFT JOIN (SELECT MAX(event_created_date_time) AS event_created_date_time, transport_call_jit_port_visit.port_visit_id
                 FROM dcsa_im_v3_0.operations_event
                 JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                 GROUP BY port_visit_id
           ) AS latest_change ON (jit_port_visit.port_visit_id = latest_change.port_visit_id)
      LEFT JOIN (
               SELECT operations_event.event_date_time, transport_call_jit_port_visit.port_visit_id, operations_event.vessel_draft, operations_event.miles_remaining_to_destination
               FROM dcsa_im_v3_0.operations_event
               JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
               JOIN (
                   SELECT MAX(event_created_date_time) AS event_created_date_time, port_visit_id
                       FROM dcsa_im_v3_0.operations_event
                       JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                       JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                       JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                       WHERE timestamp_definition.timestamp_type_name IN ('ETA-Berth', 'ETA-Berth (<implicit>)')
                       GROUP BY port_visit_id
                   ) AS latest_ts ON (transport_call_jit_port_visit.port_visit_id = latest_ts.port_visit_id AND operations_event.event_created_date_time = latest_ts.event_created_date_time)
                   JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                   JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                   WHERE timestamp_definition.timestamp_type_name IN ('ETA-Berth', 'ETA-Berth (<implicit>)')
          ) AS latest_eta_berth ON (jit_port_visit.port_visit_id = latest_eta_berth.port_visit_id)
      LEFT JOIN (
               SELECT operations_event.event_date_time, transport_call_jit_port_visit.port_visit_id
               FROM dcsa_im_v3_0.operations_event
               JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
               JOIN (
                   SELECT MAX(event_created_date_time) AS event_created_date_time, port_visit_id
                       FROM dcsa_im_v3_0.operations_event
                       JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                       JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                       JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                       WHERE timestamp_definition.timestamp_type_name IN ('ATD-Berth', 'ATD-Berth (<implicit>)')
                       GROUP BY port_visit_id
                   ) AS latest_ts ON (transport_call_jit_port_visit.port_visit_id = latest_ts.port_visit_id AND operations_event.event_created_date_time = latest_ts.event_created_date_time)
                   JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                   JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                   WHERE timestamp_definition.timestamp_type_name IN ('ATD-Berth', 'ATD-Berth (<implicit>)')
          ) AS latest_atd_berth ON (jit_port_visit.port_visit_id = latest_atd_berth.port_visit_id)
      LEFT JOIN (
               SELECT operations_event.event_created_date_time, transport_call_jit_port_visit.port_visit_id
               FROM dcsa_im_v3_0.operations_event
                        JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                        JOIN (
                   SELECT MAX(event_created_date_time) AS event_created_date_time, port_visit_id
                   FROM dcsa_im_v3_0.operations_event
                            JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                            JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                            JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                   WHERE timestamp_definition.timestamp_type_name IN ('Omit Port Call')
                   GROUP BY port_visit_id
               ) AS latest_ts ON (transport_call_jit_port_visit.port_visit_id = latest_ts.port_visit_id AND operations_event.event_created_date_time = latest_ts.event_created_date_time)
                        JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                        JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
               WHERE timestamp_definition.timestamp_type_name IN ('Omit Port Call')
           ) AS latest_omit ON (jit_port_visit.port_visit_id = latest_omit.port_visit_id);


DROP TABLE IF EXISTS dcsa_im_v3_0.ebl_solution_provider_type CASCADE;
CREATE TABLE dcsa_im_v3_0.ebl_solution_provider_type (
    ebl_solution_provider_name varchar(50) NOT NULL,
    ebl_solution_provider_code varchar(5) PRIMARY KEY,
    ebl_solution_provider_url varchar(100) NOT NULL,
    ebl_solution_provider_description varchar(250) NULL
);

\copy dcsa_im_v3_0.ebl_solution_provider_type from '../referencedata.d/eblsolutionproviders.csv' with NULL AS E'\'\'' CSV HEADER

--- DDT-948
ALTER TABLE dcsa_im_v3_0.equipment_event ADD utilized_transport_equipment_id uuid NULL REFERENCES dcsa_im_v3_0.utilized_transport_equipment(id);

ALTER TABLE dcsa_im_v3_0.booking ADD valid_until timestamp with time zone NULL;
CREATE UNIQUE INDEX unq_valid_until_booking_idx ON dcsa_im_v3_0.booking(carrier_booking_request_reference) WHERE valid_until IS NULL;

ALTER TABLE dcsa_im_v3_0.shipment ADD valid_until timestamp with time zone NULL;
CREATE UNIQUE INDEX unq_valid_until_shipment_idx ON dcsa_im_v3_0.shipment(carrier_booking_reference) WHERE valid_until IS NULL;

ALTER TABLE dcsa_im_v3_0.shipping_instruction ADD valid_until timestamp with time zone NULL;
CREATE UNIQUE INDEX unq_valid_until_si_idx ON dcsa_im_v3_0.shipping_instruction(shipping_instruction_reference) WHERE valid_until IS NULL;

ALTER TABLE dcsa_im_v3_0.transport_document ADD valid_until timestamp with time zone NULL;
CREATE UNIQUE INDEX unq_valid_until_td_idx ON dcsa_im_v3_0.transport_document(transport_document_reference) WHERE valid_until IS NULL;


--- DDT-1017
DROP TABLE IF EXISTS dcsa_im_v3_0.service_schedule CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_schedule (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    vessel_id uuid NOT NULL REFERENCES dcsa_im_v3_0.vessel (id),
    service_id uuid NOT NULL REFERENCES dcsa_im_v3_0.service (id),
    created_date_time timestamp with time zone NOT NULL DEFAULT now()
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_schedule_terminal_visits CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_schedule_terminal_visits (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY, -- JPA/Hibernate requires an identifying field
    vessel_schedule_id uuid NOT NULL REFERENCES dcsa_im_v3_0.vessel_schedule (id),
    actual_arrival_event_id uuid NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    planned_arrival_event_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    estimated_arrival_event_id uuid NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    actual_departure_event_id uuid NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    planned_departure_event_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    estimated_departure_event_id uuid NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    port_call_status_event_id uuid NULL REFERENCES dcsa_im_v3_0.transport_event (event_id),
    transport_call_sequence integer NOT NULL,
    created_date_time timestamp with time zone NOT NULL DEFAULT now()
);


-- DDT-1180 - message routing
DROP TABLE IF EXISTS dcsa_im_v3_0.message_routing_rule CASCADE;
CREATE TABLE dcsa_im_v3_0.message_routing_rule (
    id uuid NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    api_url varchar(255) NOT NULL,
    login_type varchar(8) NOT NULL CHECK(login_type IN ('OIDC')),
    login_information jsonb NOT NULL,
    vessel_imo_number varchar(255) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.outbox_message CASCADE;
CREATE TABLE dcsa_im_v3_0.outbox_message (
    id uuid NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    message_routing_rule_id uuid NOT NULL REFERENCES dcsa_im_v3_0.message_routing_rule (id),
    payload jsonb NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.outbox_message (message_routing_rule_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.timestamp_notification_dead CASCADE;
CREATE TABLE dcsa_im_v3_0.timestamp_notification_dead (
    id uuid NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    message_routing_rule_id uuid NOT NULL REFERENCES dcsa_im_v3_0.message_routing_rule (id),
    payload jsonb NOT NULL,
    latest_delivery_attempted_datetime timestamp with time zone NOT NULL DEFAULT now()
);

COMMIT;
