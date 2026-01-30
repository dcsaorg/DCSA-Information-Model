package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
`Import License` requirements

**Condition:** Subject to local customs requirements and commodity.
""",
    title = "Import License (Shipper)")
@Data
public class ImportLicenseShipper {

  @Schema(
      description =
"""
Information provided by the shipper to indicate whether an `Import License` or permit is required for this shipment/commodity/destination.

**Note:** If this property is omitted, it may be interpreted differently by different API providers and by the same API provider in different contexts.
""",
      example = "true")
  private Boolean isRequired;

  @Schema(
      description =
          "Reference number assigned to an `Import License` or permit, issued by countries exercising import controls that authorizes the importation of the articles stated in the license. The `Import License` must be valid at time of arrival.",
      example = "EMC007123",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String reference;

  @Schema(description = "Issue date of the `Import License`.", example = "2024-09-14", format = "date")
  private String issueDate;

  @Schema(
      description = "Expiry date of the `Import License`.",
      example = "2024-09-21",
      format = "date")
  private String expiryDate;
}
