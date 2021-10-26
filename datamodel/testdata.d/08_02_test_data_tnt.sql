\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

SELECT 'Start: 08_02_test_data_tnt.sql...' as progress;

INSERT INTO dcsa_im_v3_0.vessel (
    id,
    vessel_imo_number,
    vessel_name,
    vessel_flag,
    vessel_call_sign_number,
    vessel_operator_carrier_id
) VALUES (
    '48fddb68-74d9-4b30-8bc9-2fbe70aff303',
    '1234567',
    'King of the Seas',
    'DE',
    'NCVV',
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK')
);

INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    mode_of_transport,
    vessel_id
) VALUES (
    '8b64d20b-523b-4491-b2e5-32cfa5174eed',
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'SGSIN' AND facility_smdg_code = 'PSABT'),
    'POTE',
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'VESSEL'),
    '48fddb68-74d9-4b30-8bc9-2fbe70aff303'
);


INSERT INTO dcsa_im_v3_0.transport_call (
    id,
    transport_call_sequence_number,
    facility_id,
    facility_type_code,
    mode_of_transport,
    vessel_id
) VALUES (
    '123e4567-e89b-12d3-a456-426614174000',
    1,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE un_location_code = 'USNYC' AND facility_smdg_code = 'APMT'),
    'POTE',
    (SELECT mode_of_transport_code FROM dcsa_im_v3_0.mode_of_transport WHERE dcsa_transport_type = 'VESSEL'),
    '48fddb68-74d9-4b30-8bc9-2fbe70aff303'
);

INSERT INTO dcsa_im_v3_0.transport (
    transport_reference,
    transport_name,
    load_transport_call_id,
    discharge_transport_call_id
) VALUES (
    'transport reference',
    'Transport name (Singapore -> NYC)',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    uuid('123e4567-e89b-12d3-a456-426614174000')
);



INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_reference,
    receipt_delivery_type_at_origin,
    receipt_delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
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
    service_contract_reference,
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

INSERT INTO dcsa_im_v3_0.booking (
    carrier_booking_reference,
    receipt_delivery_type_at_origin,
    receipt_delivery_type_at_destination,
    cargo_movement_type_at_origin,
    cargo_movement_type_at_destination,
    booking_request_datetime,
    service_contract_reference,
    commodity_type,
    cargo_gross_weight,
    cargo_gross_weight_unit
) VALUES (
    'ABC123123123',
    'CY',
    'CFS',
    'FCL',
    'LCL',
    DATE '2019-09-10',
    'You will surrender',
    'Donno',
    13233.4,
    'KGM'
);

INSERT INTO dcsa_im_v3_0.shipment (
    collection_datetime,
    delivery_datetime,
    carrier_id,
    carrier_booking_reference
) VALUES (
    DATE '2020-07-07',
    DATE '2020-07-31',
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    'BR1239719971'
);

INSERT INTO dcsa_im_v3_0.shipment (
    collection_datetime,
    delivery_datetime,
    carrier_id,
    carrier_booking_reference
) VALUES (
    DATE '2019-11-10',
    DATE '2019-11-12',
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'MSK'),
    'ABC123123123'
);

INSERT INTO dcsa_im_v3_0.shipment_transport (
    transport_id,
    shipment_id,
    transport_plan_stage_sequence_number,
    transport_plan_stage_code,
    is_under_shippers_responsibility
) VALUES (
    (SELECT DISTINCT transport.id FROM dcsa_im_v3_0.transport WHERE load_transport_call_id = '8b64d20b-523b-4491-b2e5-32cfa5174eed' OR discharge_transport_call_id = '8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'ABC123123123'),
    1,
    'PRC',
    false
);

INSERT INTO dcsa_im_v3_0.references (
    reference_type,
    reference_value,
    shipment_id
) VALUES (
    'FF',
    'string',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'ABC123123123')
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_date_time,
    shipment_event_type_code,
    document_type_code,
    document_id
) VALUES (
    uuid('784871e7-c9cd-4f59-8d88-2e033fa799a1'),
    'PLN',
    '2020-07-15',
    'APPR',
    'BKG',
    'BR1239719971'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_date_time,
    shipment_event_type_code,
    document_type_code,
    document_id
) VALUES (
    uuid('e48f2bc0-c746-11ea-a3ff-db48243a89f4'),
    'PLN',
    TO_DATE('2020/07/15 13:14:15', 'yyyy/mm/dd hh24:mi:ss'),
    'APPR',
    'BKG',
    'BR1239719971'
);

INSERT INTO dcsa_im_v3_0.shipment_event (
    event_id,
    event_classifier_code,
    event_date_time,
    shipment_event_type_code,
    document_type_code,
    document_id
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea1'),
    'PLN',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'CONF',
    'BKG',
    'ABC123123123'
);

