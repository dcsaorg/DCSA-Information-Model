-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Implementation specific SQL for the reference implementation.
BEGIN;
-- Aggregated table containing all events
DROP VIEW IF EXISTS dcsa_im_v3_0.aggregated_events CASCADE;
CREATE VIEW dcsa_im_v3_0.aggregated_events AS
 SELECT transport_event.event_id,
    'TRANSPORT' AS event_type,
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
    NULL::text AS document_id,
    NULL::text AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::text as event_location,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::text as vessel_position,
    NULL::text as publisher,
    (
        SELECT DISTINCT s.carrier_booking_reference
        FROM dcsa_im_v3_0.shipment s
        JOIN dcsa_im_v3_0.shipment_transport st ON s.id = st.shipment_id
        JOIN dcsa_im_v3_0.transport t ON st.transport_id = t.id
        WHERE t.discharge_transport_call_id = transport_call_id
    ) AS carrier_booking_reference
   FROM dcsa_im_v3_0.transport_event
UNION
 SELECT shipment_event.event_id,
    'SHIPMENT' AS event_type,
    shipment_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    shipment_event.shipment_event_type_code,
    shipment_event.document_type_code,
    NULL::text AS equipment_event_type_code,
    shipment_event.event_date_time,
    shipment_event.event_created_date_time,
    NULL::text AS transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    shipment_event.document_id AS document_id,
    shipment_event.reason AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::text as event_location,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::text as vessel_position,
    NULL::text as publisher,
    (
        CASE shipment_event.document_type_code
            WHEN 'BKG'
            THEN  document_id
            WHEN 'TRD'
            THEN (SELECT DISTINCT s.carrier_booking_reference
                  FROM dcsa_im_v3_0.transport_document td
                  JOIN dcsa_im_v3_0.cargo_item ci ON td.shipping_instruction_id = ci.shipping_instruction_id
                  JOIN dcsa_im_v3_0.shipment s ON ci.shipment_id = s.id
                  WHERE td.transport_document_reference = document_id)
            WHEN 'SHI'
            THEN (SELECT DISTINCT s.carrier_booking_reference
                  FROM dcsa_im_v3_0.shipment s
                  JOIN dcsa_im_v3_0.cargo_item ci ON s.id = ci.shipment_id
                  WHERE ci.shipping_instruction_id = document_id)
        END
     ) AS carrier_booking_reference
   FROM dcsa_im_v3_0.shipment_event
UNION
 SELECT equipment_event.event_id,
    'EQUIPMENT' AS event_type,
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
    NULL::text AS document_id,
    NULL::text AS reason,
    NULL::text as operations_event_type_code,
    NULL::text as publisher_role,
    NULL::text as event_location,
    NULL::text as port_call_phase_type_code,
    NULL::text as port_call_service_type_code,
    NULL::text as facility_type_code,
    NULL::text as vessel_position,
    NULL::text as publisher,
    (
        SELECT DISTINCT s.carrier_booking_reference
        FROM dcsa_im_v3_0.shipment s
        JOIN dcsa_im_v3_0.shipment_transport st ON s.id = st.shipment_id
        JOIN dcsa_im_v3_0.transport t ON st.transport_id = t.id
        WHERE t.discharge_transport_call_id = transport_call_id
    ) AS carrier_booking_reference
   FROM dcsa_im_v3_0.equipment_event
UNION
 SELECT operations_event.event_id,
    'OPERATIONS' AS event_type,
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
    NULL::text AS document_id,
    NULL::text AS reason,
    operations_event.operations_event_type_code,
    operations_event.publisher_role,
    operations_event.event_location,
    operations_event.port_call_phase_type_code,
    operations_event.port_call_service_type_code,
    operations_event.facility_type_code,
    operations_event.vessel_position,
    operations_event.publisher,
    (
        SELECT DISTINCT s.carrier_booking_reference
        FROM dcsa_im_v3_0.shipment s
        JOIN dcsa_im_v3_0.shipment_transport st ON s.id = st.shipment_id
        JOIN dcsa_im_v3_0.transport t ON st.transport_id = t.id
        WHERE t.discharge_transport_call_id = transport_call_id
    ) AS carrier_booking_reference
   FROM dcsa_im_v3_0.operations_event;

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
     subscription_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
     callback_url text NOT NULL,
     carrier_booking_reference varchar(35),
     transport_document_id varchar(20),
     transport_document_type text,
     equipment_reference varchar(15),
     transport_call_id varchar(100) NULL,
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

-- Indexes to help the reference implementation deduplicate these. Fields are ordered by how discriminatory they are
-- presumed to be in the general case or how fast they would like to check.
CREATE UNIQUE INDEX deduplicate_location
    ON dcsa_im_v3_0.location (address_id, facility_id, un_location_code, location_name, latitude, longitude);

CREATE UNIQUE INDEX deduplicate_address
    ON dcsa_im_v3_0.address (postal_code, name, country, state_region, city, street, street_number, floor);


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
    subscription_url varchar(100) NULL,

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


-- Only used by UI support to assist the UI
DROP TABLE IF EXISTS dcsa_im_v3_0.port_timezone CASCADE;
CREATE TABLE dcsa_im_v3_0.port_timezone (
    un_location_code char(5) PRIMARY KEY REFERENCES dcsa_im_v3_0.un_location (un_location_code),
    iana_timezone text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.negotiation_cycle CASCADE;
CREATE TABLE dcsa_im_v3_0.negotiation_cycle (
     cycle_key text PRIMARY KEY,
     cycle_name text NOT NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.timestamp_definition CASCADE;
CREATE TABLE dcsa_im_v3_0.timestamp_definition (
    id text PRIMARY KEY,
    timestamp_type_name text NOT NULL UNIQUE,
    publisher_role varchar(3) NOT NULL,  -- TODO: Reference publisher role table
    primary_receiver varchar(3) NOT NULL,  -- TODO: Reference publisher role table
    event_classifier_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.event_classifier(event_classifier_code),
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type(operations_event_type_code),
    port_call_phase_type_code varchar(4) NULL, -- TODO, REFERENCES dcsa_im_v3_0.port_call_phase_type(port_call_phase_type_code),
    port_call_service_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_service_type(port_call_service_type_code),
    facility_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.facility_type(facility_type_code),
    is_berth_location_needed boolean NOT NULL,
    is_pbp_location_needed boolean NOT NULL,
    is_terminal_needed boolean NOT NULL,
    is_vessel_position_needed boolean NOT NULL,
    negotiation_cycle text NOT NULL REFERENCES dcsa_im_v3_0.negotiation_cycle(cycle_key),
    provided_in_standard text NOT NULL,
    accept_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(id) INITIALLY DEFERRED,
    reject_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(id) INITIALLY DEFERRED
);

COMMIT;
