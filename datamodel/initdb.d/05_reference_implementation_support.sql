-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Implementation specific SQL for the reference implementation.
BEGIN;
-- Aggregated table containing all events
DROP VIEW IF EXISTS dcsa_im_v3_0.aggregated_events CASCADE;
CREATE VIEW dcsa_im_v3_0.aggregated_events AS
 SELECT transport_event.event_id,
    transport_event.event_type,
    transport_event.event_classifier_code,
    transport_event.event_type_code,
    transport_event.event_date_time,
    transport_event.event_created_date_time,
    transport_event.transport_call_id,
    transport_event.delay_reason_code,
    transport_event.change_remark,
    NULL::text AS shipment_information_type_code,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    NULL::UUID AS shipment_id
   FROM dcsa_im_v3_0.transport_event
UNION
 SELECT shipment_event.event_id,
    shipment_event.event_type,
    shipment_event.event_classifier_code,
    shipment_event.event_type_code,
    shipment_event.event_date_time,
    shipment_event.event_created_date_time,
    NULL::text AS transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    shipment_event.shipment_information_type_code,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code,
    shipment_event.shipment_id
   FROM dcsa_im_v3_0.shipment_event
UNION
 SELECT equipment_event.event_id,
    equipment_event.event_type,
    equipment_event.event_classifier_code,
    equipment_event.event_type_code,
    equipment_event.event_date_time,
    equipment_event.event_created_date_time,
    equipment_event.transport_call_id,
    NULL::text AS delay_reason_code,
    NULL::text AS change_remark,
    NULL::text AS shipment_information_type_code,
    equipment_event.equipment_reference,
    equipment_event.empty_indicator_code,
    NULL::UUID AS shipment_id
   FROM dcsa_im_v3_0.equipment_event;



DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
    subscription_id varchar(100) DEFAULT uuid_generate_v4() PRIMARY KEY,
    callback_url text NOT NULL,
    booking_reference varchar(35),
    transport_document_id varchar(20),
    transport_document_type text,
    equipment_reference varchar(15),
    schedule_id uuid NULL,
    transport_call_id uuid NULL,

    signature_method varchar(20) NOT NULL,
    secret bytea NOT NULL,
    -- these two combined is a cursor for the subscription to unique identify which
    -- event was the last delivered
    last_event_date_created_date_time timestamp with time zone,
    last_event_id uuid NULL,
    last_bundle_size int NULL,
    last_status_message text NULL,
    accumulated_retry_delay bigint NULL,
    -- Retry state
    retry_after timestamp with time zone NULL,
    retry_count int DEFAULT 0 NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription_event_types CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription_event_types (
    subscription_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.event_subscription (subscription_id) ON DELETE CASCADE,
    event_type text,

    PRIMARY KEY (subscription_id, event_type)
);

COMMIT;
