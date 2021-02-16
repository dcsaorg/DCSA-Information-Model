\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

INSERT INTO dcsa_ebl_v1_0.shipment (
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

INSERT INTO dcsa_ebl_v1_0.party (
    id,
    party_name
) VALUES (
    uuid('4e448f26-4035-11eb-a49d-7f9eb9bc8dd9'),
    'Malwart'
);

INSERT INTO dcsa_ebl_v1_0.party (
    id,
    party_name
) VALUES (
    uuid('8dd9a4c4-4039-11eb-8770-0b2b19847fab'),
    'Malwart Düsseldorf'
);

INSERT INTO dcsa_ebl_v1_0.document_party (
    party_id,
    shipment_id,
    party_function,
    should_be_notified
) VALUES (
    uuid('4e448f26-4035-11eb-a49d-7f9eb9bc8dd9'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    'OS',
    true
);

INSERT INTO dcsa_ebl_v1_0.document_party (
    party_id,
    shipment_id,
    party_function,
    should_be_notified
) VALUES (
    uuid('8dd9a4c4-4039-11eb-8770-0b2b19847fab'),
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    'CN',
    true
);

INSERT INTO dcsa_ebl_v1_0.location (
    id,
    location_name
) VALUES (
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'The Factory'
);

INSERT INTO dcsa_ebl_v1_0.location (
    id,
    location_name
) VALUES (
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'Port of Singapore'
);

INSERT INTO dcsa_ebl_v1_0.location (
    id,
    location_name
) VALUES (
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'Port of Rotterdam'
);

INSERT INTO dcsa_ebl_v1_0.location (
    id,
    location_name
) VALUES (
    uuid('7f29ce3c-403d-11eb-9579-6bd2f4cf4ed6'),
    'The Warehouse'
);

INSERT INTO dcsa_ebl_v1_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('84bfcf2e-403b-11eb-bc4a-1fc4aa7d879d'),
    'PRE'
);

INSERT INTO dcsa_ebl_v1_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('286c605e-4043-11eb-9c0b-7b4196cf71fa'),
    'POL'
);

INSERT INTO dcsa_ebl_v1_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    'POD'
);

INSERT INTO dcsa_ebl_v1_0.shipment_location (
    shipment_id,
    location_id,
    location_type
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0f'),
    uuid('7f29ce3c-403d-11eb-9579-6bd2f4cf4ed6'),
    'PDE'
);

INSERT INTO dcsa_ebl_v1_0.iso_equipment_code (
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

INSERT INTO dcsa_ebl_v1_0.equipment (
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

INSERT INTO dcsa_ebl_v1_0.shipment_equipment (
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

INSERT INTO dcsa_ebl_v1_0.hs_code (
    hs_code,
    code_description
) VALUES (
    '411510',
    'Leather; composition leather with a basis of leather or leather fibre, in slabs, sheets or strip, whether or not in rolls'
);

INSERT INTO dcsa_ebl_v1_0.party (
    id,
    party_name
) VALUES (
    uuid('1e118f26-1035-11eb-a19d-7f9eb9bc8ff9'),
    'Cargo Unlimited'
);

INSERT INTO dcsa_ebl_v1_0.shipping_instruction (
	id,
	transport_document_type,
	number_of_copies,
	number_of_originals,
	is_part_load,
	is_electronic,
	callback_url

) VALUES (
    uuid('111a5606-402e-11eb-b19a-0f3aa4962e00'),
    'BOL',
    2,
    4,
    false,
    true,
    'http://myserver.com'
);

INSERT INTO dcsa_ebl_v1_0.transport_document (
	id,
	place_of_issue,
	date_of_issue,
	onboard_date,
	received_for_shipment_date,
	document_reference_number,
	terms_and_conditions,
	issuer,
	shipping_instruction_id,
	declared_value_currency,
	declared_value,
	number_of_rider_pages
) VALUES (
    uuid('561a5606-402e-11eb-b19a-0f3aa4962e0e'),
    uuid('770b7624-403d-11eb-b44b-d3f4ad185386'),
    DATE '2020-01-14',
    DATE '2020-01-20',
    DATE '2020-01-18',
    '123',
    'You will surrender',
    'MMCU',
    uuid('111a5606-402e-11eb-b19a-0f3aa4962e00'),
    'DKK',
    '10.000',
    '5'
);

COMMIT;
