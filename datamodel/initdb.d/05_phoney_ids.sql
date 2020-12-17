-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Use a transaction so a bug will not leave tainted / incomplete data.
BEGIN;

-- Most of the R2DBC tooling we are currently using requires that every entity
-- has a direct ID.  This change is to insert these, so the code works but they
-- are not a part of the original data model.

ALTER TABLE dcsa_ebl_v1_0.document_party
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;


ALTER TABLE dcsa_ebl_v1_0.shipment_location
    ADD COLUMN IF NOT EXISTS id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

COMMIT;
