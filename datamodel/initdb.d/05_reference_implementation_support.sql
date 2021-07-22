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
    NULL::text AS document_type_code,
    NULL::text AS equipment_event_type_code,
    transport_event.event_date_time,
    transport_event.event_created_date_time,
    transport_event.transport_call_id,
    transport_event.delay_reason_code,
    transport_event.change_remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    NULL::text AS document_id,
    NULL::text AS reason
   FROM dcsa_im_v3_0.transport_event
UNION
 SELECT shipment_event.event_id,
    'SHIPMENT' AS event_type,
    shipment_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    shipment_event.document_type_code,
    NULL::text AS equipment_event_type_code,
    shipment_event.event_date_time,
    shipment_event.event_created_date_time,
    NULL::text AS transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    shipment_event.document_id AS document_id,
    shipment_event.reason AS reason
   FROM dcsa_im_v3_0.shipment_event
UNION
 SELECT equipment_event.event_id,
    'EQUIPMENT' AS event_type,
    equipment_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    NULL::text AS document_type_code,
    equipment_event.equipment_event_type_code,
    equipment_event.event_date_time,
    equipment_event.event_created_date_time,
    equipment_event.transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    equipment_event.equipment_reference,
    equipment_event.empty_indicator_code,
    NULL::text AS document_id,
    NULL::text AS reason
   FROM dcsa_im_v3_0.equipment_event
UNION
 SELECT operations_event.event_id,
    'OPERATIONS' AS event_type,
    operations_event.event_classifier_code,
    NULL::text AS transport_event_type_code,
    NULL::text AS document_type_code,
    NULL::text AS equipment_event_type_code,
    operations_event.event_date_time,
    operations_event.event_created_date_time,
    operations_event.transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    NULL::text AS document_id,
    NULL::text AS reason
   FROM dcsa_im_v3_0.operations_event;

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
     subscription_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
     callback_url text NOT NULL,
     carrier_booking_reference varchar(35),
     transport_document_id varchar(20),
     transport_document_type text,
     equipment_reference varchar(15),
     schedule_id varchar(100) NULL,
     transport_call_id varchar(100) NULL,
     signature_method varchar(20) NOT NULL,
     secret bytea NOT NULL,
     transport_event_type_code varchar(20) ,
     transport_document_reference text NULL,
     transport_document_type_code text NULL,
     shipment_event_type_code varchar(4) NULL,
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


-- Indexes to help the reference implementation deduplicate these. Fields are ordered by how discriminatory they are
-- presumed to be in the general case or how fast they would like to check.
CREATE UNIQUE INDEX deduplicate_location
    ON dcsa_im_v3_0.location (address_id, un_location_code, location_name, latitude, longitude);

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

COMMIT;
