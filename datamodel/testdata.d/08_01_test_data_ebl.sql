\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

SELECT 'Start: 08_01_test_data_ebl.sql...' as progress;

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'The Factory'
), (
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'Port of Singapore'
), (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'Port of Rotterdam'
), (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    'Genneb'
), (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388'),
    'Nijmegen'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('7f29ce3c-403d-11eb-9579-6bd2f4cf4ed6'),
    'The Warehouse'
);

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
    uuid('41272437-a160-4040-9a9d-c6676f9eb1b7'),
    'Lagkagehuset',
    'Islands Brygge',
    '43',
    'St',
    '2300',
    'København S',
    'N/A',
    'Denmark'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name,
    address_id,
    latitude,
    longitude,
    un_location_code
) VALUES (
    '01670315-a51f-4a11-b947-ce8e245128eb',
    'Lagkagehuset Islands Brygge',
    uuid('41272437-a160-4040-9a9d-c6676f9eb1b7'),
    '55.6642249',
    '12.57341045',
    'USNYC'
);

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_01',
    'RECE',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_01',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_01',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_01',
    TRUE,
    'FCA',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_01',
    'BOOKING_CHA_REF_01',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_01',
    NULL,
    DATE '2021-12-09'
);

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    invoice_payable_at,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_02',
    'RECE',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_02',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_02',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_02',
    TRUE,
    'FCA',
    '84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_02',
    'BOOKING_CHA_REF_02',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_02',
    '01670315-a51f-4a11-b947-ce8e245128eb',
    DATE '2021-12-16'
);


INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime,
    updated_date_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    'BR1239719871',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
);

INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime,
    updated_date_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_02'),
    'CR1239719872',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
);

/**
 * Data used in integration tests - Do not modify - make your own data
 */
INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime,
    updated_date_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    'bca68f1d3b804ff88aaa1e43055432f7',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    '832deb4bd4ea4b728430b857c59bd057',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    '994f0c2b590347ab86ad34cd1ffba505',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    '02c965382f5a41feb9f19b24b5fe2906',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
);

INSERT INTO dcsa_im_v3_0.reference (
    reference_type_code,
    reference_value,
    shipment_id
) VALUES (
    'CR',
    'AB-123743CR',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871')
), (
    'PO',
    'PO0027',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871')
), (
    'CR',
    'BC-346267CR',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'CR1239719872')
), (
    'PO',
    'PO0028',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'CR1239719872')
);

INSERT INTO dcsa_im_v3_0.voyage (
    carrier_voyage_number
) VALUES
    ('2106W'),
    ('2107E'),
    ('2108W'),
    ('2218W'),
    ('2219E'),
    ('2418W'),
    ('2419E'),
    ('3418W'),
    ('3419E');

INSERT INTO dcsa_im_v3_0.vessel (
    vessel_imo_number,
    vessel_name,
    vessel_flag,
    vessel_call_sign_number,
    vessel_operator_carrier_id
) VALUES (
    '9321483',
    'Emma Maersk',
    'DK',
    null,
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    mode_of_transport_code,
    vessel_id,
    import_voyage_id,
    export_voyage_id
) VALUES (
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'SGSIN' AND facility_smdg_code = 'PSABT'),
    'POTE',
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'VESSEL'),
    (SELECT id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2106W'),
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2107E')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    mode_of_transport_code,
    vessel_id,
    import_voyage_id,
    export_voyage_id
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'NLRTM' AND facility_smdg_code = 'APM'),
    'COFS',
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'RAIL'),
    null,
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2218W'),
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2219E')
);


INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    other_facility,
    location_id,
    mode_of_transport_code,
    vessel_id,
    import_voyage_id,
    export_voyage_id
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    1,
    null,
    'COFS',
    null,
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'RAIL'),
    null,
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2418W'),
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '2419E')
);


INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    other_facility,
    location_id,
    mode_of_transport_code,
    vessel_id,
    import_voyage_id,
    export_voyage_id
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388'),
    1,
    null,
    'INTE',
    null,
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388'),
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'RAIL'),
    null,
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '3418W'),
    (SELECT id FROM dcsa_im_v3_0.voyage WHERE carrier_voyage_number = '3419E')
);

INSERT INTO dcsa_im_v3_0.transport (
    id,
    transport_reference,
    transport_name,
    load_transport_call_id,
    discharge_transport_call_id
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e1f'),
    'transport reference',
    'Transport name',
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386')
);

INSERT INTO dcsa_im_v3_0.transport (
    id,
    transport_reference,
    transport_name,
    load_transport_call_id,
    discharge_transport_call_id
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e2f'),
    'transport reference xx',
    'Transport name xx',
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387')
), (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e3f'),
    'transport reference yy',
    'Transport name yy',
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388')
);

