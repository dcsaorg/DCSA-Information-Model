-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Currently an implementation detail for the reference implementation but one
-- we would like to remove eventually.
BEGIN;

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

ALTER TABLE dcsa_im_v3_0.references
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

COMMIT;
