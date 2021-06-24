\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

INSERT INTO dcsa_im_v3_0.transport_call(
    id
) VALUES (
    '8b64d20b-523b-4491-b2e5-32cfa5174eed'
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
    'BR1239719971',
    'CY',
    'CFS',
    'FCL',
    'LCL',
    DATE '2020-07-07',
    'You will surrender',
    'Donno',
    13233.4,
    'KGM'
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
    'BR1239719872',
    'CY',
    'CFS',
    'FCL',
    'LCL',
    DATE '2020-04-15',
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
    uuid('6e2d856c-d871-11ea-a630-03e5334d1800'),
    DATE '2020-07-07',
    DATE '2020-07-31',
    uuid('5c7e736a-402e-11eb-b3e9-cff0135e510a'),
    'BR1239719971'
);

INSERT INTO dcsa_im_v3_0.shipment (
    id,
    collection_datetime,
    delivery_datetime,
    carrier_id,
    carrier_booking_reference
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea1'),
    DATE '2020-04-15',
    DATE '2003-05-03',
    uuid('5c7e736a-402e-11eb-b3e9-cff0135e510a'),
    'BR1239719872'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_type,
    event_date_time,
    shipment_event_type_code,
    shipment_id,
    document_type_code
) VALUES (
    uuid('784871e7-c9cd-4f59-8d88-2e033fa799a1'),
    'PLN',
    'SHIPMENT',
    '2020-07-15',
    'APPR',
    uuid('6e2d856c-d871-11ea-a630-03e5334d1800'),
    'BKG'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_type,
    event_date_time,
    shipment_event_type_code,
    shipment_id,
    document_type_code
) VALUES (
    uuid('e48f2bc0-c746-11ea-a3ff-db48243a89f4'),
    'PLN',
    'SHIPMENT',
    TO_DATE('2020/07/15 13:14:15', 'yyyy/mm/dd hh24:mi:ss'),
    'APPR',
    uuid('6e2d856c-d871-11ea-a630-03e5334d1800'),
    'BKG'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_type,
    event_date_time,
    shipment_event_type_code,
    shipment_id,
    document_type_code
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea1'),
    'PLN',
    'SHIPMENT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'CONF',
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea1'),
    'BKG'
);

INSERT INTO dcsa_im_v3_0.equipment_event (
    event_id,
    event_classifier_code,
    event_type,
    event_date_time,
    equipment_event_type_code,
    transport_call_id,
    equipment_reference,
    empty_indicator_code
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea2'),
    'ACT',
    'EQUIPMENT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'LOAD',
    '8b64d20b-523b-4491-b2e5-32cfa5174eed',
    'equipref3453',
    'EMPTY'
);

INSERT INTO dcsa_im_v3_0.transport_event (
    event_id,
    event_classifier_code,
    event_type,
    event_date_time,
    transport_event_type_code,
    transport_call_id,
    delay_reason_code,
    change_remark
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea3'),
    'ACT',
    'TRANSPORT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    'ABC',
    'Do not know a valid delay reason code...'
);

COMMIT;
