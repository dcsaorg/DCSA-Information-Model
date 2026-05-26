package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An object for storing address related information.")
@Data
public class Address {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the address.",
      maxLength = 100,
      example = "John")
  private String name;

  @Schema(
      description = "The name of the street of the party's address.",
      maxLength = 100,
      example = "Ruijggoordweg")
  private String street;

  @Schema(
      description = "The number of the street of the party's address.",
      maxLength = 50,
      example = "100")
  private String streetNumber;

  @Schema(description = "The floor of the party's street number.", maxLength = 50, example = "N/A")
  private String floor;

  @Schema(
      description = "The post code of the party's address.",
      maxLength = 50,
      example = "1047 HM")
  private String postCode;

  @Schema(
      description = "The city name of the party's address.",
      maxLength = 65,
      example = "Amsterdam")
  private String city;

  @Schema(
      description = "The state/region of the party's address.",
      maxLength = 65,
      example = "North Holland")
  private String stateRegion;

  @Schema(
      description = "The country of the party's address.",
      maxLength = 75,
      example = "The Netherlands")
  private String country;
}