INSERT INTO dcsa_im_v3_0.shipment_transport (
    shipment_id,
    transport_id,
    transport_plan_stage_sequence_number,
    transport_plan_stage_code,
    commercial_voyage_id,
    is_under_shippers_responsibility
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e1f'),
    1,
    'PRC',
    null,
    false
), (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e2f'),
    2,
    'PRC',
    null,
    false
), (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e3f'),
    3,
    'PRC',
    null,
    true
);

INSERT INTO dcsa_im_v3_0.party (
    id,
    party_name
) VALUES (
    '4e448f26-4035-11eb-a49d-7f9eb9bc8dd9',
    'Malwart'
), (
    '8dd9a4c4-4039-11eb-8770-0b2b19847fab',
    'Malwart Düsseldorf'
);

INSERT INTO dcsa_im_v3_0.document_party (
    party_id,
    shipment_id,
    party_function,
    is_to_be_notified,
    booking_id
) VALUES (
    '4e448f26-4035-11eb-a49d-7f9eb9bc8dd9',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    'OS',
    true,
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01')
), (
    '8dd9a4c4-4039-11eb-8770-0b2b19847fab',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    'CN',
    true,
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01')
);

INSERT INTO dcsa_im_v3_0.shipment_location (
    shipment_id,
    booking_id,
    location_id,
    shipment_location_type_code
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'PRE'
), (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'POL'
), (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'POD'
), (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    uuid('7f29ce3c-403d-11eb-9579-6bd2f4cf4ed6'),
    'PDE'
);

INSERT INTO dcsa_im_v3_0.iso_equipment_code (
    iso_equipment_code,
    iso_equipment_name,
    iso_equipment_size_code,
    iso_equipment_type_code_a
) VALUES (
    '22G1',
    'Twenty foot dry',
    '22',
    'G1'
);

INSERT INTO dcsa_im_v3_0.equipment (
    equipment_reference,
    iso_equipment_code,
    tare_weight,
    weight_unit
) VALUES (
    'BMOU2149612',
    '22G1',
    2000,
    'KGM'
);

