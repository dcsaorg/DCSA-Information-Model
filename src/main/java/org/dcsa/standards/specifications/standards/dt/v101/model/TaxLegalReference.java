package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = TaxLegalReference.CLASS_SCHEMA_DESCRIPTION)
@Data
public class TaxLegalReference {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Reference that uniquely identifies a party for tax and/or legal purposes according to local jurisdiction.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The reference type code as defined by the relevant tax and/or legal authority.
""",
      example = "PAN",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  protected String type;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
""",
      example = "IN",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  protected String countryCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The value of the `taxLegalReference`
""",
      example = "AAAAA0000A",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  protected String value;
}
