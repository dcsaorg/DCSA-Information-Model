-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Use a transaction so a bug will not leave tainted / incomplete data.
BEGIN;

\copy dcsa_im_v3_0.facility_type from '../referencedata.d/facilitytypes.csv' CSV HEADER
\copy dcsa_im_v3_0.seal_source from '../referencedata.d/sealsourcecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.seal_type from '../referencedata.d/sealtypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.shipment_location_type from '../referencedata.d/shipmentlocationtypes.csv' CSV HEADER
\copy dcsa_im_v3_0.party_function from '../referencedata.d/partyfunctioncodes.csv' CSV HEADER
\copy dcsa_im_v3_0.reference_type from '../referencedata.d/referencetypes.csv' CSV HEADER
\copy dcsa_im_v3_0.receipt_delivery_type from '../referencedata.d/receiptdeliverytypes.csv' CSV HEADER
\copy dcsa_im_v3_0.cargo_movement_type from '../referencedata.d/cargomovementtypes.csv' CSV HEADER
\copy dcsa_im_v3_0.mode_of_transport from '../referencedata.d/modeoftransportcodes.csv' CSV HEADER
\copy dcsa_im_v3_0.transport_document_type from '../referencedata.d/transportdocumenttypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.shipment_event_type from '../referencedata.d/shipmenteventtypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.event_classifier from '../referencedata.d/eventclassifiercodes.csv' CSV HEADER
\copy dcsa_im_v3_0.equipment_event_type from '../referencedata.d/equipmenteventtypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.shipment_information_type from '../referencedata.d/shipmentinformationtypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.transport_event_type from '../referencedata.d/transporteventtypecodes.csv' CSV HEADER
\copy dcsa_im_v3_0.empty_indicator from '../referencedata.d/emptyindicatorcodes.csv' CSV HEADER

COMMIT;
