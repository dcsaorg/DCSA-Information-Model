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
    carrier_voyage_number,
    place_of_issue
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
    NULL
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
    carrier_voyage_number,
    place_of_issue
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
    '01670315-a51f-4a11-b947-ce8e245128eb'
);


INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_01'),
    'BR1239719871',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12'
);

INSERT INTO dcsa_im_v3_0.shipment (
    carrier_id,
    booking_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    (SELECT id FROM dcsa_im_v3_0.booking WHERE carrier_booking_request_reference = 'CARRIER_BOOKING_REQUEST_REFERENCE_02'),
    'CR1239719872',
    'TERMS AND CONDITIONS!',
    DATE '2020-03-07T12:12:12'
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
    booking_id,
    shipment_id,
    transport_id,
    transport_plan_stage_sequence_number,
    transport_plan_stage_code,
    commercial_voyage_id,
    is_under_shippers_responsibility
) VALUES (
    null,
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e1f'),
    1,
    'PRC',
    null,
    false
), (
    null,
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e2f'),
    2,
    'PRC',
    null,
    false
), (
    null,
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

INSERT INTO dcsa_im_v3_0.shipping_instruction (
    id,
    is_shipped_onboard_type,
    number_of_copies,
    number_of_originals,
    is_electronic,
    are_charges_displayed_on_originals,
    are_charges_displayed_on_copies
) VALUES (
    '01670315-a51f-4a11-b947-ce8e245128eb',
    TRUE,
    2,
    4,
    TRUE,
    TRUE,
    FALSE
);

SELECT 'End: 08_01_test_data_ebl.sql' as progress;

COMMIT;
