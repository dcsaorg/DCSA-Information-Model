-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Use a transaction so a bug will not leave tainted / incomplete data.
BEGIN;

\copy dcsa_ebl_v1_0.facility_type from 'facilitytypes.csv' CSV HEADER
\copy dcsa_ebl_v1_0.seal_source from 'sealsourcecodes.csv' CSV HEADER
\copy dcsa_ebl_v1_0.seal_type from 'sealtypecodes.csv' CSV HEADER
\copy dcsa_ebl_v1_0.shipment_location_type from 'shipmentlocationtypes.csv' CSV HEADER

COMMIT;
