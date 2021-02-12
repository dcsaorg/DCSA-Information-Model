\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

/* Create Tables */


/* Shipment related Entities */

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	carrier_booking_reference varchar(35) NOT NULL,
	collection_datetime timestamp with time zone NOT NULL,
	delivery_datetime timestamp with time zone NOT NULL,
	carrier_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.booking CASCADE;
CREATE TABLE dcsa_ebl_v1_0.booking (
	carrier_booking_reference varchar(35) PRIMARY KEY,
	service_type_at_origin varchar(3) NOT NULL,
	service_type_at_destination varchar(3) NOT NULL,
	shipment_term_at_origin varchar(3) NOT NULL,
	shipment_term_at_destination varchar(3) NOT NULL,
	booking_datetime timestamp with time zone NOT NULL,
	service_contract varchar(30) NOT NULL,
	commodity_type varchar(20) NOT NULL,
	cargo_gross_weight real NOT NULL,
	cargo_gross_weight_unit varchar(3) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.requested_equipment CASCADE;
CREATE TABLE dcsa_ebl_v1_0.requested_equipment (
	carrier_booking_reference varchar(35) NOT NULL,
	shipment_id uuid NULL,
	requested_equipment_type varchar(4) NOT NULL,
	requested_equipment_units integer NOT NULL,
	confirmed_equipment_type varchar(4) NULL,
	confirmed_equipment_units integer NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.service_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.service_type (
	service_type varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment_term CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment_term (
	shipment_term varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.references CASCADE;
CREATE TABLE dcsa_ebl_v1_0.references (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	reference_type varchar(3) NOT NULL,
	reference_value varchar(100) NOT NULL,
	shipment_id uuid NULL,
	shipping_instruction_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.reference_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.reference_type (
	reference_type_code varchar(3) PRIMARY KEY,
	reference_name varchar(100) NOT NULL,
	reference_description varchar(200) NOT NULL
);


/* Transport Document related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport_document CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport_document (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	place_of_issue uuid NULL,
	date_of_issue date NULL,
	onboard_date date NULL,
	received_for_shipment_date date NULL,
	document_reference_number varchar(20) NULL,
	terms_and_conditions text NULL,
	issuer varchar(4) NULL,
	shipping_instruction_id UUID NULL,
	declared_value_currency varchar(3) NULL,
	declared_value real NULL,
	number_of_rider_pages integer NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.document_version CASCADE;
CREATE TABLE dcsa_ebl_v1_0.document_version (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_id uuid NOT NULL,
	document_status varchar(4) NOT NULL,
	binary_copy bytea NOT NULL,
	document_hash text NOT NULL,
	last_modified_datetime timestamp with time zone NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipping_instruction CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipping_instruction (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_type varchar(3) NULL,
	is_shipped_onboard_type boolean NOT NULL,
	number_of_copies integer NULL,
	number_of_originals integer NULL,
	precarriage_mode_of_transport_under_shippers_responsibilty varchar(3) NULL,
	freight_payable_at uuid NOT NULL,
	is_part_load boolean NULL,
	number_of_partloads integer NULL,
	is_electronic boolean NULL,
	callback_url text NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.ebl_endorsement_chain CASCADE;
CREATE TABLE dcsa_ebl_v1_0.ebl_endorsement_chain (
	transport_document_id uuid NOT NULL,
	title_holder uuid NOT NULL,
	signature varchar(500) NOT NULL,
	endorsement_datetime timestamp with time zone NOT NULL,
	endorsee uuid NOT NULL,
    CONSTRAINT "pk_ebl_endorsement_chain" PRIMARY KEY (transport_document_id,title_holder)
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport_document_carrier_clauses CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport_document_carrier_clauses (
	carrier_clause_id uuid NOT NULL,
	transport_document_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.carrier_clauses CASCADE;
CREATE TABLE dcsa_ebl_v1_0.carrier_clauses (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	clause_content text NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport_document_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport_document_type (
	transport_document_type_code varchar(3) PRIMARY KEY,
	transport_document_type_name varchar(20) NULL,
	transport_document_type_description varchar(200) NULL
);


/* Party related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.party CASCADE;
CREATE TABLE dcsa_ebl_v1_0.party (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	party_name varchar(100) NULL,
	tax_reference_1 varchar(20) NULL,
	tax_reference_2 varchar(20) NULL,
	public_key varchar(500) NULL,
	street_name varchar(100) NULL,
	street_number varchar(50) NULL,
	floor varchar(50) NULL,
	postal_code varchar(10) NULL,
	city_name varchar(65) NULL,
	state_region varchar(65) NULL,
	country varchar(75) NULL,
	nmfta_code varchar(4) NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.party_function CASCADE;
CREATE TABLE dcsa_ebl_v1_0.party_function (
    party_function_code varchar(3) PRIMARY KEY,
    party_function_name varchar(100) NOT NULL,
    party_function_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.document_party CASCADE;
CREATE TABLE dcsa_ebl_v1_0.document_party (
    party_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.party (id),
    shipment_id uuid NULL REFERENCES dcsa_ebl_v1_0.shipment (id),
    shipping_instruction_id uuid NULL REFERENCES dcsa_ebl_v1_0.shipping_instruction (id),
    party_function varchar(3) NOT NULL REFERENCES dcsa_ebl_v1_0.party_function (party_function_code),
    displayed_address varchar(250) NULL,
    party_contact_details varchar(250) NULL,
    should_be_notified boolean NOT NULL
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.document_party (party_id);
CREATE INDEX ON dcsa_ebl_v1_0.document_party (shipment_id);
CREATE INDEX ON dcsa_ebl_v1_0.document_party (shipping_instruction_id);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.carrier CASCADE;
CREATE TABLE dcsa_ebl_v1_0.carrier (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    carrier_name varchar(100),
    smdg_code varchar(3) NULL,
    nmfta_code varchar(4) NULL
);


/* Charges related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.charges CASCADE;
CREATE TABLE dcsa_ebl_v1_0.charges (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_document_id uuid NULL,
	shipment_id uuid NULL,
	charge_type varchar(20) NULL,
	currency_amount real NULL,
	currency_code varchar(3) NULL,
	payment_term varchar(3) NULL,
	calculation_basis varchar(50) NULL,
	freight_payable_at uuid NULL,
	unit_price real NULL,
	quantity real NULL
);


/* Equipment related Entities */

DROP TABLE IF EXISTS dcsa_ebl_v1_0.iso_equipment_code CASCADE;
CREATE TABLE dcsa_ebl_v1_0.iso_equipment_code (
    iso_equipment_code varchar(4) PRIMARY KEY,
    iso_equipment_name varchar(35) NULL,
    iso_equipment_size_code varchar(2) NULL,
    iso_equipment_type_code_a varchar(2) NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.equipment CASCADE;
CREATE TABLE dcsa_ebl_v1_0.equipment (
	equipment_reference varchar(15) PRIMARY KEY,	-- The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible. According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number and a check-digit). If a container does not comply with ISO 6346, it is suggested to follow Recommendation #2 “Container with non-ISO identification” from SMDG.
	-- Unique code for the different equipment size/type used for transporting commodities. The code is a concatenation of ISO Equipment Size Code and ISO Equipment Type Code A and follows the ISO 6346 standard.
 	iso_equipment_code char(4) NULL REFERENCES dcsa_ebl_v1_0.iso_equipment_code (iso_equipment_code),
	is_shipper_owned boolean NOT NULL DEFAULT false,
	tare_weight real NULL,
	weight_unit varchar(3) NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.equipment (iso_equipment_code);

/* Stuffing related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.package_code CASCADE;
CREATE TABLE dcsa_ebl_v1_0.package_code (
	package_code varchar(3) PRIMARY KEY,
	description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment_equipment CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment_equipment (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	shipment_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.shipment (id),
	equipment_reference varchar(15) NOT NULL REFERENCES dcsa_ebl_v1_0.equipment (equipment_reference),
	cargo_gross_weight real NULL,
	cargo_gross_weight_unit varchar(3) NULL,
	number_of_partloads integer NULL
);

-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.shipment_equipment (equipment_reference);
CREATE INDEX ON dcsa_ebl_v1_0.shipment_equipment (shipment_id);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.active_reefer_settings CASCADE;
CREATE TABLE dcsa_ebl_v1_0.active_reefer_settings (
	shipment_equipment_id uuid PRIMARY KEY REFERENCES dcsa_ebl_v1_0.shipment_equipment (id),
	temperature_min real NULL,
	temperature_max real NULL,
	temperature_unit varchar(3) NULL,
	humidity_min real NULL,
	humidity_max real NULL,
	ventilation_min real NULL,
	ventilation_max real NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.hs_code CASCADE;
CREATE TABLE dcsa_ebl_v1_0.hs_code (
	hs_code varchar(10) PRIMARY KEY,
	code_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.cargo_item CASCADE;
CREATE TABLE dcsa_ebl_v1_0.cargo_item (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	shipment_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.shipment (id),
	description_of_goods text NOT NULL,
	hs_code varchar(10) NOT NULL REFERENCES dcsa_ebl_v1_0.hs_code (hs_code),
	weight real NULL,
	volume real NULL,
	weight_unit varchar(3) NULL,
	volume_unit varchar(3) NULL,
	number_of_packages integer NULL,
	partload_sequence_number integer NULL,
	shipping_instruction_id uuid NULL REFERENCES dcsa_ebl_v1_0.shipping_instruction (id),
	package_code varchar(3) NULL REFERENCES dcsa_ebl_v1_0.package_code (package_code),
	shipment_equipment_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.shipment_equipment (id)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.cargo_item (shipment_id);
CREATE INDEX ON dcsa_ebl_v1_0.cargo_item (hs_code);
CREATE INDEX ON dcsa_ebl_v1_0.cargo_item (shipping_instruction_id);
CREATE INDEX ON dcsa_ebl_v1_0.cargo_item (package_code);
CREATE INDEX ON dcsa_ebl_v1_0.cargo_item (shipment_equipment_id);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.cargo_line_item CASCADE;
CREATE TABLE dcsa_ebl_v1_0.cargo_line_item (
	cargo_line_item_id text NOT NULL,
	cargo_item_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.cargo_item (id),
	shipping_marks text NOT NULL,
	-- Choice of cargo_item_id as first member is deliberate as it enables the
	-- underlying index to be used for FK checks as well (without a separate index
	-- because Postgres currently always creates an INDEX for UNIQUE constraints)
	UNIQUE (cargo_item_id, cargo_line_item_id)
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.seal_source CASCADE;
CREATE TABLE dcsa_ebl_v1_0.seal_source (
	seal_source_code varchar(5) PRIMARY KEY,
	description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.seal_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.seal_type (
	seal_type_code varchar(5) PRIMARY KEY,
	description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.seal CASCADE;
CREATE TABLE dcsa_ebl_v1_0.seal (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	shipment_equipment_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.shipment_equipment (id),
	seal_number varchar(15) NOT NULL,
	seal_source varchar(5) NOT NULL REFERENCES dcsa_ebl_v1_0.seal_source (seal_source_code),
	seal_type varchar(5) NOT NULL REFERENCES dcsa_ebl_v1_0.seal_type (seal_type_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.seal (shipment_equipment_id);
CREATE INDEX ON dcsa_ebl_v1_0.seal (seal_source);
CREATE INDEX ON dcsa_ebl_v1_0.seal (seal_type);


/* Location related Entities */

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment_location_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment_location_type (
	location_type_code varchar(3) PRIMARY KEY,
	location_type_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.country CASCADE;
CREATE TABLE dcsa_ebl_v1_0.country (
	country_code varchar(2) PRIMARY KEY,
	country_name varchar(75) NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.un_location CASCADE;
CREATE TABLE dcsa_ebl_v1_0.un_location (
	un_location_code char(5) PRIMARY KEY,
	un_location_name varchar(100) NULL,
	location_code char(3) NULL,
	country_code char(2) NULL REFERENCES dcsa_ebl_v1_0.country (country_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.un_location (country_code);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.location CASCADE;
CREATE TABLE dcsa_ebl_v1_0.location (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    location_name varchar(100) NULL,
	address varchar(250) NULL,
	latitude varchar(10) NULL,
	longitude varchar(11) NULL,
	un_location_code char(5) NULL REFERENCES dcsa_ebl_v1_0.un_location (un_location_code)
);
-- Supporting FK constraints
CREATE INDEX ON dcsa_ebl_v1_0.location (un_location_code);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment_location CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment_location (
	shipment_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.shipment (id),
	location_id uuid NOT NULL REFERENCES dcsa_ebl_v1_0.location (id),
	location_type varchar(3) NOT NULL REFERENCES dcsa_ebl_v1_0.shipment_location_type (location_type_code),
	displayed_name varchar(250),
	UNIQUE (location_id, location_type, shipment_id)
);

-- Supporting FK constraints
-- Note the omission of INDEX for "location_id" is deliberate; we rely on the implicit INDEX from the
-- UNIQUE constraint for that.
CREATE INDEX ON dcsa_ebl_v1_0.shipment_location (location_type);
CREATE INDEX ON dcsa_ebl_v1_0.shipment_location (shipment_id);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.facility CASCADE;
CREATE TABLE dcsa_ebl_v1_0.facility (
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

DROP TABLE IF EXISTS dcsa_ebl_v1_0.facility_type CASCADE;
CREATE TABLE dcsa_ebl_v1_0.facility_type (
    facility_type_code varchar(4) PRIMARY KEY,
    facility_type_name varchar(100) NULL,
    facility_type_description varchar(250) NULL
);


/* Transport related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.mode_of_transport CASCADE;
CREATE TABLE dcsa_ebl_v1_0.mode_of_transport (
	mode_of_transport_code varchar(3) PRIMARY KEY,
	mode_of_transport_name varchar(100) NULL,
	mode_of_transport_description varchar(250) NULL,
	dcsa_transport_type varchar(50) NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_reference varchar(50) NULL,
	transport_name varchar(100) NULL,
	mode_of_transport varchar(3) NULL,
	load_transport_call_id uuid NOT NULL,
	discharge_transport_call_id uuid NOT NULL,
	vessel varchar(7) NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.vessel CASCADE;
CREATE TABLE dcsa_ebl_v1_0.vessel (
	vessel_imo_number varchar(7) PRIMARY KEY,
	vessel_name varchar(35) NULL,
	vessel_flag char(2) NULL,
	vessel_call_sign_number varchar(10) NULL,
	vessel_operator_carrier_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.shipment_transport CASCADE;
CREATE TABLE dcsa_ebl_v1_0.shipment_transport (
	shipment_id uuid NOT NULL,
	transport_id uuid NOT NULL,
	sequence_number integer NOT NULL,
	commercial_voyage_id uuid
);


/* Events related Entities */




/* Vessel Sharing Agreement related Entities */




/* Service related Entities */




/* Transport Journey related Entities */


DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport_call CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport_call (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	transport_call_sequence_number integer,
	facility_code varchar(11) NULL,
	facility_type_code char(4) NULL,
	other_facility varchar(50) NULL,
	customer_address varchar(250) NULL,
	location_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.voyage CASCADE;
CREATE TABLE dcsa_ebl_v1_0.voyage (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	carrier_voyage_number varchar(50) NULL,
	service_id uuid NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.transport_call_voyage CASCADE;
CREATE TABLE dcsa_ebl_v1_0.transport_call_voyage (
	voyage_id uuid NOT NULL,
	transport_call_id uuid NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.commercial_voyage CASCADE;
CREATE TABLE dcsa_ebl_v1_0.commercial_voyage (
	commercial_voyage_id uuid PRIMARY KEY,
	commercial_voyage_name text NOT NULL
);

DROP TABLE IF EXISTS dcsa_ebl_v1_0.commercial_voyage_transport_call CASCADE;
CREATE TABLE dcsa_ebl_v1_0.commercial_voyage_transport_call (
	transport_call_id uuid NOT NULL,
	commercial_voyage_id uuid NOT NULL
);


/* Create Foreign Key Constraints (Not implemented yet) */


-- ALTER TABLE dcsa_ebl_v1_0.booking ADD CONSTRAINT "FK_Booking_Shipment"
-- 	FOREIGN KEY (shipment_id) REFERENCES dcsa_ebl_v1_0.shipment (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.charges ADD CONSTRAINT "FK_Charges_Location"
-- 	FOREIGN KEY (freight_payable_at) REFERENCES dcsa_ebl_v1_0.location (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.charges ADD CONSTRAINT "FK_Charges_Shipment"
-- 	FOREIGN KEY (shipment_id) REFERENCES dcsa_ebl_v1_0.shipment (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.ebl_endorsement_chain ADD CONSTRAINT "FK_EBL Change Signing Parties_Party"
-- 	FOREIGN KEY (title_holder) REFERENCES dcsa_ebl_v1_0.party (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.ebl_endorsement_chain ADD CONSTRAINT "FK_EBL Endorsement Chain_Party"
-- 	FOREIGN KEY (endorsee) REFERENCES dcsa_ebl_v1_0.party (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.ebl_endorsement_chain ADD CONSTRAINT "FK_EBL Endorsement Chain_Transport Document"
-- 	FOREIGN KEY (transport_document_id) REFERENCES dcsa_ebl_v1_0.transport_document (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.references ADD CONSTRAINT "FK_References_Reference Type"
-- 	FOREIGN KEY (reference_type) REFERENCES dcsa_ebl_v1_0.reference_type (reference_type_code) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.references ADD CONSTRAINT "FK_References_Shipment"
-- 	FOREIGN KEY (shipment_id) REFERENCES dcsa_ebl_v1_0.shipment (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.shipment ADD CONSTRAINT "FK_Shipment_Mode Of Transport"
-- 	FOREIGN KEY (pre_carrier_mode_of_transport) REFERENCES dcsa_ebl_v1_0.mode_of_transport (mode_of_transport_code) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.shipment_transport ADD CONSTRAINT "FK_ShipmentTransportLeg_Shipment"
-- 	FOREIGN KEY (shipment_id) REFERENCES dcsa_ebl_v1_0.shipment (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.shipment_transport ADD CONSTRAINT "FK_ShipmentTransportLeg_Transport Leg"
-- 	FOREIGN KEY (transport_id) REFERENCES dcsa_ebl_v1_0.transport (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport ADD CONSTRAINT "FK_Transport Leg_Transport Call"
-- 	FOREIGN KEY (departure_transport_call_id) REFERENCES dcsa_ebl_v1_0.transport_call (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport ADD CONSTRAINT "FK_Transport Leg_Transport Call_02"
-- 	FOREIGN KEY (arrival_transport_call_id) REFERENCES dcsa_ebl_v1_0.transport_call (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport ADD CONSTRAINT "FK_Transport_Mode Of Transport"
-- 	FOREIGN KEY (mode_of_transport_code) REFERENCES dcsa_ebl_v1_0.mode_of_transport (mode_of_transport_code) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_call ADD CONSTRAINT "FK_Transport Call_Location"
-- 	FOREIGN KEY (location_id) REFERENCES dcsa_ebl_v1_0.location (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_call_voyage ADD CONSTRAINT "FK_Transport Call Voyage_Transport Call"
-- 	FOREIGN KEY (transport_call_id) REFERENCES dcsa_ebl_v1_0.transport_call (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_call_voyage ADD CONSTRAINT "FK_Transport Call Voyage_Voyage"
-- 	FOREIGN KEY (voyage_id) REFERENCES dcsa_ebl_v1_0.voyage (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_document ADD CONSTRAINT "FK_Transport Document_Location"
-- 	FOREIGN KEY (place_of_issue) REFERENCES dcsa_ebl_v1_0.location (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_document ADD CONSTRAINT "FK_Transport Document_Shipping Instruction"
-- 	FOREIGN KEY (shipping_instruction_number) REFERENCES dcsa_ebl_v1_0.shipping_instruction (shipping_instruction_number) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_document ADD CONSTRAINT "FK_Transport Document_Transport Document Type"
-- 	FOREIGN KEY (transport_document_code) REFERENCES dcsa_ebl_v1_0.transport_document_type (transport_document_code) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_document_carrier_clauses ADD CONSTRAINT "FK_Transport Document Carrier Clauses_Carrier Clauses"
-- 	FOREIGN KEY (carrier_clauses_id) REFERENCES dcsa_ebl_v1_0.carrier_clauses (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.transport_document_carrier_clauses ADD CONSTRAINT "FK_Transport Document Carrier Clauses_Transport Document"
-- 	FOREIGN KEY (transport_document_id) REFERENCES dcsa_ebl_v1_0.transport_document (id) ON DELETE No Action ON UPDATE No Action
-- ;
--
-- ALTER TABLE dcsa_ebl_v1_0.vessel ADD CONSTRAINT "FK_Vessel_Transport"
-- 	FOREIGN KEY (transport_id) REFERENCES dcsa_ebl_v1_0.transport (id) ON DELETE No Action ON UPDATE No Action
-- ;

COMMIT;
