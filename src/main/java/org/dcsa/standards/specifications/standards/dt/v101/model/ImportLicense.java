package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "`Import License` requirements.\n\nCondition: Included if the property is provided in the `Shipping Instructions.`")
@Data
public class ImportLicense {

  @Schema(
      description =
"""
Information provided by the shipper to indicate whether an `Import License` or permit is required for this shipment/commodity/destination.

**Note:** If this property is omitted, it may be interpreted differently by different API providers and by the same API provider in different contexts.
""",
      example = "true")
  protected Boolean isRequired;

  @Schema(
      description =
"""
Reference number assigned to an `Import License` or permit, issued by countries exercising import controls that authorizes the importation of the articles stated in the license. The `Import License` must be valid at time of arrival.
""",
      example = "EMC007123",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  protected String reference;

  @Schema(
      description = "Issue date of the `Import License`.",
      example = "2024-09-14",
      format = "date")
  protected String issueDate;

  @Schema(
      description = "Expiry date of the `Import License`.",
      example = "2024-09-21",
      format = "date")
  protected String expiryDate;
}
