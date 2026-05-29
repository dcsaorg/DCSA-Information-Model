package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Tax or legal reference relevant to a party.")
@Data
public class TaxLegalReference {

  @Schema(
      description =
          "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      example = "IN",
      maxLength = 2)
  private String countryCode;

  @Schema(
      description =
          "The reference type code as defined by the relevant tax and/or legal authority.",
      example = "PAN",
      maxLength = 50)
  private String type;

  @Schema(
      description = "The value of the `taxLegalReference`",
      example = "AAAAA0000A",
      maxLength = 100)
  private String value;
}
