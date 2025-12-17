package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.model.Location;
import org.dcsa.standards.specifications.standards.core.v102.types.VesselIMONumber;
import org.dcsa.standards.specifications.standards.core.v102.types.VesselMMSINumber;

@Data
@Schema(description = "Vessel information relevant for a port call service")
public class Vessel {

  @Schema() private VesselIMONumber vesselIMONumber;

  @Schema() private VesselMMSINumber vesselMMSINumber;

  @Schema(maxLength = 50, example = "King of the Seas", description = "Vessel name")
  private String vesselName;

  @Schema(
      maxLength = 10,
      example = "NCVV",
      description =
"""
A unique alphanumeric identity that belongs to the vessel and is assigned by the International Telecommunication Union (ITU).
It consists of a three-letter alphanumeric prefix that indicates nationality, followed by one to four characters
to identify the individual vessel.
""")
  private String vesselCallSign;

  @Schema(
      maxLength = 4,
      example = "CONT",
      description =
"""
Categorization of ocean-going vessels distinguished by the main cargo the vessel carries:
- `GCGO` (General cargo)
- `CONT` (Container)
- `RORO` (RoRo)
- `CARC` (Car carrier)
- `PASS` (Passenger)
- `FERY` (Ferry)
- `BULK` (Bulk)
- `TANK` (Tanker)
- `LGTK` (Liquefied gas tanker)
- `ASSI` (Assistance)
- `PILO` (Pilot boat)
""")
  private String vesselTypeCode;

  @Schema(description = "Current location of the vessel")
  private Location vesselPosition;

  @Schema(
      example = "245.45",
      description =
"""
Remaining distance reported by the vessel to the berth in the next destination port, expressed in nautical miles.
""")
  private Double milesToDestinationPort;

  @Schema(
      maxLength = 3,
      example = "MTR",
      description =
"""
The unit of measure in which the vessel length and width are expressed:
- `MTR` (Meter)
- `FOT` (Foot)

If values are specified in feet (`FOT`) then the decimal part means a fraction of a foot and **not** as a number of inches.
E.g. 120.5 feet means 120 and a half foot (which would be 120'6").
""")
  private String vesselSizeUnit;

  @Schema(
      format = "float",
      example = "245.45",
      description =
"""
The maximum length of the vessel's hull measured parallel to the waterline ("length overall").
""")
  private Double lengthOverall;

  @Schema(
      format = "float",
      example = "37.33",
      description =
"""
Overall width of the ship measured at the widest point of the nominal waterline.
""")
  private Double width;

  @Schema(
    maxLength = 3,
    example = "MTR",
    description =
"""
The unit of measure in which the draft values are expressed:
- `MTR` (Meter)
- `FOT` (Foot)

If values are specified in feet (`FOT`) then the decimal part means a fraction of a foot and **not** as a number of inches.
E.g. 120.5 feet means 120 and a half foot (which would be 120'6").
""")
  private String draftUnit;

  @Schema(
      format = "float",
      example = "55.0",
      description =
"""
The vertical distance from the surface of the water to the highest point of mast or aerial.
""")
  private Double airDraft;

  @Schema(
      format = "float",
      example = "12.5",
      description =
"""
The vertical distance between the waterline and the bottom of the hull (keel) of a ship.
The depth to which a ship is immersed in water.
""")
  private Double draft;

  @Schema(
      format = "float",
      example = "37",
      description =
"""
The vertical distance between the waterline and the bottom of the hull (keel) at the stern (after part) of the vessel.
The depth to which the after part of the ship is immersed in water.
""")
  private Double aftDraft;

  @Schema(
      format = "float",
      example = "35",
      description =
"""
The vertical distance between the waterline and the bottom of the hull (keel) at the bow (forward part) of the vessel.
The depth to which the forward part of the ship is immersed in water.
""")
  private Double forwardDraft;
}
