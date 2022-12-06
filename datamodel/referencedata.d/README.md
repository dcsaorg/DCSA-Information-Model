DCSA defined data reference data
================================

This directory contains DCSA reference code lists.  In some cases,
it has been crossed with external reference data lists where this
is both a DCSA code and a external code reference.  

DCSA maintains these on a best effort basis and do not guarantee
stability.  CSV files and their structure may change.

Disclaimer: DCSA does *not* guarantee that the CSV lists are
complete. In case there is any discrepancy between these CSV
lists and the official standards documentation, then the
official standards documentation would be correct.


Data sources
------------

Notes about some particular files:

 * [modeoftransportcodes.csv](modeoftransportcodes.csv):
   * This is a mix of a DCSA and a subset of the EDIFACT
     standard.  It has been classified as reference data as
     DCSA defines the code for the `DCSA Transport Type` column.
     The `Mode of Transport Code` column is from the EDIFACT
     standard, which defines additional codes (but these do not
     have a DCSA code).
