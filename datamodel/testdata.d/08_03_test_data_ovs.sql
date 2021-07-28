\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'PILO',
    'PILO_port_call_service_type_name',
    'PILO_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'MOOR',
    'MOOR_port_call_service_type_name',
    'MOOR_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'CRGO',
    'CRGO_port_call_service_type_name',
    'CRGO_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'TOWG',
    'TOWG_port_call_service_type_name',
    'TOWG_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'BUNK',
    'BUNK_port_call_service_type_name',
    'BUNK_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'WSDP',
    'WSDP_port_call_service_type_name',
    'WSDP_port_call_service_type_description'
);

INSERT INTO dcsa_im_v3_0.operations_event (
    event_id,
    event_classifier_code,
    event_date_time,
    operations_event_type_code,
    transport_call_id,
    delay_reason_code,
    publisher_role,
    port_call_service_type_code,
    event_location,
    facility_type_code
) VALUES (
    uuid('a7a30556-ef7c-11eb-9a03-0242ac130003'),
    'ACT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    'ANA',
    'HAT',
    'CRGO',
    'this is definitely an event location',
    'POTE'
);

INSERT INTO dcsa_im_v3_0.operations_event (
    event_id,
    event_classifier_code,
    event_date_time,
    operations_event_type_code,
    transport_call_id,
    delay_reason_code,
    publisher_role,
    port_call_service_type_code,
    event_location,
    facility_type_code
) VALUES (
    uuid('03482296-ef9c-11eb-9a03-0242ac130003'),
    'EST',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'ARRI',
    uuid('123e4567-e89b-12d3-a456-426614174000'),
    'ANA',
    'KAT',
    'WSDP',
    'this is definitely also another event location',
    'BRTH'
);

COMMIT;

-- REMOVE DATA OVERLAPPING WITH 08_02_test_data_tnt.sql