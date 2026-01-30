package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = City.CLASS_SCHEMA_DESCRIPTION)
@Data
public class City {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An object for storing city, state/region, and country related information.";

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
""",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
