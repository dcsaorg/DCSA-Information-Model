Sample data from external (non-DCSA) providers
==============================================

This directory contains sample data from code list provides such as
SMDG or UNECE. DCSA has collected them to show case how the data would
fit inside the datamodel or because DCSA relies on them in tests.


Disclaimer: DCSA does *not* guarantee that the lists are complete,
correct or up to date.  Implementors are expected to collect and
maintain the full lists themselves.


Data sources
------------

Below is a list of the data sources for each the files maintained on a
best-effort basis.  Corrections are welcome (via PR).

 * carriers.csv: Subset of https://smdg.org/documents/smdg-code-lists/smdg-liner-code-list/

 * countrycodes.csv: Subset of https://unece.org/trade/cefact/unlocode-code-list-country-and-territory

 * facilities.csv: BIC and SMDG facility code lists
   - BIC facility code list (see https://www.bic-code.org/bic-facility-codes/)
     * File is a subset of: https://raw.githubusercontent.com/bic-org/Facility-Code/master/sample-bic-facility-codes.csv
   - SMDG terminal list (see https://smdg.org/documents/smdg-code-lists/smdg-terminal-code-list/)
     * File is a subset of: https://raw.githubusercontent.com/smdg-org/Terminal-Code-List/master/SMDG%20Terminal%20Code%20List.csv
   - Use `python3 update_facilities.py` to update the data set

 * hscodes.csv: Subset of the "Harmonized System" list. Defined by: WCO (http://www.wcoomd.org/en/topics/nomenclature/overview/what-is-the-harmonized-system.aspx)

 * unlocationcodes.csv: Subset of https://unece.org/trade/cefact/unlocode-code-list-country-and-territory

 * porttimezones.csv: Subset of https://github.com/marek5050/UN-Locode-with-Timezone
   - Note: This data is *not* part of the official IM.  It is only used via DCSA-UI-Support
   - Use `python3 update_porttimezones.py`to update the data set
