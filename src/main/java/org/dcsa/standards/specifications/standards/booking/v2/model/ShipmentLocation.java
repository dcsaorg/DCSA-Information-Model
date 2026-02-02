package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = ShipmentLocation.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ShipmentLocation {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Maps the relationship between `Shipment` and `Location`, e.g., the `Place of Receipt` and the `Place of Delivery` for a specific shipment. This is a reusable object between `Booking` and `Transport Document`";

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The location information.")
  private Location location;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Links to the Location Type Code defined by DCSA. Possible values are:
- `PRE` (Place of Receipt)
- `POL` (Port of Loading)
- `POD` (Port of Discharge)
- `PDE` (Place of Delivery)
- `PCF` (Pre-carriage From)
- `OIR` (Onward In-land Routing)
- `ORI` (Origin of goods)
- `IEL` (Container intermediate export stop off location)
- `PTP` (Prohibited transshipment port)
- `RTP` (Requested transshipment port)
- `FCD` (Full container drop-off location)
""",
      example = "PRE",
      maxLength = 3)
  private String locationTypeCode;
}
