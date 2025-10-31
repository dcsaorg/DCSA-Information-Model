DCSA non-reference data
=======================

This directory contains DCSA data lists created by DCSA
for the sake of supporting DCSA's reference implementation.

These data lists are in some cases not part of any standard
or can be a mix of data from the standard "enriched" with
additional data.

DCSA maintains these on a best effort basis and do not guarantee
stability.  CSV files and their structure may change.

Disclaimer: DCSA does *not* guarantee that the CSV lists are
complete. In case there is any discrepancy between these CSV
lists and the official standards documentation, then the
official standards documentation would be correct.


Data sources
------------

Some of these files are based on external input or have been
generated:

 * `publisherpattern.csv`, `timestampdefinitions.csv`, `timestampdefinitions_publisherpattern.csv`
   * Generated from `generate_timestampdefinitions.py` 

 * `porttimezones.csv`
   * Generated from `update_porttimezones.py`
   * Data input: [unlocationcodes.csv](../samples.d/unlocationcodes.csv)

