package org.dcsa.standards.specifications.standards.ebl.v3.model_end;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = EndorsementChainLink.CLASS_SCHEMA_DESCRIPTION, title = "Endorsement Chain Link")
@Data
public class EndorsementChainLink {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Entry in the endorsement chain.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Date time when the action occurred.",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String actionDateTime;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          """
The action performed by the party. This should be one of:
- `ISSUE` (The actor issued the document to the recipient)
- `ENDORSE` (The actor endorsed the document to the recipient)
- `SIGN` (The actor signed or performed an "assignment" to the recipient)
- `SURRENDER_FOR_DELIVERY` (The actor requested this surrender request for delivery to the recipient)
- `SURRENDER_FOR_AMENDMENT` (The actor requested this surrender request for amendment to the recipient)
Not all actions are applicable to all surrender requests.
""",
      example = "ISSUE",
      maxLength = 50)
  private String actionCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private ActorParty actor;

  @Schema private RecipientParty recipient;
}
