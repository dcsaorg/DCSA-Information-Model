package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
An intermediate call between `departure` and `arrival` in the current leg.

The cargo remains on the same planned transport, and no transhipment takes place at this location.
""")
@Data
public class IntermediateCall {

  @Schema(
      description =
"""
The unique reference for the arrival transport call. It's the vessel operator's responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.
""",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLAMS-ACT-1-1")
  private String transportCallReference;

  @Schema(
      description =
"""
The code to identify the specific type of facility. The code indicates which role the facility plays during the transportCall.
- `BORD` (Border)
- `CLOC` (Customer Location)
- `COFS` (Container Freight Station)
- `OFFD` (Off Dock Storage)
- `DEPO` (Depot)
- `INTE` (Inland Terminal)
- `POTE` (Port Terminal)
- `PBPL` (Pilot Boarding Place)
- `BRTH` (Berth)
- `RAMP` (Ramp)
- `WAYP` (Waypoint)
""",
      maxLength = 4,
      example = "POTE")
  private String facilityTypeCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private org.dcsa.standards.specifications.standards.cs.v102.model.Location location;

  @Schema(
      description = "The local date and time, when the arrival will take place.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String arrivalDateTime;

  @Schema(
      description = "The local date and time, when the departure will take place.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String departureDateTime;
}
