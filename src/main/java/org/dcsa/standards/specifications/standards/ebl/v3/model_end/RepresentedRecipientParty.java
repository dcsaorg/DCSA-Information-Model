package org.dcsa.standards.specifications.standards.ebl.v3.model_end;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(
    description = "The party on whose behalf the action was directed.",
    title = "Represented Party")
@Data
public class RepresentedRecipientParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "Globeteam",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema private List<IdentifyingCode> identifyingCodes;
}
