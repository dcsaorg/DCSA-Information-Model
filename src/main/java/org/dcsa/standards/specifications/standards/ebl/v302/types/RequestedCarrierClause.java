package org.dcsa.standards.specifications.standards.ebl.v302.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
A clause to request from the carrier. Detailed information about the carrier clauses can be found [here](https://dcsa-website.cdn.prismic.io/dcsa-website/ZvaCMbVsGrYSwERk_202409StandardisedClausesBL-Final.pdf). Possible values are:
- `CARGO_CARGOSPECIFICS` (Cargo/Cargo specifics)
- `VESSELCONVEYANCE_COUNTRYSPECIFIC` (Vessel conveyance/Country Specific)
- `CARGO_RETURNOFEMPTYCONTAINER` (Cargo/Return of Empty Container)
- `CARGO_CARGOVALUE` (Cargo/Cargo value)
- `CARGO_REEFERTEMPERATURE` (Cargo/Reefer temperature)
- `CARGO_CONFLICTINGTEMPERATURES_MIXEDLOADS` (Cargo/Conflicting temperatures/Mixed loads)
- `SHIPPERSLOADSTOWWEIGHTANDCOUNT` (Shipper's Load, Stow, Weight and Count)
- `INTRANSITCLAUSE` (In transit clause)
""",
    example = "CARGO_CARGOSPECIFICS",
    maxLength = 100)
public class RequestedCarrierClause {}
