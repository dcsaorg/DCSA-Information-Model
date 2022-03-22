-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Currently an implementation detail for the reference implementation but one
-- we would like to remove eventually.
BEGIN;

-- Metadata IDs

-- Most of the R2DBC tooling we are currently using requires that every entity
-- has a direct ID.  This change is to insert these, so the code works but they
-- are not a part of the original data model.

ALTER TABLE dcsa_im_v3_0.displayed_address
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.shipment_location
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.cargo_line_item
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.shipment_transport
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.seal
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.reference
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.party_identifying_code
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.value_added_service_request
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.commodity
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

ALTER TABLE dcsa_im_v3_0.requested_equipment
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;
    
-- DateTime metadata

-- Metadata for Booking table to avoid having to query shipmentEvent for 
-- updated date_time necessary for BookingResponseTO

ALTER TABLE dcsa_im_v3_0.booking
    ADD COLUMN IF NOT EXISTS updated_date_time timestamp with time zone NOT NULL;

ALTER TABLE dcsa_im_v3_0.shipping_instruction
    ADD COLUMN IF NOT EXISTS created_date_time timestamp with time zone NOT NULL;

ALTER TABLE dcsa_im_v3_0.shipping_instruction
    ADD COLUMN IF NOT EXISTS updated_date_time timestamp with time zone NOT NULL;

ALTER TABLE dcsa_im_v3_0.transport_document
    ADD COLUMN IF NOT EXISTS created_date_time timestamp with time zone NOT NULL;

ALTER TABLE dcsa_im_v3_0.transport_document
    ADD COLUMN IF NOT EXISTS updated_date_time timestamp with time zone NOT NULL;

ALTER TABLE dcsa_im_v3_0.shipment
    ADD COLUMN IF NOT EXISTS updated_date_time timestamp with time zone NOT NULL;

DROP TABLE IF EXISTS dcsa_im_v3_0.ebl_solution_provider_type CASCADE;
CREATE TABLE dcsa_im_v3_0.ebl_solution_provider_type (
    ebl_solution_provider_name varchar(50) NOT NULL,
    ebl_solution_provider_code varchar(5) PRIMARY KEY,
    ebl_solution_provider_url varchar(100) NOT NULL,
    ebl_solution_provider_description varchar(250) NULL
);

\copy dcsa_im_v3_0.ebl_solution_provider_type from '../referencedata.d/eblsolutionproviders.csv' with NULL AS E'\'\'' CSV HEADER

COMMIT;
