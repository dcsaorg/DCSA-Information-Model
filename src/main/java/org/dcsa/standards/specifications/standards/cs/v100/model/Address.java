package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An object for storing address related information.")
@Data
public class Address {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the street.",
      maxLength = 70,
      example = "Ruijggoordweg")
  private String street;

  @Schema(description = "The number of the street.", maxLength = 50, example = "100")
  private String streetNumber;

  @Schema(
      description = "The floor of the street number.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "N/A")
  private String floor;

  @Schema(description = "The post code.", maxLength = 10, example = "1047 HM")
  private String postCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the city.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35,
      example = "Amsterdam")
  private String city;

  @Schema(description = "The name of the state/region.", maxLength = 65, example = "North Holland")
  private String stateRegion;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      pattern = "^[A-Z]{2}$",
      minLength = 2,
      maxLength = 2,
      example = "NL")
  private String countryCode;
}
