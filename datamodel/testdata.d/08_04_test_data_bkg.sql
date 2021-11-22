\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

SELECT 'Start: 08_04_test_data_bkg.sql...' as progress;

INSERT INTO dcsa_im_v3_0.shipment_cutoff_time (
    shipment_id,
    cut_off_time_code,
    cut_off_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'ABC123123123'),
    'AFD',
    DATE '2021-03-09'
);

INSERT INTO dcsa_im_v3_0.shipment_cutoff_time (
    shipment_id,
    cut_off_time_code,
    cut_off_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871'),
    'DCO',
    DATE '2021-05-01'
);

INSERT INTO dcsa_im_v3_0.shipment_cutoff_time (
    shipment_id,
    cut_off_time_code,
    cut_off_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'CR1239719872'),
    'ECP',
    DATE '2020-07-07'
);

INSERT INTO dcsa_im_v3_0.shipment_cutoff_time (
    shipment_id,
    cut_off_time_code,
    cut_off_time
) VALUES (
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719971'),
    'EFC',
    DATE '2020-01-06'
);


INSERT INTO dcsa_im_v3_0.carrier_clauses (
    id,
    clause_content
) VALUES (
    'b8e312ad-7b00-4026-88ad-9881242ca4f4'::uuid,
    'Carrier Clauses entity: comprises clauses, added by the carrier to the Shipment, which are subject to local rules/guidelines or certain mandatory information required to be shared with the customer. Usually printed below the cargo description.'
);

INSERT INTO dcsa_im_v3_0.carrier_clauses (
    id,
    clause_content
) VALUES (
    '93eedc86-f8a3-4ec3-8d30-ad1eb8a079d2'::uuid,
    'Shipment Carrier Clauses entity: address the carrier clauses for a shipment.'
);

INSERT INTO dcsa_im_v3_0.carrier_clauses (
    id,
    clause_content
) VALUES (
    'cbe900e7-7ad9-45fc-8d9e-0d1628a1b4f7'::uuid,
    'Incoterms entity: Transport obligations, costs and risks as agreed between buyer and seller.'
);

INSERT INTO dcsa_im_v3_0.carrier_clauses (
    id,
    clause_content
) VALUES (
    '3991a845-6cc8-404a-ac25-a1393e1d93a9'::uuid,
    'Value Added Service Request entity: An entity containing data on requests for value added services. '
);


INSERT INTO dcsa_im_v3_0.shipment_carrier_clauses (
    carrier_clause_id,
    shipment_id
) VALUES (
    'b8e312ad-7b00-4026-88ad-9881242ca4f4',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'ABC123123123')
);

INSERT INTO dcsa_im_v3_0.shipment_carrier_clauses (
    carrier_clause_id,
    shipment_id
) VALUES (
    '93eedc86-f8a3-4ec3-8d30-ad1eb8a079d2',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719871')
);

INSERT INTO dcsa_im_v3_0.shipment_carrier_clauses (
    carrier_clause_id,
    shipment_id
) VALUES (
    'cbe900e7-7ad9-45fc-8d9e-0d1628a1b4f7',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'CR1239719872')
);

INSERT INTO dcsa_im_v3_0.shipment_carrier_clauses (
    carrier_clause_id,
    shipment_id
) VALUES (
    '3991a845-6cc8-404a-ac25-a1393e1d93a9',
    (SELECT id FROM dcsa_im_v3_0.shipment WHERE carrier_booking_reference = 'BR1239719971')
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
    )VALUES(
    '8fecc6d0-2a78-401d-948a-b9753f6b53d5'::uuid,
    'Lukas',
    'Rohrdamm',
    '81',
    '5',
    '32108',
    'Bad Salzuflen Grastrup-hölsen',
    'Nordrhein-Westfalen',
    'Germany');

INSERT INTO dcsa_im_v3_0.location (
    id,
    location_name,
    latitude,
    longitude,
    un_location_code,
    address_id,
    facility_id
    ) VALUES (
    'c703277f-84ca-4816-9ccf-fad8e202d3b6',
    'Hamburg',
    '53.551° N',
    '9.9937° E',
    'DEHAM',
    '8fecc6d0-2a78-401d-948a-b9753f6b53d5'::uuid,
    (SELECT id FROM dcsa_im_v3_0.facility WHERE facility_name = 'DP WORLD JEBEL ALI - CT1' AND un_location_code = 'AEJEA' AND facility_smdg_code = 'DPWJA')
    );

INSERT INTO dcsa_im_v3_0.booking (
    id,
    carrier_booking_request_reference,
    document_status,
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
    submission_datetime,
    is_ams_aci_filing_required,
    is_destination_filing_required,
    contract_quotation_reference,
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
    )VALUES(
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    'ef223019-ff16-4870-be69-9dbaaaae9b11',
    'PENA',
    'CY',
    'CY',
    'FCL',
    'LCL',
    '2021-11-03 02:11:00.000',
    'Test',
     NULL,
     true,
     true,
     NULL,
     true,
     NULL,
     '2021-11-03 10:41:00.000',
     true,
     true,
     NULL,
     NULL,
     'c703277f-84ca-4816-9ccf-fad8e202d3b6',
     NULL,
     NULL,
     NULL,
     NULL,
     'AO',
     true,
     NULL,
     NULL,
     NULL);

INSERT INTO dcsa_im_v3_0.commodity(
    id,
    booking_id,
    commodity_type,
    hs_code,
    cargo_gross_weight,
    cargo_gross_weight_unit,
    export_license_issue_date,
    export_license_expiry_date
    ) VALUES (
    '85b681bf-68a0-4f90-8cc6-79bf77d3b2a1'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    'Hand Bags',
    '411510',
    1200.0,
    'KGM',
    NULL,
    NULL);

