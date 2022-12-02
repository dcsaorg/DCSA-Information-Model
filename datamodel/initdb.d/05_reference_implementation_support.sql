-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Implementation specific SQL for the reference implementation.
BEGIN;

-- DDT-1058
ALTER TABLE dcsa_im_v3_0.shipment_event ADD document_reference varchar(100) NOT NULL;


-- DDT-1221
DROP TABLE IF EXISTS dcsa_im_v3_0.event_cache_queue CASCADE;
CREATE TABLE dcsa_im_v3_0.event_cache_queue (
    event_id uuid NOT NULL PRIMARY KEY,
    event_type varchar(16) NOT NULL CONSTRAINT event_type CHECK (event_type IN ('SHIPMENT','TRANSPORT', 'EQUIPMENT'))
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_cache_queue_dead CASCADE;
CREATE TABLE dcsa_im_v3_0.event_cache_queue_dead (
    event_id uuid NOT NULL PRIMARY KEY,
    event_type varchar(16) NOT NULL CONSTRAINT event_type CHECK (event_type IN ('SHIPMENT','TRANSPORT', 'EQUIPMENT')),
    failure_reason_type varchar(200),
    failure_reason_message text
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_cache CASCADE;
CREATE TABLE dcsa_im_v3_0.event_cache (
    event_id uuid NOT NULL PRIMARY KEY,
    event_type varchar(16) NOT NULL CONSTRAINT event_type CHECK (event_type IN ('SHIPMENT','TRANSPORT', 'EQUIPMENT')),
    content jsonb NOT NULL,
    document_references text,
    event_created_date_time timestamp with time zone NOT NULL,
    event_date_time timestamp with time zone NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.event_cache (event_created_date_time);
CREATE INDEX ON dcsa_im_v3_0.event_cache (event_date_time);

CREATE OR REPLACE FUNCTION dcsa_im_v3_0.queue_shipment_event() RETURNS TRIGGER AS $$
    BEGIN
      INSERT INTO dcsa_im_v3_0.event_cache_queue (event_id, event_type) VALUES(NEW.event_id, 'SHIPMENT');
      RETURN NULL;
    END;
$$ LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS queue_shipment_events ON dcsa_im_v3_0.shipment_event;
CREATE TRIGGER queue_shipment_events AFTER INSERT ON dcsa_im_v3_0.shipment_event
    FOR EACH ROW EXECUTE PROCEDURE dcsa_im_v3_0.queue_shipment_event();

CREATE OR REPLACE FUNCTION dcsa_im_v3_0.queue_transport_event() RETURNS TRIGGER AS $$
    BEGIN
      INSERT INTO dcsa_im_v3_0.event_cache_queue (event_id, event_type) VALUES(NEW.event_id, 'TRANSPORT');
      RETURN NULL;
    END;
$$ LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS queue_transport_events ON dcsa_im_v3_0.transport_event;
CREATE TRIGGER queue_transport_events AFTER INSERT ON dcsa_im_v3_0.transport_event
    FOR EACH ROW EXECUTE PROCEDURE dcsa_im_v3_0.queue_transport_event();

CREATE OR REPLACE FUNCTION dcsa_im_v3_0.queue_equipment_event() RETURNS TRIGGER AS $$
    BEGIN
      INSERT INTO dcsa_im_v3_0.event_cache_queue (event_id, event_type) VALUES(NEW.event_id, 'EQUIPMENT');
      RETURN NULL;
    END;
$$ LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS queue_equipment_events ON dcsa_im_v3_0.equipment_event;
CREATE TRIGGER queue_equipment_events AFTER INSERT ON dcsa_im_v3_0.equipment_event
    FOR EACH ROW EXECUTE PROCEDURE dcsa_im_v3_0.queue_equipment_event();

/* Views to assist with finding references for GET /events endpoint.
 * It provide the following information:
 *
 * It provides a link_type and a document_reference (for ShipmentEvent)
 * or transport_call_id (for TransportCall based Events)
 * and a utilized_transport_equipment_id (for EquipmentEvent)
 *
 * These provided values can be used for to find references for the specific event.
 * Below is an example showing how to find references for a transport_call based event:
 * FROM aggregated_events ae
 * JOIN
 * (SELECT transport_call_id,
 *        reference_value,
 *        reference_type_code
 * FROM event_reference) er ON ae.transport_call_id = er.transport_call_id
 *
 * NOTE: VIEWS ARE MADE SEPARATELY BELOW THAN MERGED AS ONE VIEW
 */

/*
* View to extract references for equipmentEvent
*/
DROP VIEW IF EXISTS dcsa_im_v3_0.equipment_event_reference CASCADE;

CREATE VIEW dcsa_im_v3_0.equipment_event_reference AS
  SELECT DISTINCT  reference.reference_value,
                   reference.reference_type_code,
                   NULL::UUID AS transport_call_id,
                   NULL::UUID AS document_id,
                   ci.utilized_transport_equipment_id AS utilized_transport_equipment_id,
                   'EQ_ID' AS link_type
   FROM dcsa_im_v3_0.cargo_item AS ci
   JOIN dcsa_im_v3_0.consignment_item AS con ON ci.consignment_item_id = con.id
   JOIN dcsa_im_v3_0.shipping_instruction AS si ON con.shipping_instruction_id = si.id
   JOIN dcsa_im_v3_0.shipment AS shipment ON con.shipment_id = shipment.id
   JOIN dcsa_im_v3_0.booking AS booking ON shipment.booking_id = booking.id
   JOIN dcsa_im_v3_0.reference AS reference ON reference.consignment_item_id = con.id
   OR reference.shipment_id = shipment.id
   OR reference.shipping_instruction_id = si.id
   OR reference.booking_id = booking.id;

/*
* View to extract references for for TransportCall based event
*/
DROP VIEW IF EXISTS dcsa_im_v3_0.transport_based_event_reference CASCADE;

CREATE VIEW dcsa_im_v3_0.transport_based_event_reference AS
  SELECT DISTINCT  reference.reference_value,
                   reference.reference_type_code,
                   tc.id AS transport_call_id,
                   NULL::UUID AS document_id,
                   NULL::UUID AS utilized_transport_equipment_id,
                   'TC_ID' AS link_type
   FROM dcsa_im_v3_0.consignment_item AS con
   JOIN dcsa_im_v3_0.shipping_instruction AS si ON con.shipping_instruction_id = si.id
   JOIN dcsa_im_v3_0.shipment AS shipment ON con.shipment_id = shipment.id
   JOIN dcsa_im_v3_0.booking AS booking ON shipment.booking_id = booking.id
   JOIN dcsa_im_v3_0.shipment_transport st ON st.shipment_id = shipment.id
   JOIN
     (SELECT DISTINCT tc.id,
                      t.id AS transport_id
      FROM dcsa_im_v3_0.transport_call tc
      JOIN dcsa_im_v3_0.transport t ON t.load_transport_call_id = tc.id
      OR t.discharge_transport_call_id = tc.id) tc ON tc.transport_id = st.transport_id
   JOIN dcsa_im_v3_0.reference AS reference ON reference.consignment_item_id = con.id
   OR reference.shipment_id = shipment.id
   OR reference.shipping_instruction_id = si.id
   OR reference.booking_id = booking.id;

/*
* View to extract references for for ShipmentEvent
*/
DROP VIEW IF EXISTS dcsa_im_v3_0.shipment_event_reference CASCADE;

CREATE VIEW dcsa_im_v3_0.shipment_event_reference AS
  (-- For CBR document reference ShipmentEvents
 SELECT DISTINCT reference.reference_value,
                 reference.reference_type_code,
                 NULL::UUID AS transport_call_id,
                 b.id AS document_id,
                 NULL::UUID AS utilized_transport_equipment_id,
                 'CBR' AS link_type
   FROM dcsa_im_v3_0.booking b
   JOIN dcsa_im_v3_0.shipment s ON b.id = s.booking_id
   JOIN dcsa_im_v3_0.consignment_item con ON con.shipment_id = s.id
   JOIN dcsa_im_v3_0.shipping_instruction AS si ON con.shipping_instruction_id = si.id
   JOIN dcsa_im_v3_0.reference AS reference ON reference.consignment_item_id = con.id
   OR reference.shipment_id = s.id
   OR reference.shipping_instruction_id = si.id
   OR reference.booking_id = b.id
   UNION ALL
      -- For BKG document reference ShipmentEvents
 SELECT DISTINCT reference.reference_value,
                 reference.reference_type_code,
                 NULL::UUID AS transport_call_id,
                 s.id AS document_id,
                 NULL::UUID AS utilized_transport_equipment_id,
                 'BKG' AS link_type
   FROM dcsa_im_v3_0.shipment s
   JOIN dcsa_im_v3_0.booking b ON b.id = s.booking_id
   JOIN dcsa_im_v3_0.consignment_item con ON con.shipment_id = s.id
   JOIN dcsa_im_v3_0.shipping_instruction AS si ON con.shipping_instruction_id = si.id
   JOIN dcsa_im_v3_0.reference AS reference ON reference.consignment_item_id = con.id
   OR reference.shipment_id = s.id
   OR reference.shipping_instruction_id = si.id
   OR reference.booking_id = b.id
   UNION ALL -- For SHI document reference ShipmentEvents
 SELECT DISTINCT reference.reference_value,
                 reference.reference_type_code,
                 NULL::UUID AS transport_call_id,
                 si.id AS document_id,
                 NULL::UUID AS utilized_transport_equipment_id,
                 'SHI' AS link_type
   FROM dcsa_im_v3_0.shipping_instruction si
   JOIN dcsa_im_v3_0.consignment_item con ON con.shipping_instruction_id = si.id
   JOIN dcsa_im_v3_0.shipment s ON s.id = con.shipment_id
   JOIN dcsa_im_v3_0.booking b ON b.id = s.booking_id
   JOIN dcsa_im_v3_0.reference AS reference ON reference.consignment_item_id = con.id
   OR reference.shipment_id = s.id
   OR reference.shipping_instruction_id = si.id
   OR reference.booking_id = b.id);

/*
* COMBINE references view for all event types
*/
DROP VIEW IF EXISTS dcsa_im_v3_0.event_reference CASCADE;

CREATE VIEW dcsa_im_v3_0.event_reference AS
  SELECT uuid_generate_v4() AS random_id,
                               *
  FROM
  (SELECT *
   FROM dcsa_im_v3_0.equipment_event_reference
   UNION ALL SELECT *
   FROM dcsa_im_v3_0.transport_based_event_reference
   UNION ALL SELECT *
   FROM dcsa_im_v3_0.shipment_event_reference) AS foo;

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
CREATE VIEW dcsa_im_v3_0.event_document_reference AS
SELECT uuid_generate_v4() AS random_id,
                             *
FROM (
        (-- For Transport Call based events
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
            FROM dcsa_im_v3_0.transport_document td)
) AS foo;

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
    subscription_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    callback_url text NOT NULL,
    document_reference varchar(100) NULL,
    equipment_reference varchar(15) NULL,
    transport_call_reference varchar(100) NULL,
    vessel_imo_number varchar(7) NULL,
    carrier_export_voyage_number varchar(50) NULL,
    universal_export_voyage_reference varchar(5) NULL,
    carrier_service_code varchar(5) NULL,
    universal_service_reference varchar(8) NULL,
    un_location_code varchar(5) NULL,
    secret bytea NOT NULL,
    created_date_time timestamp with time zone NOT NULL default now()
);
CREATE INDEX ON dcsa_im_v3_0.event_subscription (created_date_time);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_event_types CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_event_type (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    event_type varchar(16) NOT NULL CONSTRAINT event_type CHECK (event_type IN ('SHIPMENT','TRANSPORT', 'EQUIPMENT')),
    PRIMARY KEY (subscription_id, event_type)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_transport_event_type_code CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_transport_event_type_code (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    transport_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_event_type (transport_event_type_code),
    PRIMARY KEY (subscription_id, transport_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_shipment_event_type_code CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_shipment_event_type_code (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    shipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type (shipment_event_type_code),
    PRIMARY KEY (subscription_id, shipment_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_equipment_event_type_code CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_equipment_event_type_code (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    equipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.equipment_event_type (equipment_event_type_code),
    PRIMARY KEY (subscription_id, equipment_event_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_document_type_code CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_document_type_code (
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    document_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.document_type (document_type_code),
    PRIMARY KEY (subscription_id, document_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.outgoing_event_queue CASCADE;
CREATE TABLE dcsa_im_v3_0.outgoing_event_queue (
    delivery_id uuid PRIMARY KEY default uuid_generate_v4(),
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    event_id uuid NOT NULL,
    payload TEXT NOT NULL,
    enqueued_at_date_time timestamp with time zone NOT NULL default now(),
    UNIQUE (subscription_id, event_id)
);

-- Separate view to avoid entangling `EventSubscription` entity into the event processors (and thereby keeping it
-- clear of TNT and other implementations that supports event subscriptions).
-- Note it might be tempting to denormalize secret and callback_url into the outgoing_event_queue (thereby avoiding
-- the need for the view), but that would require that we also update these fields when updating subscription and
-- that would do a "reverse entanglement", where the subscription handlers need to know about this queue.
DROP VIEW IF EXISTS dcsa_im_v3_0.outgoing_event_queue_with_metadata CASCADE;
CREATE VIEW dcsa_im_v3_0.outgoing_event_queue_with_metadata AS
    SELECT outgoing_event_queue.delivery_id,
           outgoing_event_queue.subscription_id,
           outgoing_event_queue.event_id,
           outgoing_event_queue.enqueued_at_date_time,
           convert_to(outgoing_event_queue.payload, 'UTF8') AS payload_bytes,
           'sha256=' || encode(
                hmac(
                    convert_to(outgoing_event_queue.payload, 'UTF8'),
                    event_subscription.secret,
                    'sha256'
                ),
               'hex'
           ) AS signature_header_value,
           event_subscription.callback_url AS callback_url
      FROM dcsa_im_v3_0.outgoing_event_queue
      JOIN dcsa_im_v3_0.event_subscription USING (subscription_id);

-- A rule that enables us to delete from outgoing_event_queue via the outgoing_event_queue_with_metadata
-- view.  This simplifies the camel part as camel can now just listen on the view.
CREATE RULE outgoing_event_queue_with_metadata_delete AS ON DELETE TO dcsa_im_v3_0.outgoing_event_queue_with_metadata DO INSTEAD(
   DELETE FROM dcsa_im_v3_0.outgoing_event_queue WHERE delivery_id=OLD.delivery_id;
);

DROP TABLE IF EXISTS dcsa_im_v3_0.outgoing_event_queue_dead CASCADE;
CREATE TABLE dcsa_im_v3_0.outgoing_event_queue_dead (
    delivery_id uuid PRIMARY KEY,
    event_id uuid NOT NULL,
    subscription_id uuid NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    payload TEXT NOT NULL,
    enqueued_at_date_time timestamp with time zone NOT NULL,
    last_failed_at_date_time timestamp with time zone NOT NULL default now(),
    failure_reason_type varchar(200),
    failure_reason_message text
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
     cycle_name text NOT NULL UNIQUE,
     display_order int NOT NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_part CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_part (
    port_call_part varchar(100) PRIMARY KEY,
    display_order int NOT NULL UNIQUE
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
    port_call_part varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.port_call_part (port_call_part),
    event_location_requirement varchar(10) NOT NULL CHECK (event_location_requirement IN ('EXCLUDED', 'OPTIONAL', 'REQUIRED')),
    is_terminal_needed boolean NOT NULL,
    is_vessel_draft_relevant boolean NOT NULL,
    vessel_position_requirement varchar(10) NOT NULL CHECK (event_location_requirement IN ('EXCLUDED', 'OPTIONAL', 'REQUIRED')),
    is_miles_to_destination_relevant boolean NOT NULL,
    provided_in_standard text NOT NULL,
    accept_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(timestamp_id) INITIALLY DEFERRED,
    reject_timestamp_definition text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(timestamp_id) INITIALLY DEFERRED,
    negotiation_cycle varchar(50) NOT NULL REFERENCES dcsa_im_v3_0.negotiation_cycle(cycle_key) INITIALLY DEFERRED,
    implicit_variant_of text NULL REFERENCES dcsa_im_v3_0.timestamp_definition(timestamp_id) INITIALLY DEFERRED
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
CREATE INDEX ON dcsa_im_v3_0.operations_event (event_created_date_time);
CREATE INDEX ON dcsa_im_v3_0.operations_event (transport_call_id);

-- Only used by UI support to assist the UI
DROP VIEW IF EXISTS dcsa_im_v3_0.jit_port_visit_ui_context CASCADE;
CREATE OR REPLACE VIEW dcsa_im_v3_0.jit_port_visit_ui_context AS
    SELECT jit_port_visit.port_visit_id,  -- port call visit
           latest_change.event_created_date_time AS latest_event_created_date_time,
           latest_eta_or_pta_berth.event_date_time AS best_berth_estimate_date_time,
           latest_atd_berth.event_date_time AS atd_berth_date_time,
           -- We use created for omits because it makes it easier for the UI to tell whether the OMIT is the latest
           -- timestamp (via tc.latest_event_created_date_time == tc.omit_created_date_time).
           -- The event_created_date_time timestamp is also useful for marking anything before that date time as
           -- obsolete.
           latest_omit.event_created_date_time AS omit_created_date_time,
           latest_eta_or_pta_berth.vessel_draft AS vessel_draft,
           latest_eta_or_pta_berth.miles_to_destination_port AS miles_to_destination_port
           FROM dcsa_im_v3_0.jit_port_visit
      LEFT JOIN (SELECT MAX(event_created_date_time) AS event_created_date_time, transport_call_jit_port_visit.port_visit_id
                 FROM dcsa_im_v3_0.operations_event
                 JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                 GROUP BY port_visit_id
           ) AS latest_change ON (jit_port_visit.port_visit_id = latest_change.port_visit_id)
      LEFT JOIN (
               SELECT operations_event.event_date_time, transport_call_jit_port_visit.port_visit_id, operations_event.vessel_draft, operations_event.miles_to_destination_port
               FROM dcsa_im_v3_0.operations_event
               JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
               JOIN (
                   SELECT MAX(event_created_date_time) AS event_created_date_time, port_visit_id
                       FROM dcsa_im_v3_0.operations_event
                       JOIN dcsa_im_v3_0.transport_call_jit_port_visit ON operations_event.transport_call_id = transport_call_jit_port_visit.transport_call_id
                       JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                       JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                       WHERE timestamp_definition.timestamp_type_name IN ('ETA-Berth', 'ETA-Berth (<implicit>)', 'PTA-Berth', 'PTA-Berth (<implicit>)')
                       GROUP BY port_visit_id
                   ) AS latest_ts ON (transport_call_jit_port_visit.port_visit_id = latest_ts.port_visit_id AND operations_event.event_created_date_time = latest_ts.event_created_date_time)
                   JOIN dcsa_im_v3_0.ops_event_timestamp_definition ON (operations_event.event_id = ops_event_timestamp_definition.event_id)
                   JOIN dcsa_im_v3_0.timestamp_definition ON (timestamp_definition.timestamp_id = ops_event_timestamp_definition.timestamp_definition)
                   WHERE timestamp_definition.timestamp_type_name IN ('ETA-Berth', 'ETA-Berth (<implicit>)', 'PTA-Berth', 'PTA-Berth (<implicit>)')
          ) AS latest_eta_or_pta_berth ON (jit_port_visit.port_visit_id = latest_eta_or_pta_berth.port_visit_id)
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
    login_information TEXT NOT NULL,
    vessel_imo_number varchar(255) NULL,
    publisher_role varchar(3) NULL REFERENCES dcsa_im_v3_0.party_function(party_function_code) CHECK(publisher_role IN ('CA', 'AG', 'VSL', 'ATH', 'PLT', 'TR', 'TWG', 'BUK', 'LSH', 'SLU', 'SVP', 'MOR'))
);

DROP TABLE IF EXISTS dcsa_im_v3_0.outbox_message CASCADE;
CREATE TABLE dcsa_im_v3_0.outbox_message (
    id uuid NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    message_routing_rule_id uuid NOT NULL REFERENCES dcsa_im_v3_0.message_routing_rule (id),
    payload TEXT NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.outbox_message (message_routing_rule_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.timestamp_notification_dead CASCADE;
CREATE TABLE dcsa_im_v3_0.timestamp_notification_dead (
    id uuid NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    message_routing_rule_id uuid NOT NULL REFERENCES dcsa_im_v3_0.message_routing_rule (id),
    payload TEXT NOT NULL,
    latest_delivery_attempted_datetime timestamp with time zone NOT NULL DEFAULT now()
);


DROP VIEW IF EXISTS dcsa_im_v3_0.event_sync_state;
CREATE VIEW dcsa_im_v3_0.event_sync_state AS
    SELECT event_id, (CASE WHEN SUM(delivery_attempted) > 0 THEN 'ATTEMPTED_DELIVERY'
                           WHEN SUM(delivery_attempted) = 0 THEN 'PENDING_DELIVERY'
                           ELSE 'DELIVERY_FINISHED'
        END) AS delivery_status
    FROM (
              SELECT event_id, 0 AS delivery_attempted
              FROM dcsa_im_v3_0.outgoing_event_queue
          UNION ALL
              SELECT event_id, 1 AS delivery_attempted
              FROM dcsa_im_v3_0.outgoing_event_queue_dead
          UNION ALL
              SELECT outbox_message.id, 0 AS delivery_attempted
                FROM dcsa_im_v3_0.outbox_message
          UNION ALL
              SELECT timestamp_notification_dead.id, 1 AS delivery_attempted
                FROM dcsa_im_v3_0.timestamp_notification_dead
         ) AS event_status
    GROUP BY event_id;


-- simplify commodityRequestedEquipmentLink
DROP TABLE IF EXISTS dcsa_im_v3_0.commodity_requested_equipment_link CASCADE;
CREATE TABLE dcsa_im_v3_0.commodity_requested_equipment_link (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    commodity_requested_equipment_link varchar(100) NOT NULL
);
ALTER TABLE dcsa_im_v3_0.commodity ADD commodity_requested_equipment_link_id uuid NULL REFERENCES dcsa_im_v3_0.commodity_requested_equipment_link(id);
ALTER TABLE dcsa_im_v3_0.requested_equipment_group ADD commodity_requested_equipment_link_id uuid NULL REFERENCES dcsa_im_v3_0.commodity_requested_equipment_link(id);

ALTER TABLE dcsa_im_v3_0.requested_equipment_commodity ADD commodity_requested_equipment_link varchar(100) NOT NULL;

COMMIT;
