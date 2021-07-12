\set ON_ERROR_STOP true
\connect dcsa_openapi

BEGIN;

\copy dcsa_im_v3_0.carrier (carrier_name, smdg_code, nmfta_code) from '../samples.d/carriers.csv' CSV HEADER
\copy dcsa_im_v3_0.country from '../samples.d/countrycodes.csv' CSV HEADER
\copy dcsa_im_v3_0.un_location from '../samples.d/unlocationcodes.csv' CSV HEADER
\copy dcsa_im_v3_0.facility (facility_name, un_location_code, facility_smdg_code) from '../samples.d/facilities.csv' CSV HEADER
\copy dcsa_im_v3_0.hs_code from '../samples.d/hscodes.csv' CSV HEADER
\copy dcsa_im_v3_0.smdg_delay_reason from '../samples.d/smdgdelayreasoncodes.csv' CSV HEADER

COMMIT;