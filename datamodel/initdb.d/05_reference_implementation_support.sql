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
    transport_event.event_date_time,
    transport_event.transport_call_id,
    transport_event.delay_reason_code,
    transport_event.change_remark,
    NULL::text AS shipment_information_type_code,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code
   FROM dcsa_im_v3_0.transport_event
UNION
 SELECT shipment_event.event_id,
    shipment_event.event_type,
    shipment_event.event_classifier_code,
    shipment_event.event_date_time,
    NULL::UUID AS transport_call_id,
    NULL::text AS delay_reason_code,
    NULL:: text AS change_remark,
    shipment_event.shipment_information_type_code,
    NULL::text AS equipment_reference,
    NULL::text AS empty_indicator_code
   FROM dcsa_im_v3_0.shipment_event
UNION
 SELECT equipment_event.event_id,
    equipment_event.event_type,
    equipment_event.event_classifier_code,
    equipment_event.event_date_time,
    equipment_event.transport_call_id,
    NULL::text AS delay_reason_code,
    NULL:: text AS change_remark,
    NULL::text AS shipment_information_type_code,
    equipment_event.equipment_reference,
    equipment_event.empty_indicator_code
   FROM dcsa_im_v3_0.equipment_event;

COMMIT;
