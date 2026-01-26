package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An object for storing city, state/region, and country related information.")
@Data
public class City {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the city.",
      example = "Amsterdam",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String city;

  @Schema(description = "The name of the state/region.", example = "North Holland", maxLength = 65)
  private String stateRegion;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
