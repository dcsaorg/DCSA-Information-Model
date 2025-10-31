package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Address.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Address {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "An object for storing address-related information.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the street.",
      example = "Ruijggoordweg",
      maxLength = 70)
  protected String street;

  @Schema(description = "The number of the street.", example = "100", maxLength = 50)
  protected String streetNumber;

  @Schema(
      description = "The floor of the street number.",
      example = "N/A",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  protected String floor;

  @Schema(description = "The post code of the address.", example = "1047 HM", maxLength = 10)
  protected String postCode;

  @Schema(
      name = "POBox",
      description =
          "A numbered box at a post office where a person or business can have mail or parcels delivered.",
      example = "123",
      maxLength = 20)
  protected String poBox;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the city.",
      example = "Amsterdam",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  protected String city;

  @Schema(description = "The name of the state/region.", example = "North Holland", maxLength = 65)
  protected String stateRegion;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  protected String countryCode;
}
