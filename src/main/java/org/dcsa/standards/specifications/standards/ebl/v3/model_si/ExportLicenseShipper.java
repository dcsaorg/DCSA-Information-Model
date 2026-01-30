package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
`Export License` requirements

**Condition:** Subject to local customs requirements and commodity.
""",
    title = "Export License (Shipper)")
@Data
public class ExportLicenseShipper {

  @Schema(
      description =
"""
Information provided by the shipper to indicate whether an `Export License` or permit is required for this shipment/commodity/destination.

**Note:** If this property is omitted, it may be interpreted differently by different API providers and by the same API provider in different contexts.
""",
      example = "true")
  private Boolean isRequired;

  @Schema(
      description =
          "Reference number assigned to an `Export License` or permit, which authorizes a business or individual to export specific goods to specific countries under defined conditions. It is a permit that is required when shipping certain restricted or controlled goods, such as military equipment, high-tech items, chemicals, or items subject to international regulations. The `Export License` must be valid at time of departure.",
      example = "EMC007123",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String reference;

  @Schema(description = "Issue date of the `Export License`.", example = "2024-09-14", format = "date")
  private String issueDate;

  @Schema(
      description = "Expiry date of the `Export License`.",
      example = "2024-09-21",
      format = "date")
  private String expiryDate;
}
