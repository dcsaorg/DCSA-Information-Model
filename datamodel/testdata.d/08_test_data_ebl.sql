\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'The Factory'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'Port of Singapore'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'Port of Rotterdam'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    'Genneb'
);

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name
) VALUES (
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

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_reference,
    receipt_delivery_type_at_origin,
    receipt_delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract,
    commodity_type,
    cargo_gross_weight,
    cargo_gross_weight_unit
) VALUES (
    'BR1239719871',
    'CY',
    'CFS',
    'FCL',
    'LCL',
    DATE '2020-03-07',
    'You will surrender',
    'Donno',
    13233.4,
    'KGM'
);

INSERT INTO dcsa_im_v3_0.shipment (
    id,
    collection_datetime,
    delivery_datetime,
    carrier_id,
    carrier_booking_reference
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    DATE '2020-03-07',
    DATE '2020-03-31',
    uuid('5c7e736a-402e-11eb-b3e9-cff0135e510a'),
    'BR1239719871'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    '2106W'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879e'),
    '2107E'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879f'),
    '2108W'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8790'),
    '2218W'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8791'),
    '2219E'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8792'),
    '2418W'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8793'),
    '2419E'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8794'),
    '3418W'
);

INSERT INTO dcsa_im_v3_0.voyage (
    id,
    carrier_voyage_number
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8795'),
    '3419E'
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code
) VALUES (
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'SGSIN' AND facility_smdg_code = 'PSABT'),
    'POTE'
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879e'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879f'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'NLRTM' AND facility_smdg_code = 'APM'),
    'COFS'
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8790'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8791'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    other_facility,
    location_id
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    1,
    null,
    'COFS',
    null,
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8792'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8793'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    other_facility,
    location_id
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388'),
    1,
    null,
    'INTE',
    null,
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8794'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388')
);

INSERT INTO dcsa_im_v3_0.transport_call_voyage (
    voyage_id,
    transport_call_id
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d8795'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388')
);

INSERT INTO dcsa_im_v3_0.vessel (
    vessel_imo_number,
    vessel_name,
    vessel_flag,
    vessel_call_sign_number,
    vessel_operator_carrier_id
) VALUES (
    '1801323',
    'Emma Maersk',
    'DK',
    null,
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK')
);

INSERT INTO dcsa_im_v3_0.transport (
    id,
    transport_reference,
    transport_name,
    mode_of_transport,
    load_transport_call_id,
    discharge_transport_call_id,
    vessel_imo_number
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e1f'),
    'transport reference',
    'Transport name',
    '1',
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    '1801323'
);

INSERT INTO dcsa_im_v3_0.transport (
    id,
    transport_reference,
    transport_name,
    mode_of_transport,
    load_transport_call_id,
    discharge_transport_call_id,
    vessel_imo_number
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e2f'),
    'transport reference xx',
    'Transport name xx',
    '2',
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    null
);

INSERT INTO dcsa_im_v3_0.transport (
    id,
    transport_reference,
    transport_name,
    mode_of_transport,
    load_transport_call_id,
    discharge_transport_call_id,
    vessel_imo_number
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e3f'),
    'transport reference yy',
    'Transport name yy',
    '2',
    uuid('770b7624-403d-11eb-b44b-d3f4ad185387'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185388'),
    null
);

INSERT INTO dcsa_im_v3_0.shipment_transport (
    shipment_id,
    transport_id,
    sequence_number,
    commercial_voyage_id,
    is_under_shippers_responsibility
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e1f'),
    1,
    null,
    false
);

INSERT INTO dcsa_im_v3_0.shipment_transport (
    shipment_id,
    transport_id,
    sequence_number,
    commercial_voyage_id,
    is_under_shippers_responsibility
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e2f'),
    2,
    null,
    false
);

INSERT INTO dcsa_im_v3_0.shipment_transport (
    shipment_id,
    transport_id,
    sequence_number,
    commercial_voyage_id,
    is_under_shippers_responsibility
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e3f'),
    3,
    null,
    true
);

INSERT INTO dcsa_im_v3_0.party (
    id,
    party_name
) VALUES (
    '4e448f26-4035-11eb-a49d-7f9eb9bc8dd9',
    'Malwart'
);

INSERT INTO dcsa_im_v3_0.party (
    id,
    party_name
) VALUES (
    '8dd9a4c4-4039-11eb-8770-0b2b19847fab',
    'Malwart Düsseldorf'
);

INSERT INTO dcsa_im_v3_0.document_party (
    party_id,
    shipment_id,
    party_function,
    is_to_be_notified
) VALUES (
    '4e448f26-4035-11eb-a49d-7f9eb9bc8dd9',
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    'OS',
    true
);

INSERT INTO dcsa_im_v3_0.document_party (
    party_id,
    shipment_id,
    party_function,
    is_to_be_notified
) VALUES (
    '8dd9a4c4-4039-11eb-8770-0b2b19847fab',
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    'CN',
    true
);


INSERT INTO dcsa_im_v3_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'PRE'
);

INSERT INTO dcsa_im_v3_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'POL'
);

INSERT INTO dcsa_im_v3_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'POD'
);

INSERT INTO dcsa_im_v3_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
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
    cargo_gross_weight_unit
) VALUES (
    uuid('e0b81540-4066-11eb-9a35-7734806583a6'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    'BMOU2149612',
    4000,
    'KGM'
);


COMMIT;
