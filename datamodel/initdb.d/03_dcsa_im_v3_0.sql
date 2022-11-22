\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event_type (
    shipment_event_type_code varchar(4) PRIMARY KEY,
    shipment_event_type_name varchar(30) NOT NULL,
    shipment_event_type_description varchar(350) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.unit_of_measure CASCADE;
CREATE TABLE dcsa_im_v3_0.unit_of_measure (
    unit_of_measure_code varchar(3) PRIMARY KEY,
    unit_of_measure_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.hs_code CASCADE;
CREATE TABLE dcsa_im_v3_0.hs_code (
    hs_code varchar(10) PRIMARY KEY,
    hs_code_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.value_added_service CASCADE;
CREATE TABLE dcsa_im_v3_0.value_added_service (
    value_added_service_code varchar(5) PRIMARY KEY,
    value_added_service_name varchar(100) NOT NULL,
    value_added_service_description varchar(200) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.reference_type CASCADE;
CREATE TABLE dcsa_im_v3_0.reference_type (
    reference_type_code varchar(3) PRIMARY KEY,
    reference_type_name varchar(100) NOT NULL,
    reference_type_description varchar(400) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.receipt_delivery_type CASCADE;
CREATE TABLE dcsa_im_v3_0.receipt_delivery_type (
    receipt_delivery_type_code varchar(3) PRIMARY KEY,
    receipt_delivery_type_name varchar(50) NOT NULL,
    receipt_delivery_type_description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.cargo_movement_type CASCADE;
CREATE TABLE dcsa_im_v3_0.cargo_movement_type (
    cargo_movement_type_code varchar(3) PRIMARY KEY,
    cargo_movement_type_name varchar(50) NOT NULL,
    cargo_movement_type_description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.country CASCADE;
CREATE TABLE dcsa_im_v3_0.country (
    country_code varchar(2) PRIMARY KEY,
    country_name varchar(75) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.un_location CASCADE;
CREATE TABLE dcsa_im_v3_0.un_location (
    un_location_code char(5) PRIMARY KEY,
    un_location_name varchar(100) NULL,
    location_code char(3) NULL,
    country_code char(2) NULL REFERENCES dcsa_im_v3_0.country (country_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.address CASCADE;
CREATE TABLE dcsa_im_v3_0.address (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(100) NULL,
    street varchar(100) NULL,
    street_number varchar(50) NULL,
    floor varchar(50) NULL,
    postal_code varchar(10) NULL,
    city varchar(65) NULL,
    state_region varchar(65) NULL,
    country varchar(75) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.location CASCADE;
CREATE TABLE dcsa_im_v3_0.location (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    location_name varchar(100) NULL,
    latitude varchar(10) NULL,
    longitude varchar(11) NULL,
    un_location_code char(5) NULL REFERENCES dcsa_im_v3_0.un_location (un_location_code),
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id),
    facility_id uuid NULL  -- REFERENCES facility (but there is a circular relation, so we add the FK later)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility_type CASCADE;
CREATE TABLE dcsa_im_v3_0.facility_type (
    facility_type_code varchar(4) PRIMARY KEY,
    facility_type_name varchar(100) NOT NULL,
    facility_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility CASCADE;
CREATE TABLE dcsa_im_v3_0.facility (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    facility_name varchar(100) NULL,
    un_location_code varchar(5) NULL REFERENCES dcsa_im_v3_0.un_location (un_location_code), -- The UN Locode prefixing the BIC / SMDG code
    facility_bic_code varchar(4) NULL, -- suffix uniquely identifying the facility when prefixed with the UN Locode
    facility_smdg_code varchar(6) NULL, -- suffix uniquely identifying the facility when prefixed with the UN Locode
    location_id uuid REFERENCES dcsa_im_v3_0.location(id)
);
ALTER TABLE dcsa_im_v3_0.location
    ADD FOREIGN KEY (facility_id) REFERENCES dcsa_im_v3_0.facility (id);

DROP TABLE IF EXISTS dcsa_im_v3_0.carrier CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_name varchar(100),
    smdg_code varchar(3) NULL,
    nmfta_code varchar(4) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party CASCADE;
CREATE TABLE dcsa_im_v3_0.party (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    party_name varchar(100) NULL,
    tax_reference_1 varchar(20) NULL,
    tax_reference_2 varchar(20) NULL,
    public_key varchar(500) NULL,
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_contact_details CASCADE;
CREATE TABLE dcsa_im_v3_0.party_contact_details (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    name varchar(100) NOT NULL,
    email varchar(100) NULL,
    phone varchar(30) NULL,
    url varchar(100) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.code_list_responsible_agency CASCADE;
CREATE TABLE dcsa_im_v3_0.code_list_responsible_agency (
    dcsa_responsible_agency_code varchar(5) NOT NULL PRIMARY KEY,
    code_list_responsible_agency_code varchar(3) NULL,
    code_list_responsible_agency_name varchar(100) NULL,
    code_list_responsible_agency_description varchar(300)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_identifying_code CASCADE;
CREATE TABLE dcsa_im_v3_0.party_identifying_code (
    dcsa_responsible_agency_code varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.code_list_responsible_agency(dcsa_responsible_agency_code),
    party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    code_list_name varchar(100),
    party_code varchar(100) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.payment_term_type CASCADE;
CREATE TABLE dcsa_im_v3_0.payment_term_type (
    payment_term_code varchar(3) PRIMARY KEY,
    payment_term_name varchar(100) NOT NULL,
    payment_term_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.incoterms CASCADE;
CREATE TABLE dcsa_im_v3_0.incoterms (
    incoterms_code varchar(3) PRIMARY KEY,
    incoterms_name varchar(100) NOT NULL,
    incoterms_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document_type (
    transport_document_type_code varchar(3) PRIMARY KEY,
    transport_document_type_name varchar(20) NULL,
    transport_document_type_description varchar(500) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.cut_off_time CASCADE;
CREATE TABLE dcsa_im_v3_0.cut_off_time (
    cut_off_time_code varchar(3) PRIMARY KEY,
    cut_off_time_name varchar(100) NULL,
    cut_off_time_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_type CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_type (
    vessel_type_code varchar(4) PRIMARY KEY,
    vessel_type_name varchar(100) NULL,
    unece_concatenated_means_of_transport_code varchar(4),
    vessel_type_description varchar(100) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    vessel_imo_number varchar(7) NULL UNIQUE,
    vessel_name varchar(35) NULL,
    vessel_flag char(2) NULL,
    vessel_call_sign varchar(10) NULL,
    vessel_operator_carrier_id uuid NULL REFERENCES dcsa_im_v3_0.carrier (id),
    is_dummy boolean NOT NULL default false,
    length_overall numeric NULL,
    width numeric NULL,
    vessel_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.vessel_type (vessel_type_code),
    dimension_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CONSTRAINT dimension_unit CHECK (dimension_unit IN ('FOT','MTR'))
);

DROP TABLE IF EXISTS dcsa_im_v3_0.communication_channel_qualifier CASCADE;
CREATE TABLE dcsa_im_v3_0.communication_channel_qualifier (
    communication_channel_qualifier_code varchar(2) PRIMARY KEY,
    communication_channel_qualifier_name varchar(100) NOT NULL,
    communication_channel_qualifier_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement_type CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement_type (
    vessel_sharing_agreement_type_code varchar(3) NOT NULL PRIMARY KEY,
    vessel_sharing_agreement_type_name varchar(50) NULL,
    vessel_sharing_agreement_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    vessel_sharing_agreement_name varchar(50) NULL,
    vessel_sharing_agreement_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.vessel_sharing_agreement_type(vessel_sharing_agreement_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.tradelane CASCADE;
CREATE TABLE dcsa_im_v3_0.tradelane (
    id varchar(8) PRIMARY KEY,
    tradelane_name varchar(150) NOT NULL,
    vessel_sharing_agreement_id uuid NOT NULL REFERENCES dcsa_im_v3_0.vessel_sharing_agreement(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.service CASCADE;
CREATE TABLE dcsa_im_v3_0.service (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_id uuid NULL REFERENCES dcsa_im_v3_0.carrier (id),
    carrier_service_code varchar(5),
    carrier_service_name varchar(50),
    tradelane_id varchar(8) NULL REFERENCES dcsa_im_v3_0.tradelane(id),
    universal_service_reference varchar(8) NULL CHECK (universal_service_reference ~ '^SR\d{5}[A-Z]$')
);

DROP TABLE IF EXISTS dcsa_im_v3_0.voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.voyage (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_voyage_number varchar(50) NOT NULL,
    universal_voyage_reference varchar(5) NULL,
    service_id uuid NULL REFERENCES dcsa_im_v3_0.service (id) INITIALLY DEFERRED
);

DROP TABLE IF EXISTS dcsa_im_v3_0.mode_of_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.mode_of_transport (
    mode_of_transport_code varchar(3) PRIMARY KEY,
    mode_of_transport_name varchar(100) NULL,
    mode_of_transport_description varchar(250) NULL,
    dcsa_transport_type varchar(50) NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.booking CASCADE;
CREATE TABLE dcsa_im_v3_0.booking (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_booking_request_reference varchar(100) NOT NULL DEFAULT uuid_generate_v4()::text,
    document_status varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type(shipment_event_type_code) CHECK(document_status IN ('RECE', 'PENU', 'REJE', 'CONF','PENC', 'CANC', 'DECL', 'CMPL')),
    receipt_type_at_origin varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.receipt_delivery_type(receipt_delivery_type_code),
    delivery_type_at_destination varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.receipt_delivery_type(receipt_delivery_type_code),
    cargo_movement_type_at_origin varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.cargo_movement_type(cargo_movement_type_code),
    cargo_movement_type_at_destination varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.cargo_movement_type(cargo_movement_type_code),
    booking_request_datetime timestamp with time zone NOT NULL,
    service_contract_reference varchar(30) NOT NULL,
    payment_term_code varchar(3) NULL REFERENCES dcsa_im_v3_0.payment_term_type(payment_term_code),
    is_partial_load_allowed boolean NOT NULL,
    is_export_declaration_required boolean NOT NULL,
    export_declaration_reference varchar(35) NULL,
    is_import_license_required boolean NOT NULL,
    import_license_reference varchar(35) NULL,
    is_ams_aci_filing_required boolean NULL,
    is_destination_filing_required boolean NULL,
    contract_quotation_reference varchar(35) NULL,
    incoterms varchar(3) NULL REFERENCES dcsa_im_v3_0.incoterms(incoterms_code),
    invoice_payable_at_id uuid NULL REFERENCES dcsa_im_v3_0.location(id),
    expected_departure_date date NULL,
    expected_arrival_at_place_of_delivery_start_date date NULL CHECK ((expected_arrival_at_place_of_delivery_start_date IS NULL) OR (expected_arrival_at_place_of_delivery_end_date IS NULL) OR expected_arrival_at_place_of_delivery_start_date <= expected_arrival_at_place_of_delivery_end_date),
    expected_arrival_at_place_of_delivery_end_date date NULL,
    transport_document_type_code varchar(3) NULL REFERENCES dcsa_im_v3_0.transport_document_type(transport_document_type_code),
    transport_document_reference varchar(20) NULL,
    booking_channel_reference varchar(20) NULL,
    communication_channel_code varchar(2) NOT NULL REFERENCES dcsa_im_v3_0.communication_channel_qualifier(communication_channel_qualifier_code),
    is_equipment_substitution_allowed boolean NOT NULL,
    vessel_id uuid NULL REFERENCES dcsa_im_v3_0.vessel(id),
    declared_value_currency_code varchar(3) NULL,
    declared_value real NULL,
    place_of_issue_id uuid NULL REFERENCES dcsa_im_v3_0.location(id),
    pre_carriage_mode_of_transport_code varchar(3) NULL REFERENCES dcsa_im_v3_0.mode_of_transport(mode_of_transport_code),
    voyage_id UUID NULL REFERENCES dcsa_im_v3_0.voyage(id)
);

CREATE INDEX ON dcsa_im_v3_0.booking (id);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    booking_id uuid NOT NULL REFERENCES dcsa_im_v3_0.booking(id),
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier(id),
    carrier_booking_reference varchar(35) NOT NULL UNIQUE,
    terms_and_conditions text NULL,
    confirmation_datetime timestamp with time zone NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.iso_equipment_code CASCADE;
CREATE TABLE dcsa_im_v3_0.iso_equipment_code (
    iso_equipment_code varchar(4) PRIMARY KEY,
    iso_equipment_name varchar(35) NULL,
    iso_equipment_size_code varchar(2) NOT NULL,
    iso_equipment_type_code_a varchar(2) NOT NULL
);


DROP TABLE IF EXISTS dcsa_im_v3_0.reefer_type CASCADE;
CREATE TABLE dcsa_im_v3_0.reefer_type (
    reefer_type_code varchar(4) PRIMARY KEY,
    reefer_type_name varchar(100) NOT NULL,
    reefer_type_description varchar(255) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.active_reefer_settings CASCADE;
CREATE TABLE dcsa_im_v3_0.active_reefer_settings (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    reefer_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.reefer_type (reefer_type_code),
    is_cargo_probe_1_required boolean NOT NULL,
    is_cargo_probe_2_required boolean NOT NULL,
    is_cargo_probe_3_required boolean NOT NULL,
    is_cargo_probe_4_required boolean NOT NULL,
    is_ventilation_open boolean NOT NULL,
    is_drainholes_open boolean NOT NULL,
    is_bulb_mode boolean NOT NULL,
    is_gen_set_required boolean NOT NULL,
    is_pre_cooling_required boolean NOT NULL,
    is_cold_treatment_required boolean NOT NULL,
    is_hot_stuffing_allowed boolean NOT NULL,
    is_tracing_required boolean NOT NULL,
    is_monitoring_required boolean NOT NULL,
    product_name varchar(500) NULL,
    extra_material varchar(500) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.requested_equipment_group CASCADE;
CREATE TABLE dcsa_im_v3_0.requested_equipment_group (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    booking_id uuid NOT NULL REFERENCES dcsa_im_v3_0.booking (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    requested_equipment_iso_equipment_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.iso_equipment_code (iso_equipment_code),
    requested_equipment_units integer NOT NULL,
    confirmed_equipment_iso_equipment_code varchar(4)  NULL REFERENCES dcsa_im_v3_0.iso_equipment_code (iso_equipment_code),
    confirmed_equipment_units integer NULL,
    is_shipper_owned boolean NOT NULL DEFAULT false,
    active_reefer_settings_id uuid NULL REFERENCES dcsa_im_v3_0.active_reefer_settings (id)
);

CREATE INDEX ON dcsa_im_v3_0.requested_equipment_group (booking_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.commodity CASCADE;
CREATE TABLE dcsa_im_v3_0.commodity (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    booking_id uuid NOT NULL REFERENCES dcsa_im_v3_0.booking(id),
    commodity_type varchar(550) NOT NULL,
    hs_code varchar(10) NULL REFERENCES dcsa_im_v3_0.hs_code (hs_code),
    cargo_gross_weight real NULL,
    cargo_gross_weight_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (cargo_gross_weight_unit IN ('KGM','LBR')),
    cargo_gross_volume real NULL,
    cargo_gross_volume_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (cargo_gross_volume_unit IN ('MTQ','FTQ')),
    number_of_packages integer NULL,
    export_license_issue_date date NULL,
    export_license_expiry_date date NULL
);

CREATE INDEX ON dcsa_im_v3_0.commodity (booking_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.requested_equipment_commodity CASCADE;
CREATE TABLE dcsa_im_v3_0.requested_equipment_commodity (
    requested_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.requested_equipment_group (id),
    commodity_id uuid NOT NULL REFERENCES dcsa_im_v3_0.commodity(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_cutoff_time CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_cutoff_time (
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment(id),
    cut_off_time_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.cut_off_time(cut_off_time_code),
    cut_off_time timestamp with time zone NOT NULL,
    PRIMARY KEY (shipment_id, cut_off_time_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipping_instruction CASCADE;
CREATE TABLE dcsa_im_v3_0.shipping_instruction (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    shipping_instruction_reference  varchar(100) NOT NULL DEFAULT uuid_generate_v4()::text,
    document_status varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type(shipment_event_type_code) CHECK(document_status IN ('RECE','PENU','DRFT','PENA','APPR','ISSU','SURR','VOID')),
    is_shipped_onboard_type boolean NOT NULL,
    number_of_copies_with_charges integer NULL,
    number_of_copies_without_charges integer NULL,
    number_of_originals_with_charges integer NULL,
    number_of_originals_without_charges integer NULL,
    is_electronic boolean NOT NULL,
    is_to_order boolean NOT NULL,
    place_of_issue_id uuid NULL REFERENCES dcsa_im_v3_0.location(id),
    transport_document_type_code varchar(3) NULL REFERENCES dcsa_im_v3_0.transport_document_type(transport_document_type_code),
    displayed_name_for_place_of_receipt varchar(250) NULL,
    displayed_name_for_port_of_load varchar(250) NULL,
    displayed_name_for_port_of_discharge varchar(250) NULL,
    displayed_name_for_place_of_delivery varchar(250) NULL,
    amendment_to_transport_document_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.value_added_service_request CASCADE;
CREATE TABLE dcsa_im_v3_0.value_added_service_request (
    booking_id uuid NOT NULL REFERENCES dcsa_im_v3_0.booking(id),
    value_added_service_code varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.value_added_service(value_added_service_code)
);

CREATE INDEX ON dcsa_im_v3_0.value_added_service_request (booking_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_document_reference varchar(20) NOT NULL DEFAULT LEFT(uuid_generate_v4()::text, 20),
    place_of_issue_id uuid NULL REFERENCES dcsa_im_v3_0.location(id),
    issue_date date NULL,
    shipped_onboard_date date NULL,
    received_for_shipment_date date NULL,
    number_of_originals integer NULL,
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier(id),
    shipping_instruction_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    number_of_rider_pages integer NULL,
    issuing_party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party(id)
);

ALTER TABLE dcsa_im_v3_0.shipping_instruction
    ADD FOREIGN KEY (amendment_to_transport_document_id) REFERENCES dcsa_im_v3_0.transport_document (id);

DROP TABLE IF EXISTS dcsa_im_v3_0.carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier_clauses (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    clause_content text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_carrier_clauses (
    carrier_clause_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier_clauses (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    transport_document_id uuid NULL REFERENCES dcsa_im_v3_0.transport_document (id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_function CASCADE;
CREATE TABLE dcsa_im_v3_0.party_function (
    party_function_code varchar(3) PRIMARY KEY,
    party_function_name varchar(100) NOT NULL,
    party_function_description varchar(350) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_party CASCADE;
CREATE TABLE dcsa_im_v3_0.document_party (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party (id),
    shipping_instruction_id uuid NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    party_function varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code),
    is_to_be_notified boolean NOT NULL,
    booking_id uuid NULL REFERENCES dcsa_im_v3_0.booking(id)
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.document_party (party_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (party_function);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipping_instruction_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (booking_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.displayed_address CASCADE;
CREATE TABLE dcsa_im_v3_0.displayed_address (
    document_party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.document_party (id),
    address_line_number integer NOT NULL,
    address_line_text varchar(250) NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.displayed_address (document_party_id, address_line_number);

DROP TABLE IF EXISTS dcsa_im_v3_0.charge CASCADE;
CREATE TABLE dcsa_im_v3_0.charge (
    id varchar(100) PRIMARY KEY,
    transport_document_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_document(id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    charge_type varchar(20) NOT NULL,
    currency_amount real NOT NULL,
    currency_code varchar(3) NOT NULL,
    payment_term_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.payment_term_type(payment_term_code),
    calculation_basis varchar(50) NOT NULL,
    unit_price real NOT NULL,
    quantity real NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_version CASCADE;
CREATE TABLE dcsa_im_v3_0.document_version (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_document_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_document (id),
    document_status varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type (shipment_event_type_code),
    binary_copy bytea NOT NULL,
    document_hash text NOT NULL,
    last_modified_datetime timestamp with time zone NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment (
    equipment_reference varchar(15) PRIMARY KEY,    -- The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible. According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number and a check-digit). If a container does not comply with ISO 6346, it is suggested to follow Recommendation #2 “Container with non-ISO identification” from SMDG.
    -- Unique code for the different equipment size/type used for transporting commodities. The code is a concatenation of ISO Equipment Size Code and ISO Equipment Type Code A and follows the ISO 6346 standard.
    iso_equipment_code char(4) NULL REFERENCES dcsa_im_v3_0.iso_equipment_code (iso_equipment_code),
    tare_weight real NULL,
    weight_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code)  CHECK (weight_unit IN ('KGM','LBR'))
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.equipment (iso_equipment_code);
CREATE INDEX ON dcsa_im_v3_0.equipment (equipment_reference);


DROP TABLE IF EXISTS dcsa_im_v3_0.package_code CASCADE;
CREATE TABLE dcsa_im_v3_0.package_code (
    package_code varchar(3) PRIMARY KEY,
    package_code_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.utilized_transport_equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.utilized_transport_equipment (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    equipment_reference varchar(15) NOT NULL REFERENCES dcsa_im_v3_0.equipment (equipment_reference),
    cargo_gross_weight real NULL,
    cargo_gross_weight_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (cargo_gross_weight_unit IN ('KGM','LBR')),
    is_shipper_owned boolean NOT NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.utilized_transport_equipment (equipment_reference);

DROP TABLE IF EXISTS dcsa_im_v3_0.setpoint CASCADE;
CREATE TABLE dcsa_im_v3_0.setpoint (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    active_reefer_settings_id uuid NOT NULL REFERENCES dcsa_im_v3_0.active_reefer_settings (id),
    temperature real NULL,
    temperature_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (temperature_unit IN ('CEL','FAH')),
    humidity real NULL,
    air_exchange real NULL,
    air_exchange_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (temperature_unit IN ('MQH','2K')),
    o2 real NULL,
    co2 real NULL,
    days_prior_to_discharge real NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.consignment_item CASCADE;
CREATE TABLE dcsa_im_v3_0.consignment_item (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    description_of_goods text NOT NULL,
    shipping_instruction_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment (id),
    commodity_id uuid NOT NULL REFERENCES dcsa_im_v3_0.commodity (id)
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.consignment_item (shipping_instruction_id);
CREATE INDEX ON dcsa_im_v3_0.consignment_item (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.consignment_item (commodity_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.cargo_item CASCADE;
CREATE TABLE dcsa_im_v3_0.cargo_item (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    consignment_item_id uuid NOT NULL REFERENCES dcsa_im_v3_0.consignment_item(id),
    weight real NOT NULL,
    volume real NULL,
    weight_unit varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (weight_unit IN ('KGM','LBR')),
    volume_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CHECK (volume_unit IN ('MTQ','FTQ')),
    number_of_packages integer NOT NULL,
    package_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.package_code (package_code),
    utilized_transport_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.utilized_transport_equipment (id),
    package_name_on_bl varchar(50) NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.cargo_item (consignment_item_id);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (package_code);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (utilized_transport_equipment_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.cargo_line_item CASCADE;
CREATE TABLE dcsa_im_v3_0.cargo_line_item (
    cargo_line_item_id text NOT NULL,
    cargo_item_id uuid NOT NULL REFERENCES dcsa_im_v3_0.cargo_item (id),
    shipping_marks text NOT NULL,
    -- Choice of cargo_item_id as first member is deliberate as it enables the
    -- underlying index to be used for FK checks as well (without a separate index
    -- because Postgres currently always creates an INDEX for UNIQUE constraints)
    UNIQUE (cargo_item_id, cargo_line_item_id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.reference CASCADE;
CREATE TABLE dcsa_im_v3_0.reference (
    reference_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.reference_type (reference_type_code),
    reference_value varchar(100) NOT NULL,
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    shipping_instruction_id uuid NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    booking_id uuid NULL REFERENCES dcsa_im_v3_0.booking(id),
    consignment_item_id uuid NULL REFERENCES dcsa_im_v3_0.consignment_item(id)
);

CREATE INDEX ON dcsa_im_v3_0.reference (booking_id);
CREATE INDEX ON dcsa_im_v3_0.reference (consignment_item_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.seal_source CASCADE;
CREATE TABLE dcsa_im_v3_0.seal_source (
    seal_source_code varchar(5) PRIMARY KEY,
    seal_source_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.seal_type CASCADE;
CREATE TABLE dcsa_im_v3_0.seal_type (
    seal_type_code varchar(5) PRIMARY KEY,
    seal_type_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.seal CASCADE;
CREATE TABLE dcsa_im_v3_0.seal (
    utilized_transport_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.utilized_transport_equipment (id),
    seal_number varchar(15) NOT NULL,
    seal_source_code varchar(5) NULL REFERENCES dcsa_im_v3_0.seal_source (seal_source_code),
    seal_type_code varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.seal_type (seal_type_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.seal (utilized_transport_equipment_id);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_source_code);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_type_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location_type (
    shipment_location_type_code varchar(3) PRIMARY KEY,
    shipment_location_type_name varchar(50) NOT NULL,
    shipment_location_type_description varchar(250) NOT NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.un_location (country_code);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.location (un_location_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location (
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    booking_id uuid NULL REFERENCES dcsa_im_v3_0.booking(id),
    location_id uuid NOT NULL REFERENCES dcsa_im_v3_0.location (id),
    shipment_location_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.shipment_location_type (shipment_location_type_code),
    displayed_name varchar(250) NULL,
    event_date_time timestamp with time zone NULL, --optional datetime indicating when the event at the location takes place
    UNIQUE (location_id, shipment_location_type_code, shipment_id)
);

-- Supporting FK constraints
-- Note the omission of INDEX for "location_id" is deliberate; we rely on the implicit INDEX from the
-- UNIQUE constraint for that.
CREATE INDEX ON dcsa_im_v3_0.shipment_location (shipment_location_type_code);
CREATE INDEX ON dcsa_im_v3_0.shipment_location (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.shipment_location (booking_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_status_type CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_status_type (
    port_call_status_type_code varchar(4) NOT NULL PRIMARY KEY,
    port_call_status_type_name varchar(30) NOT NULL,
    port_call_status_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_call_reference varchar(100) NOT NULL DEFAULT uuid_generate_v4(),
    transport_call_sequence_number integer,
    facility_type_code char(4) NULL REFERENCES dcsa_im_v3_0.facility_type (facility_type_code),
    location_id uuid NULL REFERENCES dcsa_im_v3_0.location (id),
    mode_of_transport_code varchar(3) NULL REFERENCES dcsa_im_v3_0.mode_of_transport (mode_of_transport_code),
    vessel_id uuid NULL REFERENCES dcsa_im_v3_0.vessel(id),
    import_voyage_id uuid NULL, -- references on line 800
    export_voyage_id uuid NULL, -- references on line 800
    port_call_status_type_code char(4) NULL REFERENCES dcsa_im_v3_0.port_call_status_type (port_call_status_type_code),
    port_visit_reference varchar(50) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport CASCADE;
CREATE TABLE dcsa_im_v3_0.transport (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_reference varchar(50) NULL,
    transport_name varchar(100) NULL,
    load_transport_call_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    discharge_transport_call_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage (
    commercial_voyage_id uuid PRIMARY KEY,
    commercial_voyage_name text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_plan_stage_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_plan_stage_type (
    transport_plan_stage_code varchar(3) PRIMARY KEY,
    transport_plan_stage_name varchar(100) NOT NULL,
    transport_plan_stage_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_transport (
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment(id),
    transport_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport(id),
    transport_plan_stage_sequence_number integer NOT NULL,
    transport_plan_stage_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.transport_plan_stage_type(transport_plan_stage_code),
    commercial_voyage_id uuid NULL REFERENCES dcsa_im_v3_0.commercial_voyage(commercial_voyage_id),
    is_under_shippers_responsibility boolean NOT NULL,
    UNIQUE (shipment_id, transport_id, transport_plan_stage_sequence_number) -- transport_plan_stage_sequence_number must be unique together with transport and shipment
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_classifier CASCADE;
CREATE TABLE dcsa_im_v3_0.event_classifier (
    event_classifier_code varchar(3) PRIMARY KEY,
    event_classifier_name varchar(30) NOT NULL,
    event_classifier_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment_event_type (
    equipment_event_type_code varchar(4) PRIMARY KEY,
    equipment_event_type_name varchar(30) NOT NULL,
    equipment_event_type_description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.document_type (
    document_type_code varchar(3) PRIMARY KEY,
    document_type_name varchar(100) NOT NULL,
    document_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event_type (
    transport_event_type_code varchar(4) PRIMARY KEY,
    transport_event_type_name varchar(30) NOT NULL,
    transport_event_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.empty_indicator CASCADE;
CREATE TABLE dcsa_im_v3_0.empty_indicator (
    empty_indicator_code varchar(5) PRIMARY KEY
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event CASCADE;
CREATE TABLE dcsa_im_v3_0.event (
    event_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    event_classifier_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.event_classifier(event_classifier_code),
    event_created_date_time timestamp with time zone DEFAULT now() NOT NULL,
    event_date_time timestamp with time zone NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment_event CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment_event (
    equipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.equipment_event_type(equipment_event_type_code),
    equipment_reference varchar(15) NULL REFERENCES dcsa_im_v3_0.equipment (equipment_reference),
    empty_indicator_code varchar(5) NULL REFERENCES dcsa_im_v3_0.empty_indicator(empty_indicator_code),
    transport_call_id uuid NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    facility_type_code char(4) NULL REFERENCES dcsa_im_v3_0.facility_type (facility_type_code) CONSTRAINT facility_type_code CHECK(facility_type_code IN ('BOCR','CLOC','COFS','OFFD','DEPO','INTE','POTE','RAMP')),
    is_transshipment_move boolean NOT NULL default false,
    event_location_id uuid NULL REFERENCES dcsa_im_v3_0.location(id)
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.equipment_event ADD PRIMARY KEY (event_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event (
    shipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type(shipment_event_type_code),
    document_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.document_type(document_type_code),
    document_id uuid NOT NULL,
    reason varchar(250) NULL
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.shipment_event ADD PRIMARY KEY (event_id),
                                        ADD CONSTRAINT event_classifier_code_is_act CHECK (event_classifier_code = 'ACT');

DROP TABLE IF EXISTS dcsa_im_v3_0.smdg_delay_reason CASCADE;
CREATE TABLE dcsa_im_v3_0.smdg_delay_reason (
    delay_reason_code varchar(3) NOT NULL PRIMARY KEY,
    delay_reason_name varchar(100) NOT NULL,
    delay_reason_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event (
    transport_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_event_type(transport_event_type_code),
    delay_reason_code varchar(3) NULL REFERENCES dcsa_im_v3_0.smdg_delay_reason(delay_reason_code),
    change_remark varchar(250),
    transport_call_id uuid NULL REFERENCES dcsa_im_v3_0.transport_call(id)
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.transport_event ADD PRIMARY KEY (event_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement_partner CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement_partner (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier(id),
    vessel_sharing_agreement_id uuid NOT NULL REFERENCES dcsa_im_v3_0.vessel_sharing_agreement(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.service_proforma CASCADE;
CREATE TABLE dcsa_im_v3_0.service_proforma (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    service_proforma_agreed_date_time timestamp with time zone NOT NULL,
    port_call_sequence_number integer NULL,
    port_code varchar(5) NULL,
    port_terminal_call_sequence_number integer NULL,
    port_terminal_code varchar(11) NULL,
    service_id uuid NULL REFERENCES dcsa_im_v3_0.service (id)
);

ALTER TABLE dcsa_im_v3_0.transport_call
    ADD FOREIGN KEY (import_voyage_id) REFERENCES dcsa_im_v3_0.voyage (id) INITIALLY DEFERRED;
ALTER TABLE dcsa_im_v3_0.transport_call
    ADD FOREIGN KEY (export_voyage_id) REFERENCES dcsa_im_v3_0.voyage (id) INITIALLY DEFERRED;

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage_transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage_transport_call (
    transport_call_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    commercial_voyage_id uuid NOT NULL REFERENCES dcsa_im_v3_0.commercial_voyage(commercial_voyage_id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event_type (
    operations_event_type_code varchar(4) NOT NULL PRIMARY KEY,
    operations_event_type_name varchar(30) NOT NULL,
    operations_event_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_service_type CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code varchar(4) NOT NULL PRIMARY KEY,
    port_call_service_type_name varchar(30) NOT NULL,
    port_call_service_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_phase_type CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_phase_type (
     port_call_phase_type_code varchar(4) NOT NULL PRIMARY KEY,
     port_call_phase_type_name varchar(30) NOT NULL,
     port_call_phase_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event (
    publisher_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    publisher_role varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function(party_function_code) CHECK(publisher_role IN ('CA', 'AG', 'VSL', 'ATH', 'PLT', 'TR', 'TWG', 'BUK', 'LSH', 'SLU', 'SVP', 'MOR')),
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type(operations_event_type_code),
    event_location_id uuid NULL REFERENCES dcsa_im_v3_0.location (id),
    transport_call_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    port_call_service_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_service_type(port_call_service_type_code),
    facility_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.facility_type(facility_type_code) CHECK(facility_type_code IN ('PBPL', 'BRTH','ANCH')),
    delay_reason_code varchar(3) NULL REFERENCES dcsa_im_v3_0.smdg_delay_reason(delay_reason_code),
    vessel_position_id uuid NULL REFERENCES dcsa_im_v3_0.location (id),
    remark varchar(500) NULL,
    port_call_phase_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_phase_type(port_call_phase_type_code),
    vessel_draft real NULL,
    vessel_draft_unit varchar(3) NULL REFERENCES dcsa_im_v3_0.unit_of_measure(unit_of_measure_code) CONSTRAINT vessel_draft_unit CHECK (vessel_draft_unit IN ('FOT','MTR')),
    miles_to_destination_port real NULL
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.operations_event ADD PRIMARY KEY (event_id);


COMMIT;