INSERT INTO dcsa_im_v3_0.shipment_equipment (
    id,
    shipment_id,
    equipment_reference,
    cargo_gross_weight,
    cargo_gross_weight_unit,
    is_shipper_owned
) VALUES (
    uuid('e0b81540-4066-11eb-9a35-7734806583a6'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    'BMOU2149612',
    4000,
    'KGM',
    false
);

/**
 * Data used in integration tests - Do not modify - make your own data
 */
INSERT INTO dcsa_im_v3_0.shipment_equipment (
    id,
    shipment_id,
    equipment_reference,
    cargo_gross_weight,
    cargo_gross_weight_unit,
    is_shipper_owned
) VALUES (
    uuid('6824b6ca-f3da-4154-96f1-264886b68d53'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'bca68f1d3b804ff88aaa1e43055432f7'),
    'BMOU2149612',
    4000,
    'KGM',
    false
),(
    uuid('44068608-da9b-4039-b074-d9ac27ddbfbf'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = '832deb4bd4ea4b728430b857c59bd057'),
    'BMOU2149612',
    4000,
    'KGM',
    false
),(
    uuid('56812ad8-5d0b-4cbc-afca-e97f2f3c89de'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = '994f0c2b590347ab86ad34cd1ffba505'),
    'BMOU2149612',
    4000,
    'KGM',
    false
),(
    uuid('ca030eb6-009b-411c-985c-527ce008b35a'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = '02c965382f5a41feb9f19b24b5fe2906'),
    'BMOU2149612',
    4000,
    'KGM',
    false
);

INSERT INTO dcsa_im_v3_0.shipping_instruction (
    id,
    document_status,
    is_shipped_onboard_type,
    number_of_copies,
    number_of_originals,
    is_electronic,
    is_to_order,
    are_charges_displayed_on_originals,
    are_charges_displayed_on_copies,
    created_date_time,
    updated_date_time
) VALUES (
    '01670315-a51f-4a11-b947-ce8e245128eb',
    'RECE',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2021-12-24',
    DATE '2021-12-31'
);

/**
 * Data used in integration tests - Do not modify - make your own data
 */
INSERT INTO dcsa_im_v3_0.shipping_instruction (
    id,
    document_status,
    is_shipped_onboard_type,
    number_of_copies,
    number_of_originals,
    is_electronic,
    is_to_order,
    are_charges_displayed_on_originals,
    are_charges_displayed_on_copies,
    created_date_time,
    updated_date_time
) VALUES (
    '9d5965a5-9e2f-4c78-b8cb-fbb7095e13a0',
    'APPR',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2022-01-24',
    DATE '2022-01-31'
),(
    '877ce0f8-3126-45f5-b22e-2d1d27d42d85',
    'RECE',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2022-02-01',
    DATE '2022-02-07'
),(
    '770f11e5-aae2-4ae4-b27e-0c689ed2e333',
    'RECE',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2021-02-08',
    DATE '2021-02-09'
),(
    'cb6354c9-1ceb-452c-aed0-3cb25a04647a',
    'PENU',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2021-02-08',
    DATE '2021-02-09'
),(
    '8fbb78cc-e7c6-4e17-9a23-24dc3ad0378d',
    'APPR',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    DATE '2022-03-01',
    DATE '2022-03-07'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
   event_classifier_code,
   event_date_time,
   event_created_date_time,
   shipment_event_type_code,
   document_type_code,
   document_id,
   reason
) VALUES (
   'ACT',
   '2021-01-08T13:22:53Z',
   '2021-01-08T13:22:53Z',
   'RECE',
   'SHI',
   '770f11e5-aae2-4ae4-b27e-0c689ed2e333',
   null
), (
  'ACT',
  '2021-01-08T17:22:53Z',
  '2021-01-08T17:22:53Z',
  'PENU',
  'SHI',
  '770f11e5-aae2-4ae4-b27e-0c689ed2e333',
  'Carrier Booking Reference present in both shipping instruction as well as cargo items.'
), (
  'ACT',
  '2021-01-08T18:22:53Z',
  '2021-01-08T18:22:53Z',
  'DRFT',
  'SHI',
  '770f11e5-aae2-4ae4-b27e-0c689ed2e333',
  null
), (
   'ACT',
   '2022-03-01T18:22:53Z',
   '2022-03-01T18:22:53Z',
   'RECE',
   'SHI',
   '8fbb78cc-e7c6-4e17-9a23-24dc3ad0378d',
   null
), (
   'ACT',
   '2022-03-03T18:22:53Z',
   '2022-03-03T18:22:53Z',
   'DRFT',
   'SHI',
   '8fbb78cc-e7c6-4e17-9a23-24dc3ad0378d',
   null
), (
   'ACT',
   '2022-03-03T18:22:53Z',
   '2022-03-03T18:22:53Z',
   'DRFT',
   'TRD',
   '2b02401c-b2fb-5009',
   null
), (
   'ACT',
   '2022-03-05T13:56:12Z',
   '2022-03-05T13:56:12Z',
   'APPR',
   'TRD',
   '2b02401c-b2fb-5009',
   null
 );

INSERT INTO dcsa_im_v3_0.transport_document (
    transport_document_reference,
    place_of_issue,
    issue_date,
    shipped_onboard_date,
    received_for_shipment_date,
    number_of_originals,
    issuer,
    shipping_instruction_id,
    declared_value_currency,
    declared_value,
    number_of_rider_pages,
    created_date_time,
    updated_date_time
) VALUES (
    '1af13f0b-a1ea-4ff8',
    '01670315-a51f-4a11-b947-ce8e245128eb',
    DATE '2020-11-25',
    DATE '2020-12-24',
    DATE '2020-12-31',
    12,
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'HLC'),
    '877ce0f8-3126-45f5-b22e-2d1d27d42d85'::uuid,
    'WTK',
    12.12,
    12,
    '2021-11-28T14:12:56+01:00'::timestamptz,
    '2021-12-01T07:41:00+08:30'::timestamptz
), (
  '2b02401c-b2fb-5009',
  '01670315-a51f-4a11-b947-ce8e245128eb',
  DATE '2020-11-25',
  DATE '2020-12-24',
  DATE '2020-12-31',
  12,
  (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'HLC'),
  '8fbb78cc-e7c6-4e17-9a23-24dc3ad0378d'::uuid,
  'WTK',
  12.12,
  12,
  '2022-03-03T18:22:53Z'::timestamptz,
  '2022-03-05T13:56:12Z'::timestamptz
);

INSERT INTO dcsa_im_v3_0.package_code(
    package_code,
    package_code_description
) VALUES (
    '123',
    'test description1'
), (
    '234',
    'test description2'
), (
    '456',
    'test description3'
), (
    '789',
    'test description4'
);

/**
 * Data used in integration tests - Do not modify - make your own data
 */
