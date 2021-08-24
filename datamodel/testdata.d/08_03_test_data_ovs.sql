\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;


INSERT INTO dcsa_im_v3_0.address (
    id,
    name,
    street,
    street_number,
    floor,
    postal_code,
    city,
    state_region,
    country
) VALUES (
    uuid('8791f557-fe69-42c9-a420-f39f09dd6207'),
    'Henrik',
    'Kronprincessegade',
    '54',
    '5. sal',
    '1306',
    'København',
    'N/A',
    'Denmark'
);

INSERT INTO  dcsa_im_v3_0.party (
    id,
    party_name,
    tax_reference_1,
    tax_reference_2,
    public_key,
    address_id,
    nmfta_code
) VALUES ( 
    'be5bc290-7bac-48bb-a211-f3fa5a3ab3ae',
    'Asseco Denmark',
    'CVR-25645774',
    'CVR-25645774',
    'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFzaW',
    uuid('8791f557-fe69-42c9-a420-f39f09dd6207'),
    'MMCU'
);

INSERT INTO dcsa_im_v3_0.vessel (
    vessel_imo_number,
    vessel_name,
    vessel_flag,
    vessel_call_sign_number,
    vessel_operator_carrier_id
) VALUES (
    '9811000',
    'Ever Given',
    'PA',
    'H3RC',
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'EMC')
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name,
    address_id,
    latitude,
    longitude,
    un_location_code
) VALUES (
    '06aca2f6-f1d0-48f8-ba46-9a3480adfd23',
    'Eiffel Tower',
    uuid('8791f557-fe69-42c9-a420-f39f09dd6207'),
    '48.8585500',
    '2.294492036',
    'USNYC'
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    mode_of_transport,
    vessel_imo_number
) VALUES (
    '7f2d833c-2c7f-4fc5-a71a-e510881da64a',
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'USNYC' AND facility_smdg_code = 'APMT'),
    'BRTH',
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'VESSEL'),
    '9811000'
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code
) VALUES (
    'b785317a-2340-4db7-8fb3-c8dfb1edfa60',
    2,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'SGSIN' AND facility_smdg_code = 'PSABT'),
    'POTE'
);

INSERT INTO dcsa_im_v3_0.transport (
    transport_reference,
    transport_name,
    load_transport_call_id,
    discharge_transport_call_id
) VALUES (
    'transport reference',
    'Transport name (Singapore -> NYC)',
    '7f2d833c-2c7f-4fc5-a71a-e510881da64a',
    'b785317a-2340-4db7-8fb3-c8dfb1edfa60'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'PILO',
    'PILO_name',
    'PILO_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'MOOR',
    'MOOR_name',
    'MOOR_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'CRGO',
    'CRGO_name',
    'CRGO_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'TOWG',
    'TOWG_name',
    'TOWG_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'BUNK',
    'BUNK_name',
    'BUNK_description'
);

INSERT INTO dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code,
    port_call_service_type_name,
    port_call_service_type_description
) VALUES (
    'WSDP',
    'WSDP_name',
    'WSDP_description'
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
    facility_type_code,
    publisher
) VALUES (
    uuid('b785317a-2340-4db7-8fb3-c8dfb1edfa60'),
    'ACT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    '8b64d20b-523b-4491-b2e5-32cfa5174eed',
    'ANA',
    'TR',
    'BUNK',
    '06aca2f6-f1d0-48f8-ba46-9a3480adfd23',
    'POTE',
    'be5bc290-7bac-48bb-a211-f3fa5a3ab3ae'
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
    facility_type_code,
    publisher
) VALUES (
    uuid('03482296-ef9c-11eb-9a03-0242ac130003'),
    'EST',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'ARRI',
    '123e4567-e89b-12d3-a456-426614174000',
    'ANA',
    'CA',
    'WSDP',
    '06aca2f6-f1d0-48f8-ba46-9a3480adfd23',
    'BRTH',
    'be5bc290-7bac-48bb-a211-f3fa5a3ab3ae'
);

INSERT INTO dcsa_im_v3_0.service (
    id,
    carrier_id,
    carrier_service_code,
    carrier_service_name
) VALUES (
    '03482296-ef9c-11eb-9a03-0242ac131999',
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    'A_CSC',
    'A_carrier_service_name'
);


INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number,
    service_id
) VALUES (
    '03482296-ef9c-11eb-9a03-0242ac131233',
    'A_carrier_voyage_number',
    '03482296-ef9c-11eb-9a03-0242ac131999'
);


INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    '03482296-ef9c-11eb-9a03-0242ac131233',
    '8b64d20b-523b-4491-b2e5-32cfa5174eed'
);

COMMIT;

-- REMOVE DATA OVERLAPPING WITH 08_02_test_data_tnt.sql
