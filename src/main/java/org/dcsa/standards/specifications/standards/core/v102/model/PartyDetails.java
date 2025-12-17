package org.dcsa.standards.specifications.standards.core.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Details of a party involved in the VGM declaration")
@Data
public class PartyDetails {

  @Schema(description = "Party name", example = "Acme Inc.", maxLength = 70)
  private String partyName;

  @Schema() private Party party;

  @Schema() private Address address;

  @Schema() private ContactDetails contactDetails;
}
