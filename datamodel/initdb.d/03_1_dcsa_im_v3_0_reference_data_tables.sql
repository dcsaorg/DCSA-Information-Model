\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

/* Reference data tables (ordered alphabetically) */

DROP TABLE IF EXISTS dcsa_im_v3_0.country CASCADE;
CREATE TABLE dcsa_im_v3_0.country (
    country_code varchar(2) PRIMARY KEY,
    country_name varchar(75) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.equipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.equipment_event_type (
    equipment_event_type_code varchar(4) PRIMARY KEY,
    equipment_event_type_name varchar(30) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.facility_type CASCADE;
CREATE TABLE dcsa_im_v3_0.facility_type (
    facility_type_code varchar(4) PRIMARY KEY,
    facility_type_name varchar(100) NULL,
    facility_type_description varchar(250) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.hs_code CASCADE;
CREATE TABLE dcsa_im_v3_0.hs_code (
    hs_code varchar(10) PRIMARY KEY,
    code_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.iso_equipment_code CASCADE;
CREATE TABLE dcsa_im_v3_0.iso_equipment_code (
    iso_equipment_code varchar(4) PRIMARY KEY,
    iso_equipment_name varchar(35) NULL,
    iso_equipment_size_code varchar(2) NULL,
    iso_equipment_type_code_a varchar(2) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.mode_of_transport CASCADE;
CREATE TABLE dcsa_im_v3_0.mode_of_transport (
    mode_of_transport_code varchar(3) PRIMARY KEY,
    mode_of_transport_name varchar(100) NULL,
    mode_of_transport_description varchar(250) NULL,
    dcsa_transport_type varchar(50) NULL UNIQUE
);

DROP TABLE IF EXISTS dcsa_im_v3_0.operations_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.operations_event_type (
    operations_event_type_code varchar(4) PRIMARY KEY,
    operations_event_type_name varchar(30) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.party_function CASCADE;
CREATE TABLE dcsa_im_v3_0.party_function (
    party_function_code varchar(3) PRIMARY KEY,
    party_function_name varchar(100) NOT NULL,
    party_function_description varchar(250) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.port_call_service_type CASCADE;
CREATE TABLE dcsa_im_v3_0.port_call_service_type (
    port_call_service_type_code varchar(4) PRIMARY KEY, -- The 4-letter code indicating the type of the port call service.
    port_call_service_type_name varchar(50) NOT NULL, -- The name of the of the port call service type.
    port_call_service_type_description varchar(300) NOT NULL -- The description of the of the port call service type.
);

DROP TABLE IF EXISTS dcsa_im_v3_0.reference_type CASCADE;
CREATE TABLE dcsa_im_v3_0.reference_type (
    reference_type_code varchar(3) PRIMARY KEY,
    reference_name varchar(100) NOT NULL,
    reference_description varchar(200) NOT NULL
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

DROP TABLE IF EXISTS dcsa_im_v3_0.service_type CASCADE;
CREATE TABLE dcsa_im_v3_0.service_type (
    service_type varchar(3) PRIMARY KEY,
    description varchar(300) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_event_type (
    shipment_event_type_code varchar(4) PRIMARY KEY,
    shipment_event_type_name varchar(30) NOT NULL,
    shipment_event_type_description varchar(200) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.shipment_location_type CASCADE;
CREATE TABLE dcsa_im_v3_0.shipment_location_type (
    location_type_code varchar(3) PRIMARY KEY,
    location_type_description varchar(50) NOT NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_document_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_document_type (
    transport_document_type_code varchar(3) PRIMARY KEY,
    transport_document_type_name varchar(20) NULL,
    transport_document_type_description varchar(500) NULL
);

DROP TABLE IF EXISTS dcsa_im_v3_0.transport_event_type CASCADE;
CREATE TABLE dcsa_im_v3_0.transport_event_type (
    transport_event_type_code varchar(4) PRIMARY KEY,
    transport_event_type_name varchar(30) NOT NULL
);

COMMIT;
