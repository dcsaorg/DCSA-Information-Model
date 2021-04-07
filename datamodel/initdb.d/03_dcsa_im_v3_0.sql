\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

/* Create Tables */

/* Shipment related Entities */

DROP TABLE IF EXISTS dcsa_im_v3_0.booking CASCADE;
CREATE TABLE dcsa_im_v3_0.booking (
	carrier_booking_reference varchar(35) PRIMARY KEY,
	service_type_at_origin varchar(3) NOT NULL,
	service_type_at_destination varchar(3) NOT NULL,
	shipment_term_at_origin varchar(3) NOT NULL,
	shipment_term_at_destination varchar(3) NOT NULL,
	booking_datetime timestamp with time zone NOT NULL,
	service_contract varchar(30) NOT NULL,
	commodity_type varchar(20) NOT NULL,
	cargo_gross_weight real NOT NULL,
	cargo_gross_weight_unit varchar(3) NOT NULL,
    partial_load_allowed boolean NULL,
    export_declaration_required boolean NULL,
    export_declaration_number varchar(35) NULL,
    import_license_required boolean NULL,
    import_license_number varchar(35) NULL,
    pickup_date_at_place_of_receipt date NULL,
    expected_date_of_arrival_at_final_destination varchar(35) NULL, -- TODO: DateRange...
    date_and_time_of_submission timestamp with time zone NULL,
    ams_aci_filing_required boolean NULL,
    contract_quotation_reference varchar(35) NULL,
    expected_departure_date date NULL,
    booking_channel_reference varchar(20) NULL
);
CREATE INDEX ON dcsa_im_v3_0.booking (carrier_booking_reference);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	carrier_booking_reference varchar(35) NOT NULL REFERENCES dcsa_im_v3_0.booking (carrier_booking_reference),
	collection_datetime timestamp with time zone NOT NULL,
	delivery_datetime timestamp with time zone NOT NULL,
	carrier_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.requested_equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.requested_equipment (
	carrier_booking_reference varchar(35) NOT NULL REFERENCES dcsa_im_v3_0.booking (carrier_booking_reference),
	shipment_id uuid NULL,
	requested_equipment_type varchar(4) NOT NULL,
	requested_equipment_units integer NOT NULL,
	confirmed_equipment_type varchar(4) NULL,
	confirmed_equipment_units integer NULL,
    shipper_owned_containers boolean NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.service_terms CASCADE;
CREATE TABLE dcsa_im_v3_0.service_terms (
    service_terms_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	latest_time_of_si_submission timestamp with time zone NOT NULL,
    vgm_cut_off timestamp with time zone NOT NULL,
    fcl_delivery_cut_off timestamp with time zone NOT NULL,
    lcl_delivery_cut_off timestamp with time zone NOT NULL,
    empty_container_pickup_date_and_time timestamp with time zone NULL,
    earliest_full_container_delivery_date timestamp with time zone NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_term CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_term (
	shipment_term varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.references CASCADE;
CREATE TABLE dcsa_im_v3_0.references (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	reference_type varchar(3) NOT NULL,
	reference_value varchar(100) NOT NULL,
	shipment_id uuid NULL,
	shipping_instruction_id uuid NULL
);

/* Transport Document related Entities */

DROP TABLE IF EXISTS dcsa_im_v3_0.document_version CASCADE;
CREATE TABLE dcsa_im_v3_0.document_version (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_id uuid NOT NULL,
	document_status varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type (shipment_event_type_code),
	binary_copy bytea NOT NULL,
	document_hash text NOT NULL,
	last_modified_datetime timestamp with time zone NOT NULL
);


DROP TABLE IF EXISTS dcsa_im_v3_0.shipping_instruction CASCADE;
CREATE TABLE dcsa_im_v3_0.shipping_instruction (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_type varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.transport_document_type (transport_document_type_code),
	is_shipped_onboard_type boolean NOT NULL,
	number_of_copies integer NULL,
	number_of_originals integer NULL,
	freight_payable_at uuid NOT NULL,
	is_electronic boolean NULL,
	is_charges_displayed boolean NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	place_of_issue uuid NULL,
	date_of_issue date NULL,
	onboard_date date NULL,
	received_for_shipment_date date NULL,
	document_reference_number varchar(20) NULL,
	terms_and_conditions text NULL,
	issuer varchar(4) NULL,
	shipping_instruction_id UUID NOT NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
	declared_value_currency varchar(3) NULL,
	declared_value real NULL,
	number_of_rider_pages integer NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.ebl_endorsement_chain CASCADE;
CREATE TABLE dcsa_im_v3_0.ebl_endorsement_chain (
	transport_document_id uuid NOT NULL,
	title_holder uuid NOT NULL,
	signature varchar(500) NOT NULL,
	endorsement_datetime timestamp with time zone NOT NULL,
	endorsee uuid NOT NULL,
    CONSTRAINT "pk_im_endorsement_chain" PRIMARY KEY (transport_document_id,title_holder)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document_carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document_carrier_clauses (
	carrier_clause_id uuid NOT NULL,
	transport_document_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.carrier_clauses CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier_clauses (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	clause_content text NOT NULL
);

/* Address Entity */


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


/* Party related Entities */


DROP TABLE IF EXISTS dcsa_im_v3_0.party CASCADE;
CREATE TABLE dcsa_im_v3_0.party (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	party_name varchar(100) NULL,
	tax_reference_1 varchar(20) NULL,
	tax_reference_2 varchar(20) NULL,
	public_key varchar(500) NULL,
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id),
	nmfta_code varchar(4) NULL
);


DROP TABLE IF EXISTS dcsa_im_v3_0.party_contact_details CASCADE;
CREATE TABLE dcsa_im_v3_0.party_contact_details (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(250) NULL,
    phone varchar(250) NULL,
    email varchar(250) NULL,
    fax varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.document_party CASCADE;
CREATE TABLE dcsa_im_v3_0.document_party (
    party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    shipping_instruction_id uuid NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    party_function varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code),
    party_contact_details_id uuid NULL REFERENCES dcsa_im_v3_0.party_contact_details (id),
    should_be_notified boolean NOT NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.document_party (party_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (party_function);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.document_party (shipping_instruction_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.displayed_address CASCADE;
CREATE TABLE dcsa_im_v3_0.displayed_address (
    -- Same key as document_party
    party_id uuid NOT NULL REFERENCES dcsa_im_v3_0.party (id),
    shipment_id uuid NULL REFERENCES dcsa_im_v3_0.shipment (id),
    shipping_instruction_id uuid NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
    party_function varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.party_function (party_function_code),

    address_line varchar(250) NOT NULL,
    address_line_number int NOT NULL
);
CREATE INDEX ON dcsa_im_v3_0.displayed_address (party_id, party_function);
CREATE INDEX ON dcsa_im_v3_0.displayed_address (shipment_id);
CREATE INDEX ON dcsa_im_v3_0.displayed_address (shipping_instruction_id);


DROP TABLE IF EXISTS dcsa_im_v3_0.carrier CASCADE;
CREATE TABLE dcsa_im_v3_0.carrier (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_name varchar(100),
    smdg_code varchar(3) NULL,
    nmfta_code varchar(4) NULL
);


/* Charges related Entities */


DROP TABLE IF EXISTS dcsa_im_v3_0.charges CASCADE;
CREATE TABLE dcsa_im_v3_0.charges (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_id uuid NULL,
	shipment_id uuid NULL,
	charge_type varchar(20) NULL,
	currency_amount real NULL,
	currency_code varchar(3) NULL,
	payment_term varchar(3) NULL,
	calculation_basis varchar(50) NULL,
	unit_price real NULL,
	quantity real NULL
);


/* Equipment related Entities */

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment (
	equipment_reference varchar(15) PRIMARY KEY,	-- The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible. According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number and a check-digit). If a container does not comply with ISO 6346, it is suggested to follow Recommendation #2 “Container with non-ISO identification” from SMDG.
	-- Unique code for the different equipment size/type used for transporting commodities. The code is a concatenation of ISO Equipment Size Code and ISO Equipment Type Code A and follows the ISO 6346 standard.
	iso_equipment_code char(4) NULL REFERENCES dcsa_im_v3_0.iso_equipment_code (iso_equipment_code),
	is_shipper_owned boolean NOT NULL DEFAULT false,
	tare_weight real NULL,
	weight_unit varchar(3) NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.equipment (iso_equipment_code);

/* Stuffing related Entities */


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
	shipping_instruction_id uuid NULL REFERENCES dcsa_im_v3_0.shipping_instruction (id),
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

DROP TABLE IF EXISTS dcsa_im_v3_0.seal CASCADE;
CREATE TABLE dcsa_im_v3_0.seal (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	shipment_equipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment_equipment (id),
	seal_number varchar(15) NOT NULL,
	seal_source varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.seal_source (seal_source_code),
	seal_type varchar(5) NOT NULL REFERENCES dcsa_im_v3_0.seal_type (seal_type_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.seal (shipment_equipment_id);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_source);
CREATE INDEX ON dcsa_im_v3_0.seal (seal_type);


/* Location related Entities */

DROP TABLE IF EXISTS dcsa_im_v3_0.un_location CASCADE;
CREATE TABLE dcsa_im_v3_0.un_location (
	un_location_code char(5) PRIMARY KEY,
	un_location_name varchar(100) NULL,
	location_code char(3) NULL,
	country_code char(2) NULL REFERENCES dcsa_im_v3_0.country (country_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.un_location (country_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.location CASCADE;
CREATE TABLE dcsa_im_v3_0.location (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    location_name varchar(100) NULL,
    address_id uuid NULL REFERENCES dcsa_im_v3_0.address (id),
	latitude varchar(10) NULL,
	longitude varchar(11) NULL,
	un_location_code char(5) NULL REFERENCES dcsa_im_v3_0.un_location (un_location_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_im_v3_0.location (un_location_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location (
	shipment_id uuid NOT NULL REFERENCES dcsa_im_v3_0.shipment (id),
	location_id uuid NOT NULL REFERENCES dcsa_im_v3_0.location (id),
	location_type varchar(3) NOT NULL REFERENCES dcsa_im_v3_0.shipment_location_type (location_type_code),
	displayed_name varchar(250),
	UNIQUE (location_id, location_type, shipment_id)
);

-- Supporting FK constraints
-- Note the omission of INDEX for "location_id" is deliberate; we rely on the implicit INDEX from the
-- UNIQUE constraint for that.
CREATE INDEX ON dcsa_im_v3_0.shipment_location (location_type);
CREATE INDEX ON dcsa_im_v3_0.shipment_location (shipment_id);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility CASCADE;
CREATE TABLE dcsa_im_v3_0.facility (
    facility_code varchar(11) PRIMARY KEY,
    facility_name varchar(100) NULL,
    code_list_provider_code varchar(6) NULL,
    code_list_provider varchar(8) NULL,
    un_location_code varchar(5) NULL,
    latitude varchar(10) NULL,
    longitude varchar(11) NULL,
    address varchar(250) NULL,
    facility_type_code varchar(4) NULL
);

/* Transport related Entities */


DROP TABLE IF EXISTS dcsa_im_v3_0.vessel CASCADE;
CREATE TABLE dcsa_im_v3_0.vessel (
	vessel_imo_number varchar(7) PRIMARY KEY,
	vessel_name varchar(35) NULL,
	vessel_flag char(2) NULL,
	vessel_call_sign_number varchar(10) NULL,
	vessel_operator_carrier_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport CASCADE;
CREATE TABLE dcsa_im_v3_0.transport (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_reference varchar(50) NULL,
	transport_name varchar(100) NULL,
	mode_of_transport varchar(3) NULL REFERENCES dcsa_im_v3_0.mode_of_transport (mode_of_transport_code),
	load_transport_call_id uuid NOT NULL,
	discharge_transport_call_id uuid NOT NULL,
	vessel varchar(7) NULL REFERENCES dcsa_im_v3_0.vessel (vessel_imo_number)
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_transport (
	shipment_id uuid NOT NULL,
	transport_id uuid NOT NULL,
	sequence_number integer NOT NULL,
	commercial_voyage_id uuid,
	is_under_shippers_responsibility boolean NOT NULL,
	UNIQUE (shipment_id, transport_id, sequence_number) -- sequence_number must be unique together with transport and shipment
);


/* Events related Entities */

DROP TABLE IF EXISTS dcsa_im_v3_0.event_classifier CASCADE;
CREATE TABLE dcsa_im_v3_0.event_classifier (
    event_classifier_code char(3) PRIMARY KEY, -- Code for the event classifier, either PLN, ACT or EST.
    event_classifier_name varchar(30) NULL, -- Name of the classifier.
    event_classifier_description varchar(250) NULL -- The description of the event classifier. 
);

DROP TABLE IF EXISTS dcsa_im_v3_0.event CASCADE;
CREATE TABLE dcsa_im_v3_0.event (
    event_id uuid DEFAULT uuid_generate_v4() PRIMARY KEY, -- Unique identifier for the event captured.
    event_created_date_time timestamp with time zone NOT NULL DEFAULT now(), -- The date and time when the event record was created and stored.
    event_type text NOT NULL,
    event_classifier_code varchar(3) NOT NULL, -- Code for the event classifier telling whether the information relates to an actual or future event.
    event_date_time timestamp with time zone NOT NULL -- Indicating the date and time of when the event occurred or will occur.
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment_event CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment_event (
    equipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.equipment_event_type(equipment_event_type_code),
    equipment_reference varchar(15),
    empty_indicator_code varchar(5) NOT NULL,
    transport_call_id uuid NOT NULL
) INHERITS (dcsa_im_v3_0.event);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event (
    shipment_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.shipment_event_type(shipment_event_type_code),
    shipment_information_type_code varchar(3) NOT NULL,
    document_id varchar(50) NOT NULL,
    reason varchar(100)
) INHERITS (dcsa_im_v3_0.event);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event (
    transport_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.transport_event_type(transport_event_type_code),
    delay_reason_code varchar(3),
    change_remark varchar(250), -- Free text description of the reason for the change in schedule. 
    transport_call_id uuid NOT NULL
) INHERITS (dcsa_im_v3_0.event);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event (
    operations_event_type_code varchar(4) NOT NULL REFERENCES dcsa_im_v3_0.operations_event_type(operations_event_type_code), -- The code to identify the type of event that is related to the operation.
    publisher varchar(50) NOT NULL, -- The publisher (source) of the event
    publisher_role varchar(3) NOT NULL, -- The party function code of the publisher
    transport_call_id uuid NOT NULL,
    event_location uuid NOT NULL REFERENCES dcsa_im_v3_0.location(id), -- The location where the event takes place.
    port_call_service_type_code varchar(4) REFERENCES dcsa_im_v3_0.port_call_service_type(port_call_service_type_code), -- The type of the service provided in the port call.
    facility_type_code varchar(4) NULL, -- Four character code to identify the specific type of facility.
    delay_reason_code varchar(3),-- SMDG code indicating the reason for a delay
    change_remark varchar(250) -- Free text description of the reason for the change in schedule.
) INHERITS (dcsa_im_v3_0.event);

ALTER TABLE dcsa_im_v3_0.operations_event
ADD FOREIGN KEY (event_classifier_code)
REFERENCES dcsa_im_v3_0.event_classifier (event_classifier_code);

DROP TABLE IF EXISTS dcsa_im_v3_0.event_subscription CASCADE;
CREATE TABLE dcsa_im_v3_0.event_subscription (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    callback_url text NOT NULL,
    event_type text, --This field must be able to contain multiple event types. Currently it does not.
    booking_reference varchar(35),
    transport_document_id varchar(20),
    transport_document_type text,
    equipment_reference varchar(15),
    schedule_id uuid NULL,
    transport_call_id uuid NULL
    );

--Helper table in order to filter Events on schedule_id
DROP TABLE IF EXISTS dcsa_im_v3_0.schedule CASCADE;
CREATE TABLE dcsa_im_v3_0.schedule (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    vessel_operator_carrier_code varchar(10) NOT NULL,
    vessel_operator_carrier_code_list_provider text NOT NULL,
    vessel_partner_carrier_code varchar(10) NOT NULL,
    vessel_partner_carrier_code_list_provider text,
    start_date date,
    date_range text
);

/* Vessel Sharing Agreement related Entities */

/* Service related Entities */

/* Transport Journey related Entities */


DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_call_sequence_number integer,
	facility_code varchar(11) NULL REFERENCES dcsa_im_v3_0.facility (facility_code),
	facility_type_code char(4) NULL REFERENCES dcsa_im_v3_0.facility_type (facility_type_code),
	other_facility varchar(50) NULL,
	location_id uuid NULL
);

ALTER TABLE dcsa_im_v3_0.transport
ADD FOREIGN KEY (load_transport_call_id) REFERENCES dcsa_im_v3_0.transport_call(id);
ALTER TABLE dcsa_im_v3_0.transport
ADD FOREIGN KEY (discharge_transport_call_id) REFERENCES dcsa_im_v3_0.transport_call(id);

DROP TABLE IF EXISTS dcsa_im_v3_0.voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.voyage (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	carrier_voyage_number varchar(50) NULL,
	service_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_call_voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_call_voyage (
	voyage_id uuid NOT NULL,
	transport_call_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage (
	commercial_voyage_id uuid PRIMARY KEY,
	commercial_voyage_name text NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.commercial_voyage_transport_call CASCADE;
CREATE TABLE dcsa_im_v3_0.commercial_voyage_transport_call (
	transport_call_id uuid NOT NULL,
	commercial_voyage_id uuid NOT NULL
);

COMMIT;
