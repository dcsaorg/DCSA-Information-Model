-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Currently an implementation detail for the reference implementation but one
-- we would like to remove eventually.
BEGIN;

-- Metadata for Booking table to avoid having to query shipmentEvent for date_times necessary for BookingResponseTO
ALTER TABLE dcsa_im_v3_0.booking
    ADD COLUMN IF NOT EXISTS created_date_time timestamp with time zone NOT NULL;
ALTER TABLE dcsa_im_v3_0.booking
    ADD COLUMN IF NOT EXISTS updated_date_time timestamp with time zone NOT NULL;
COMMIT;
