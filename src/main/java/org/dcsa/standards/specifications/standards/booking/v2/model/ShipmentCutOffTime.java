package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = ShipmentCutOffTime.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ShipmentCutOffTime {

  public static final String CLASS_SCHEMA_DESCRIPTION = "`Cut off times` defined by the carrier";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code for the cut-off time. Possible values are:
- `DCO` (Documentation cut-off)
- `VCO` (VGM cut-off)
- `FCO` (FCL delivery cut-off)
- `LCO` (LCL delivery cut-off) **Condition:** only when the `Receipt Type at Origin` is `CFS`
- `EFC` (Earliest full-container delivery date)
""",
      example = "DCO",
      maxLength = 3)
  private String cutOffDateTimeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Actual cut-off time",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String cutOffDateTime;
}
