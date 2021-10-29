-- Assumes the PSQL client
\set ON_ERROR_STOP true
\connect dcsa_openapi

-- Use a transaction so a bug will not leave tainted / incomplete data.
BEGIN;

\copy dcsa_im_v3_0.facility_type from '../referencedata.d/facilitytypes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.seal_source from '../referencedata.d/sealsourcecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.seal_type from '../referencedata.d/sealtypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.shipment_location_type from '../referencedata.d/shipmentlocationtypes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.party_function from '../referencedata.d/partyfunctioncodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.reference_type from '../referencedata.d/referencetypes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.receipt_delivery_type from '../referencedata.d/receiptdeliverytypes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.cargo_movement_type from '../referencedata.d/cargomovementtypes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.mode_of_transport from '../referencedata.d/modeoftransportcodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.transport_document_type from '../referencedata.d/transportdocumenttypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.shipment_event_type from '../referencedata.d/shipmenteventtypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.event_classifier from '../referencedata.d/eventclassifiercodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.equipment_event_type from '../referencedata.d/equipmenteventtypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.document_type from '../referencedata.d/documenttypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.transport_event_type from '../referencedata.d/transporteventtypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.empty_indicator from '../referencedata.d/emptyindicatorcodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.operations_event_type from '../referencedata.d/operationseventtypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.port_call_phase_type from '../referencedata.d/portcallphasetypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.port_call_service_type from '../referencedata.d/portcallservicetypecodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.code_list_responsible_agency from '../referencedata.d/codelistresponsibleagencycodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.payment_term_type from '../referencedata.d/paymentterms.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.inco_terms from '../referencedata.d/incotermscodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.communication_channel_qualifier from '../referencedata.d/communicationchannelqualifier.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.cut_off_time from '../referencedata.d/cutofftimescodes.csv' with NULL AS E'\'\'' CSV HEADER
\copy dcsa_im_v3_0.transport_plan_stage_type from '../referencedata.d/transportplanstagetypes.csv' with NULL AS E'\'\'' CSV HEADER

COMMIT;
