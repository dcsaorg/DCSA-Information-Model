package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "The Location specifying where the place of receipt is located.")
@Data
public class PlaceOfReceipt {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
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
  private Location location;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The local date and time, when the event will take place, in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format specifying the offset.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String dateTime;
}
