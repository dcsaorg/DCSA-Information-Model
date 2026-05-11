package org.dcsa.standards.specifications.standards.ebl.v303.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class PartyAddress
    extends org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the street of the party's address.",
      example = "Ruijggoordweg",
      maxLength = 70)
  private String street;

  @Schema(
      description = "The number of the street of the party's address.",
      example = "100",
      maxLength = 50)
  private String streetNumber;

  @Schema(
      description = "The floor of the party's street number.",
      example = "2nd",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String floor;

  @Schema(
      description = "The post code of the party's address.",
      example = "1047 HM",
      maxLength = 10)
  private String postCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The city name of the party's address.",
      example = "Amsterdam",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String city;

  @Schema(
      description = "The state/region of the party's address.",
      example = "North Holland",
      maxLength = 65)
  private String stateRegion;
}
