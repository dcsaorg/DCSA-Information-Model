\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

DROP TABLE IF EXISTS dcsa_im_v3_0.reference_type CASCADE;
CREATE TABLE dcsa_im_v3_0.reference_type (
    reference_type_code varchar(3) PRIMARY KEY,
    reference_name varchar(100) NOT NULL,
    reference_description varchar(200) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.receipt_delivery_type CASCADE;
CREATE TABLE dcsa_im_v3_0.receipt_delivery_type (
    receipt_delivery_type varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.cargo_movement_type CASCADE;
CREATE TABLE dcsa_im_v3_0.cargo_movement_type (
    cargo_movement_type varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
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
    id varchar(100) DEFAULT uuid_generate_v4()::text PRIMARY KEY,
    location_name varchar(100) NULL,
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id),
    latitude varchar(10) NULL,
    longitude varchar(11) NULL,
    un_location_code char(5) NULL REFERENCES dcsa_im_v3_0.un_location (un_location_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility_type CASCADE;
CREATE TABLE dcsa_im_v3_0.facility_type (
    facility_type_code varchar(4) PRIMARY KEY,
    facility_type_name varchar(100) NULL,
    facility_type_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility CASCADE;
CREATE TABLE dcsa_im_v3_0.facility (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    facility_name varchar(100) NULL,
    un_location_code varchar(5) NULL REFERENCES dcsa_im_v3_0.un_location (un_location_code), -- The UN Locode prefixing the BIC / SMDG code
    facility_bic_code varchar(4) NULL, -- suffix uniquely identifying the facility when prefixed with the UN Locode
    facility_smdg_code varchar(6) NULL, -- suffix uniquely identifying the facility when prefixed with the UN Locode
    location_id varchar(100) REFERENCES dcsa_im_v3_0.location(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.carrier CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_name varchar(100),
    smdg_code varchar(3) NULL,
    nmfta_code varchar(4) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_contact_details CASCADE;
CREATE TABLE dcsa_im_v3_0.party_contact_details (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(100) NULL,
    phone varchar(30) NULL,
    email varchar(100) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party CASCADE;
CREATE TABLE dcsa_im_v3_0.party (
    id varchar(100) DEFAULT uuid_generate_v4()::text PRIMARY KEY,
    party_name varchar(100) NULL,
    tax_reference_1 varchar(20) NULL,
    tax_reference_2 varchar(20) NULL,
    public_key varchar(500) NULL,
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id),
    party_contact_details_id uuid NULL REFERENCES dcsa_im_v3_0.party_contact_details(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.code_list_responsible_agency CASCADE;
CREATE TABLE dcsa_im_v3_0.code_list_responsible_agency (
    code_list_responsible_agency_code varchar(3) NOT NULL PRIMARY KEY,
    code_list_responsible_agency_name varchar(100) NOT NULL,
    code_list_responsible_agency_description varchar(300)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_identifying_code CASCADE;
CREATE TABLE dcsa_im_v3_0.party_identifying_code (
    code_list_responsible_agency_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.code_list_responsible_agency(code_list_responsible_agency_code),
    party_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    party_code varchar(100) NOT NULL,
    code_list_name varchar(100)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.booking CASCADE;
CREATE TABLE dcsa_im_v3_0.booking (
    carrier_booking_reference varchar(35) PRIMARY KEY,
    receipt_delivery_type_at_origin varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.receipt_delivery_type(receipt_delivery_type),
    receipt_delivery_type_at_destination varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.receipt_delivery_type(receipt_delivery_type),
    cargo_movement_type_at_origin varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.cargo_movement_type(cargo_movement_type),
    cargo_movement_type_at_destination varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.cargo_movement_type(cargo_movement_type),
    booking_request_datetime timestamp with time zone NOT NULL,
    service_contract varchar(30) NOT NULL,
    cargo_gross_weight real NOT NULL,
    cargo_gross_weight_unit varchar(3) NOT NULL,
    commodity_type varchar(20) NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.booking (carrier_booking_reference);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_booking_reference varchar(35) NOT NULL REFERENCES dcsa_im_v3_0.booking (carrier_booking_reference),
    collection_datetime timestamp with time zone NOT NULL,
    delivery_datetime timestamp with time zone NOT NULL,
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.requested_equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.requested_equipment (
    carrier_booking_reference varchar(35) NOT NULL REFERENCES dcsa_im_v3_0.booking (carrier_booking_reference),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    requested_equipment_type varchar(4) NOT NULL,
    requested_equipment_units integer NOT NULL,
    confirmed_equipment_type varchar(4) NULL,
    confirmed_equipment_units integer NULL,
    is_shipper_owned boolean NOT NULL DEFAULT false
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_cutoff_times CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_cutoff_times (
    service_terms_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    latest_time_of_si_submission timestamp with time zone NOT NULL,
    vgm_cut_off timestamp with time zone NOT NULL,
    fcl_delivery_cut_off timestamp with time zone NOT NULL,
    lcl_delivery_cut_off timestamp with time zone NOT NULL,
    empty_container_pickup_date_and_time timestamp with time zone NULL,
    earliest_full_container_delivery_date timestamp with time zone NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event_type (
    shipment_event_type_code varchar(4) PRIMARY KEY,
    shipment_event_type_name varchar(30) NOT NULL,
    shipment_event_type_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document_type (
    transport_document_type_code varchar(3) PRIMARY KEY,
    transport_document_type_name varchar(20) NULL,
    transport_document_type_description varchar(500) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipping_instruction CASCADE;
CREATE TABLE dcsa_im_v3_0.shipping_instruction (
    id varchar(100) DEFAULT uuid_generate_v4()::text PRIMARY KEY,
    transport_document_type varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.transport_document_type (transport_document_type_code),
    is_shipped_onboard_type boolean NOT NULL,
    number_of_copies integer NULL,
    number_of_originals integer NULL,
    invoice_payable_at varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.location(id),
    is_electronic boolean NULL,
    are_charges_displayed boolean NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.references CASCADE;
CREATE TABLE dcsa_im_v3_0.references (
    reference_type varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.reference_type (reference_type_code),
    reference_value varchar(100) NOT NULL,
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    shipping_instruction_id varchar(100) NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document (
    transport_document_reference varchar(20) PRIMARY KEY,
    place_of_issue varchar(100) NULL REFERENCES dcsa_im_v3_0.location(id),
    issue_date date NULL,
    shipped_onboard_date date NULL,
    received_for_shipment_date date NULL,
    terms_and_conditions text NULL,
    issuer uuid NULL REFERENCES dcsa_im_v3_0.carrier(id),
    shipping_instruction_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    declared_value_currency varchar(3) NULL,
    declared_value real NULL,
    number_of_rider_pages integer NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.ebl_endorsement_chain CASCADE;
CREATE TABLE dcsa_im_v3_0.ebl_endorsement_chain (
    transport_document_reference varchar(20) NOT NULL REFERENCES dcsa_im_v3_0.transport_document (transport_document_reference),
    title_holder varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    signature varchar(500) NOT NULL,
    endorsement_datetime timestamp with time zone NOT NULL,
    endorsee varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.party(id),
    CONSTRAINT "pk_im_endorsement_chain" PRIMARY KEY (transport_document_reference,title_holder)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier_clauses (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    clause_content text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document_carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document_carrier_clauses (
    carrier_clause_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier_clauses (id),
    transport_document_reference varchar(20) NOT NULL REFERENCES dcsa_im_v3_0.transport_document (transport_document_reference)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_function CASCADE;
CREATE TABLE dcsa_im_v3_0.party_function (
    party_function_code varchar(3) PRIMARY KEY,
    party_function_name varchar(100) NOT NULL,
    party_function_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_party CASCADE;
CREATE TABLE dcsa_im_v3_0.document_party (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    party_id varchar(100) NOT NULL DEFAULT uuid_generate_v4()::text REFERENCES dcsa_im_v3_0.party (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    shipping_instruction_id varchar(100) NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    party_function varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code),
    is_to_be_notified boolean NOT NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.document_party (party_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (party_function);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipping_instruction_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.displayed_address CASCADE;
CREATE TABLE dcsa_im_v3_0.displayed_address (
    document_party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.document_party (id),
    address_line varchar(250) NOT NULL,
    address_line_number int NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.displayed_address (document_party_id, address_line_number);

DROP TABLE IF EXISTS dcsa_im_v3_0.charge_type CASCADE;
CREATE TABLE dcsa_im_v3_0.charge_type (
    charge_type_code varchar(3) NOT NULL PRIMARY KEY,
    charge_type_name varchar(20) NOT NULL,
    charge_type_description varchar(100) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.charges CASCADE;
CREATE TABLE dcsa_im_v3_0.charges (
    id varchar(100) PRIMARY KEY,
    transport_document_reference varchar(20) NOT NULL REFERENCES dcsa_im_v3_0.transport_document(transport_document_reference),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    charge_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.charge_type(charge_type_code),
    currency_amount real NULL,
    currency_code varchar(3) NULL,
    payment_term varchar(3) NULL,
    calculation_basis varchar(50) NULL,
    unit_price real NULL,
    quantity real NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_version CASCADE;
CREATE TABLE dcsa_im_v3_0.document_version (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_document_reference varchar(20) NOT NULL REFERENCES dcsa_im_v3_0.transport_document (transport_document_reference),
    document_status varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type (shipment_event_type_code),
    binary_copy bytea NOT NULL,
    document_hash text NOT NULL,
    last_modified_datetime timestamp with time zone NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.iso_equipment_code CASCADE;
CREATE TABLE dcsa_im_v3_0.iso_equipment_code (
    iso_equipment_code varchar(4) PRIMARY KEY,
    iso_equipment_name varchar(35) NULL,
    iso_equipment_size_code varchar(2) NULL,
    iso_equipment_type_code_a varchar(2) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment (
    equipment_reference varchar(15) PRIMARY KEY,    -- The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible. According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number and a check-digit). If a container does not comply with ISO 6346, it is suggested to follow Recommendation #2 “Container with non-ISO identification” from SMDG.
    -- Unique code for the different equipment size/type used for transporting commodities. The code is a concatenation of ISO Equipment Size Code and ISO Equipment Type Code A and follows the ISO 6346 standard.
    iso_equipment_code char(4) NULL REFERENCES dcsa_im_v3_0.iso_equipment_code (iso_equipment_code),
    tare_weight real NULL,
    weight_unit varchar(3) NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.equipment (iso_equipment_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.package_code CASCADE;
CREATE TABLE dcsa_im_v3_0.package_code (
    package_code varchar(3) PRIMARY KEY,
    description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_equipment (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment (id),
    equipment_reference varchar(15) NOT NULL REFERENCES dcsa_im_v3_0.equipment (equipment_reference),
    cargo_gross_weight real NULL,
    cargo_gross_weight_unit varchar(3) NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.shipment_equipment (equipment_reference);
CREATE INDEX ON dcsa_im_v3_0.shipment_equipment (shipment_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.active_reefer_settings CASCADE;
CREATE TABLE dcsa_im_v3_0.active_reefer_settings (
    shipment_equipment_id uuid PRIMARY KEY REFERENCES dcsa_im_v3_0.shipment_equipment (id),
    temperature_min real NULL,
    temperature_max real NULL,
    temperature_unit varchar(3) NULL,
    humidity_min real NULL,
    humidity_max real NULL,
    ventilation_min real NULL,
    ventilation_max real NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.hs_code CASCADE;
CREATE TABLE dcsa_im_v3_0.hs_code (
    hs_code varchar(10) PRIMARY KEY,
    code_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.cargo_item CASCADE;
CREATE TABLE dcsa_im_v3_0.cargo_item (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment (id),
    description_of_goods text NOT NULL,
    hs_code varchar(10) NOT NULL REFERENCES dcsa_im_v3_0.hs_code (hs_code),
    weight real NULL,
    volume real NULL,
    weight_unit varchar(3) NULL,
    volume_unit varchar(3) NULL,
    number_of_packages integer NULL,
    shipping_instruction_id varchar(100) NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    package_code varchar(3) NULL REFERENCES dcsa_im_v3_0.package_code (package_code),
    shipment_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment_equipment (id)
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.cargo_item (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (hs_code);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (shipping_instruction_id);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (package_code);
CREATE INDEX ON dcsa_im_v3_0.cargo_item (shipment_equipment_id);

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

DROP TABLE IF EXISTS dcsa_im_v3_0.seal_source CASCADE;
CREATE TABLE dcsa_im_v3_0.seal_source (
    seal_source_code varchar(5) PRIMARY KEY,
    description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.seal_type CASCADE;
CREATE TABLE dcsa_im_v3_0.seal_type (
    seal_type_code varchar(5) PRIMARY KEY,
    description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.seal CASCADE;
CREATE TABLE dcsa_im_v3_0.seal (
    shipment_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment_equipment (id),
    seal_number varchar(15) NOT NULL,
    seal_source varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.seal_source (seal_source_code),
    seal_type varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.seal_type (seal_type_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.seal (shipment_equipment_id);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_source);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_type);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location_type (
    location_type_code varchar(3) PRIMARY KEY,
    location_type_description varchar(50) NOT NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.un_location (country_code);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.location (un_location_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location (
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment (id),
    location_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.location (id),
    location_type varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.shipment_location_type (location_type_code),
    displayed_name varchar(250),
    event_date_time timestamp with time zone NULL, --optional datetime indicating when the event at the location takes place
    UNIQUE (location_id, location_type, shipment_id)
);

-- Supporting FK constraints
-- Note the omission of INDEX for "location_id" is deliberate; we rely on the implicit INDEX from the
-- UNIQUE constraint for that.
CREATE INDEX ON dcsa_im_v3_0.shipment_location (location_type);
CREATE INDEX ON dcsa_im_v3_0.shipment_location (shipment_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.mode_of_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.mode_of_transport (
    mode_of_transport_code varchar(3) PRIMARY KEY,
    mode_of_transport_name varchar(100) NULL,
    mode_of_transport_description varchar(250) NULL,
    dcsa_transport_type varchar(50) NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel (
    vessel_imo_number varchar(7) PRIMARY KEY,
    vessel_name varchar(35) NULL,
    vessel_flag char(2) NULL,
    vessel_call_sign_number varchar(10) NULL,
    vessel_operator_carrier_id uuid REFERENCES dcsa_im_v3_0.carrier (id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call (
    id varchar(100) DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_call_sequence_number integer,
    facility_id uuid NULL REFERENCES dcsa_im_v3_0.facility (id),
    facility_type_code char(4) NULL REFERENCES dcsa_im_v3_0.facility_type (facility_type_code),
    other_facility varchar(50) NULL, -- Free text field used if the facility cannot be identified
    location_id varchar(100) NULL REFERENCES dcsa_im_v3_0.location (id),
    mode_of_transport varchar(3) NULL REFERENCES dcsa_im_v3_0.mode_of_transport (mode_of_transport_code),
    vessel_imo_number varchar(7) NULL REFERENCES dcsa_im_v3_0.vessel (vessel_imo_number)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport CASCADE;
CREATE TABLE dcsa_im_v3_0.transport (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    transport_reference varchar(50) NULL,
    transport_name varchar(100) NULL,
    load_transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    discharge_transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage (
    commercial_voyage_id uuid PRIMARY KEY,
    commercial_voyage_name text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_transport (
    shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment(id),
    transport_id uuid NOT NULL REFERENCES dcsa_im_v3_0.transport(id),
    transport_plan_stage_sequence_number integer NOT NULL,
    transport_plan_stage varchar(50) NULL,
    commercial_voyage_id uuid NULL REFERENCES dcsa_im_v3_0.commercial_voyage(commercial_voyage_id),
    is_under_shippers_responsibility boolean NOT NULL,
    UNIQUE (shipment_id, transport_id, sequence_number) -- sequence_number must be unique together with transport and shipment
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_classifier CASCADE;
CREATE TABLE dcsa_im_v3_0.event_classifier (
    event_classifier_code varchar(3) PRIMARY KEY,
    event_classifier_name varchar(30) NOT NULL,
    event_classifier_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment_event_type (
    equipment_event_type_code varchar(4) PRIMARY KEY,
    equipment_event_type_name varchar(30) NOT NULL,
    equipment_event_type_description varchar(300) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.document_type (
    document_type_code varchar(3) PRIMARY KEY,
    document_type_name varchar(100) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event_type (
    transport_event_type_code varchar(4) PRIMARY KEY,
    transport_event_type_name varchar(30) NOT NULL,
    transport_event_type_description varchar(250) NULL
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
    equipment_reference varchar(15) NOT NULL REFERENCES dcsa_im_v3_0.equipment (equipment_reference),
    empty_indicator_code varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.empty_indicator(empty_indicator_code),
    transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    equipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.equipment_event_type(equipment_event_type_code)
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.equipment_event ADD PRIMARY KEY (event_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event (
    document_id varchar(100) NOT NULL,
    shipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type(shipment_event_type_code),
    document_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.document_type(document_type_code),
    reason varchar(100) NULL
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.shipment_event ADD PRIMARY KEY (event_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.smdg_delay_reason CASCADE;
CREATE TABLE dcsa_im_v3_0.smdg_delay_reason (
    delay_reason_code varchar(3) NOT NULL PRIMARY KEY,
    delay_reason_name varchar(100) NOT NULL,
    delay_reason_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event (
    delay_reason_code varchar(3) NULL REFERENCES dcsa_im_v3_0.smdg_delay_reason(delay_reason_code),
    change_remark varchar(250),
    transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    transport_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_event_type(transport_event_type_code)
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.transport_event ADD PRIMARY KEY (event_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement_type CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement_type (
    vessel_sharing_agreement_type_code varchar(3) NOT NULL PRIMARY KEY,
    vessel_sharing_agreement_type_name varchar(50) NULL,
    vessel_sharing_agreement_type_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    vessel_sharing_agreement_name varchar(50) NULL,
    vessel_sharing_agreement_type_code varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.vessel_sharing_agreement_type(vessel_sharing_agreement_type_code)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.vessel_sharing_agreement_partner CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel_sharing_agreement_partner (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier(id),
    vessel_sharing_agreement_id uuid NOT NULL REFERENCES dcsa_im_v3_0.vessel_sharing_agreement(id)
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
    carrier_id uuid NOT NULL REFERENCES dcsa_im_v3_0.carrier (id),
    carrier_service_code varchar(5),
    carrier_service_name varchar(50),
    tradelane_id varchar(8) NULL REFERENCES dcsa_im_v3_0.tradelane(id)
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

DROP TABLE IF EXISTS dcsa_im_v3_0.voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.voyage (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_voyage_number varchar(50) NULL,
    service_id uuid NULL REFERENCES dcsa_im_v3_0.service (id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call_voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call_voyage (
    voyage_id uuid NOT NULL REFERENCES dcsa_im_v3_0.voyage(id),
    transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    UNIQUE (voyage_id, transport_call_id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage_transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage_transport_call (
    transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    commercial_voyage_id uuid NOT NULL REFERENCES dcsa_im_v3_0.commercial_voyage(commercial_voyage_id)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event_type (
    operations_event_type_code varchar(4) NOT NULL PRIMARY KEY,
    operations_event_type_name varchar(30) NOT NULL,
    operations_event_type_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_service_type CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code varchar(4) NOT NULL PRIMARY KEY,
    port_call_service_type_name varchar(30) NOT NULL,
    port_call_service_type_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event (
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type(operations_event_type_code),
    transport_call_id varchar(100) NOT NULL REFERENCES dcsa_im_v3_0.transport_call(id),
    publisher varchar(100) NULL,
    publisher_role varchar(3) NULL,
    port_call_service_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.port_call_service_type(port_call_service_type_code),
    event_location varchar(100) NULL REFERENCES dcsa_im_v3_0.location (id),
    facility_type_code varchar(4) NULL REFERENCES dcsa_im_v3_0.facility_type(facility_type_code),
    delay_reason_code varchar(3) NULL REFERENCES dcsa_im_v3_0.smdg_delay_reason(delay_reason_code),
    vessel_position varchar(100) NULL REFERENCES dcsa_im_v3_0.location (id),
    remark varchar(500) NULL
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.operations_event ADD PRIMARY KEY (event_id);

COMMIT;
