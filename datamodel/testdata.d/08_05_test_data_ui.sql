\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

SELECT 'Start: 08_05_test_data_ui.sql...' as progress;

INSERT INTO dcsa_im_v3_0.operations_event (
    event_id,
    event_date_time,
    event_classifier_code,
    publisher,
    publisher_role,
    operations_event_type_code,
    event_location,
    transport_call_id,
    port_call_service_type_code,
    facility_type_code,
    delay_reason_code,
    vessel_position,
    remark,
    port_call_phase_type_code    
) VALUES (
    'd330b6f5-edcb-4e9e-a09f-e98e91deba95',
    DATE '2022-03-07',
    'REQ',
    'c49ea2d6-3806-46c8-8490-294affc71286',
    'TR',
    'ARRI',
    'b4454ae5-dcd4-4955-8080-1f986aa5c6c3',
    '286c605e-4043-11eb-9c0b-7b4196cf71fa',
    null,
    'BRTH',
    null,
    '1d09e9e9-dba3-4de1-8ef8-3ab6d32dbb40',
    null,
    'INBD'
), (
    '538312da-674c-4278-bf9f-10e2a7c018e3', /* event_id */
    DATE '2022-03-07', /* event_date_time */
    'PLN', /* event_classifier_code */
    '7bf6f428-58f0-4347-9ce8-d6be2f5d5745', /* publisher */
    'PLT', /* publisher_role */
    'STRT', /* operations_event_type_code */
    '06aca2f6-f1d0-48f8-ba46-9a3480adfd23', /* event_location */
    'b785317a-2340-4db7-8fb3-c8dfb1edfa60', /* transport_call_id */
    'PILO', /* port_call_service_type_code */
    null, /* facility_type_code */
    'ANA', /* delay_reason_code */
    null, /* vessel_position */
    null, /* remark */
    'INBD' /* port_call_phase_type_code */
);

INSERT INTO dcsa_im_v3_0.ops_event_timestamp_definition (
    event_id,
    payload_id,
    timestamp_definition
) VALUES (
    'd330b6f5-edcb-4e9e-a09f-e98e91deba95',
    null,
    'TS1'
), (
    '538312da-674c-4278-bf9f-10e2a7c018e3',
    null,
    'TS50'
);

SELECT 'End: 08_05_test_data_ui.sql' as progress;

COMMIT;