INSERT INTO dcsa_im_v3_0.commodity (
    id,
    booking_id,
    commodity_type,
    hs_code,
    cargo_gross_weight,
    cargo_gross_weight_unit,
    export_license_issue_date,
    export_license_expiry_date
    ) VALUES (
    '54c9b7fb-b641-4ccc-b1be-70a63fac17d6'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    'Bloom',
    '720711',
    2000.0,
    'LBR',
    NULL,
    NULL);


INSERT INTO dcsa_im_v3_0.value_added_service_request (
    id,
    booking_id,
    value_added_service_code
    ) VALUES (
    '1f32fee5-edcc-4a62-afe1-a97ac236f30d'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    'SCON');

INSERT INTO dcsa_im_v3_0.shipment (
    id,
    booking_id,
    carrier_id,
    carrier_booking_reference,
    terms_and_conditions,
    confirmation_datetime
    ) VALUES (
    '6e4035d9-3200-43ee-a7ea-6d8108c5f0c8'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    (SELECT id FROM dcsa_im_v3_0.carrier WHERE smdg_code = 'HLC'),
    'ABC123123123',
    'TERMS AND CONDITIONS!',
    '2021-12-12 12:12:12.000');

INSERT INTO dcsa_im_v3_0.reference (
    id,
    reference_type_code,
    reference_value,
    shipment_id,
    shipping_instruction_id,
    booking_id
    ) VALUES (
    '48fbbce6-54f7-4e87-b9aa-525dda865962'::uuid,
    'FF',
    'test',
    '6e4035d9-3200-43ee-a7ea-6d8108c5f0c8'::uuid,
    NULL,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid);

INSERT INTO dcsa_im_v3_0.requested_equipment (
    id,
    booking_id,
    shipment_id,
    requested_equipment_type,
    requested_equipment_units,
    confirmed_equipment_type,
    confirmed_equipment_units,
    is_shipper_owned
    ) VALUES (
    '1a595981-c2d9-46a9-a870-3086735b4529'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    '6e4035d9-3200-43ee-a7ea-6d8108c5f0c8'::uuid,
    '22GP',
    3,
    NULL,
    NULL,
    true);

INSERT INTO dcsa_im_v3_0.party (
    id,
    party_name,
    tax_reference_1,
    tax_reference_2,
    public_key,
    address_id
    ) VALUES (
    '7bf6f428-58f0-4347-9ce8-d6be2f5d5745',
    'Hapag Lloyd',
    'CVR-25645774',
    'CVR-25645774',
    'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFzaW',
    '8fecc6d0-2a78-401d-948a-b9753f6b53d5'::uuid);

INSERT INTO dcsa_im_v3_0.party_identifying_code (
    id,
    dcsa_responsible_agency_code,
    party_id,
    code_list_name,
    party_code
    ) VALUES (
    '3fce10f1-e524-49c3-ac47-4260ceccc7f6'::uuid,
    'SCAC',
    '7bf6f428-58f0-4347-9ce8-d6be2f5d5745',
    'LCL',
    'MMCU');

INSERT INTO dcsa_im_v3_0.document_party (
    id,
    party_id,
    shipping_instruction_id,
    shipment_id,
    party_function,
    is_to_be_notified,
    booking_id
    ) VALUES (
    'c678ce03-3859-4db3-a23f-d7c3f998fd0a'::uuid,
    '7bf6f428-58f0-4347-9ce8-d6be2f5d5745',
    NULL,
    '6e4035d9-3200-43ee-a7ea-6d8108c5f0c8'::uuid,
    'DDS',
    true,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid);

INSERT INTO dcsa_im_v3_0.displayed_address (
    id,
    document_party_id,
    address_line_number,
    address_line_text
    ) VALUES (
    '207e0ee4-9750-4b41-8fe2-ca65b1e11c2c'::uuid,
    'c678ce03-3859-4db3-a23f-d7c3f998fd0a'::uuid,
    1,
    'Gubener Str. 42');

INSERT INTO dcsa_im_v3_0.displayed_address (
    id,
    document_party_id,
    address_line_number,
    address_line_text
    ) VALUES (
    '659013f6-cf4b-46c5-a2b5-20a173a05ce6'::uuid,
    'c678ce03-3859-4db3-a23f-d7c3f998fd0a'::uuid,
    2,
    'Rhinstrasse 87');

INSERT INTO dcsa_im_v3_0.party_contact_details (
    id,
    party_id,
    name,
    email,
    phone
    ) VALUES (
    'b24d099e-a6f6-404e-b082-776f7f589061'::uuid,
    '7bf6f428-58f0-4347-9ce8-d6be2f5d5745',
    'Peanut',
    'peanut@jeff-fa-fa.com',
    '+31123456789');

INSERT INTO dcsa_im_v3_0.shipment_location (
    id,
    shipment_id,
    booking_id,
    location_id,
    shipment_location_type_code,
    displayed_name,
    event_date_time
    ) VALUES (
    'd3001e47-07e9-4bba-87a7-019d3e12f3aa'::uuid,
    '6e4035d9-3200-43ee-a7ea-6d8108c5f0c8'::uuid,
    'a521dbdb-a12b-48f5-b489-8594349731bf'::uuid,
    'c703277f-84ca-4816-9ccf-fad8e202d3b6',
    'POL',
    'Hamburg',
     NULL);


SELECT 'End: 08_04_test_data_bkg.sql' as progress;

COMMIT;