INSERT INTO dcsa_im_v3_0.iso_equipment_code (
    iso_equipment_code,
    iso_equipment_name,
    iso_equipment_size_code,
    iso_equipment_type_code_a
) VALUES (
    '22G2',
    'Twenty foot dry 2',
    '23',
    'G2'
);

INSERT INTO dcsa_im_v3_0.equipment(
    equipment_reference,
    iso_equipment_code,
    tare_weight,
    weight_unit
) VALUES (
    'equipref3453',
    '22G2',
    null,
    null
);

INSERT INTO dcsa_im_v3_0.equipment(
    equipment_reference,
    iso_equipment_code,
    tare_weight,
    weight_unit
) VALUES (
    'APZU4812090',
    '22G2',
    null,
    null
);

INSERT INTO dcsa_im_v3_0.shipment_equipment (
    shipment_id,
    equipment_reference,
    cargo_gross_weight,
    cargo_gross_weight_unit,
    is_shipper_owned
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'ABC123123123'),
    'APZU4812090',
    1424.2,
    'KGM',
    false
);

INSERT INTO dcsa_im_v3_0.seal (
    shipment_equipment_id,
    seal_number,
    seal_source_code,
    seal_type_code
) VALUES (
     (SELECT DISTINCT shipment_equipment.id FROM dcsa_im_v3_0.shipment_equipment JOIN dcsa_im_v3_0.shipment ON (shipment.id = shipment_equipment.shipment_id)
            WHERE carrier_booking_reference = 'ABC123123123' AND equipment_reference = 'APZU4812090'),
     'SN123457',
     'CUS',
     'WIR'
);

INSERT INTO dcsa_im_v3_0.equipment_event (
    event_id,
    event_classifier_code,
    event_date_time,
    equipment_event_type_code,
    transport_call_id,
    equipment_reference,
    empty_indicator_code
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea2'),
    'ACT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'LOAD',
    '8b64d20b-523b-4491-b2e5-32cfa5174eed',
    'equipref3453',
    'EMPTY'
);

INSERT INTO dcsa_im_v3_0.transport_event (
    event_id,
    event_classifier_code,
    event_date_time,
    transport_event_type_code,
    transport_call_id,
    delay_reason_code,
    change_remark
) VALUES (
    uuid('5e51e72c-d872-11ea-811c-0f8f10a32ea3'),
    'ACT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    'ANA',
    'Authorities not available'
);

INSERT INTO dcsa_im_v3_0.transport_event (
    event_id,
    event_classifier_code,
    event_created_date_time,
    event_date_time,
    transport_event_type_code,
    transport_call_id,
    delay_reason_code,
    change_remark
) VALUES (
    uuid('84db923d-2a19-4eb0-beb5-446c1ec57d34'),
    'ACT',
    '2021-01-09T14:12:56+01:00'::timestamptz,
    '2019-11-12T07:41:00+08:30'::timestamptz,
    'ARRI',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    'WEA',
    'Bad weather'
);


INSERT INTO dcsa_im_v3_0.equipment_event (
    event_id,
    event_classifier_code,
    event_created_date_time,
    event_date_time,
    equipment_event_type_code,
    transport_call_id,
    empty_indicator_code,
    equipment_reference
) VALUES (
    uuid('84db923d-2a19-4eb0-beb5-446c1ec57d34'),
    'EST',
    '2021-01-09T14:12:56+01:00'::timestamptz,
    '2019-11-12T07:41:00+08:30'::timestamptz,
    'LOAD',
    uuid('8b64d20b-523b-4491-b2e5-32cfa5174eed'),
    'EMPTY',
    'APZU4812090'
);

INSERT INTO dcsa_im_v3_0.transport_event (
    event_classifier_code,
    event_date_time,
    event_created_date_time,
    transport_event_type_code,
    transport_call_id,
    delay_reason_code,
    change_remark
) VALUES (
    'EST',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2003/05/01 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    uuid('123e4567-e89b-12d3-a456-426614174000'),
    'ANA',
    'Authorities not available'
);

INSERT INTO dcsa_im_v3_0.transport_event (
    event_classifier_code,
    event_date_time,
    event_created_date_time,
    transport_event_type_code,
    transport_call_id,
    delay_reason_code,
    change_remark
) VALUES (
    'ACT',
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),
    'DEPA',
    uuid('123e4567-e89b-12d3-a456-426614174000'),
    'ANA',
    'Authorities not available'
);

SELECT 'End: 08_02_test_data_tnt.sql' as progress;

COMMIT;
