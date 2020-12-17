\connect dcsa_openapi

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