INSERT INTO dcsa_im_v3_0.cargo_item (
    shipping_instruction_id,
    description_of_goods,
    hs_code,
    weight,
    weight_unit,
    number_of_packages,
    package_code,
    shipment_equipment_id
) VALUES (
    '9d5965a5-9e2f-4c78-b8cb-fbb7095e13a0',
    'Expensive Shoes',
    '411510',
    50.0,
    'KGM',
    5000,
    '123',
    uuid('6824b6ca-f3da-4154-96f1-264886b68d53')
), (
    '9d5965a5-9e2f-4c78-b8cb-fbb7095e13a0',
    'Massive Yacht',
    '720711',
    1000.0,
    'KGM',
    1,
    '456',
    uuid('44068608-da9b-4039-b074-d9ac27ddbfbf')
), (
    '877ce0f8-3126-45f5-b22e-2d1d27d42d85',
    'Leather Jackets',
    '411510',
    23.5,
    'KGM',
    2500,
    '789',
    uuid('56812ad8-5d0b-4cbc-afca-e97f2f3c89de')
), (
    '877ce0f8-3126-45f5-b22e-2d1d27d42d85',
    'luftballons',
    '411510',
    99.9,
    'KGM',
    99,
    '234',
    uuid('44068608-da9b-4039-b074-d9ac27ddbfbf')
), (
   '770f11e5-aae2-4ae4-b27e-0c689ed2e333',
   'Air ballons',
   '411510',
   99.9,
   'KGM',
   99,
   '234',
   uuid('44068608-da9b-4039-b074-d9ac27ddbfbf')
), (
    'cb6354c9-1ceb-452c-aed0-3cb25a04647a',
    'Leather Jackets',
    '411510',
    23.5,
    'KGM',
    2500,
    '789',
    uuid('ca030eb6-009b-411c-985c-527ce008b35a')
), (
   '8fbb78cc-e7c6-4e17-9a23-24dc3ad0378d',
   'Leather Jackets',
   '411510',
   23.5,
   'KGM',
   2500,
   '789',
   uuid('ca030eb6-009b-411c-985c-527ce008b35a')
);

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_03',
    'CONF',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_03',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_03',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_03',
    TRUE,
    'FCA',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_03',
    'BOOKING_CHA_REF_03',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_03',
    NULL,
    DATE '2021-12-09'
);

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    invoice_payable_at,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_04',
    'CONF',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_04',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_04',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_04',
    TRUE,
    'FCA',
    '84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_04',
    'BOOKING_CHA_REF_04',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_04',
    '01670315-a51f-4a11-b947-ce8e245128eb',
    DATE '2021-12-16'
);


INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_05',
    'CONF',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_05',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_05',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_05',
    TRUE,
    'FCA',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_05',
    'BOOKING_CHA_REF_05',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_05',
    NULL,
    DATE '2021-12-09'
);

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_request_reference,
    document_status,
    submission_datetime,
    receipt_type_at_origin,
    delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    payment_term_code,
    is_partial_load_allowed,
    is_export_declaration_required,
    export_declaration_reference,
    is_import_license_required,
    import_license_reference,
    is_destination_filing_required,
    incoterms,
    invoice_payable_at,
    expected_departure_date,
    transport_document_type_code,
    transport_document_reference,
    booking_channel_reference,
    communication_channel_code,
    is_equipment_substitution_allowed,
    vessel_id,
    export_voyage_number,
    place_of_issue,
    updated_date_time
) VALUES (
    'CARRIER_BOOKING_REQUEST_REFERENCE_06',
    'CONF',
    DATE '2020-03-07',
    'CY',
    'CFS',
    'FCL',
    'BB',
    DATE '2020-03-07',
    'SERVICE_CONTRACT_REFERENCE_06',
    'PRE',
    TRUE,
    TRUE,
    'EXPORT_DECLARATION_REFERENCE_06',
    FALSE,
    'IMPORT_LICENSE_REFERENCE_06',
    TRUE,
    'FCA',
    '84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d',
    DATE '2020-03-07',
    'SWB',
    'TRANSPORT_DOC_REF_06',
    'BOOKING_CHA_REF_06',
    'EI',
    FALSE,
    (SELECT vessel.id FROM dcsa_im_v3_0.vessel WHERE vessel_imo_number = '9321483'),
    'CARRIER_VOYAGE_NUMBER_06',
    '01670315-a51f-4a11-b947-ce8e245128eb',
    DATE '2021-12-16'
);



INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime,
    updated_date_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_03'),
    '43f615138efc4d3286b36402405f851b',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_04'),
    'e8e9d64172934a40aec82e4308cdf97a',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_05'),
    '6fe84758a4cc471fb5eb-4de63ddadc41',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
),
(
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_06'),
    '5dc92988f48a420495b786c224efce7d',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12',
    DATE '2020-04-07T12:12:12'
);

SELECT 'End: 08_01_test_data_ebl.sql' as progress;

COMMIT;
