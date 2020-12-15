#!/bin/sh

set -e

if [ -z "${DCSA_REFERENCE_DATA}" ]; then
  DCSA_REFERENCE_DATA="`dirname $0`/datamodel/referencedata.d"
fi

echo "Loading reference data from ${DCSA_REFERENCE_DATA}"
echo "  set DCSA_REFERENCE_DATA=<some-directory> if that is wrong"
cd "${DCSA_REFERENCE_DATA}" && psql -f ./load_ebl_reference_data.sql
