package org.dcsa.standards.specifications.standards.ebl.v302.model_end;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_end.EndorsementChainLink
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class EndorsementChainLink
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_end.EndorsementChainLink {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The action performed by the actor. This should be one of:
- `ISSUE` (The actor issued the document to the recipient, who is the first possessor of the eBL, as designated by the `Issue to Party`)
- `ENDORSE` (The actor endorsed the document to the recipient)
- `SIGN` (The actor signed the eBL while it was in the actor's possession)
- `SURRENDER_FOR_DELIVERY` (The actor requested to surrender the eBL to the recipient for delivery)
- `SURRENDER_FOR_AMENDMENT` (The actor requested to surrender the eBL to the recipient for amendment)
- `BLANK_ENDORSE` (The actor endorsed the document in blank)
- `ENDORSE_TO_ORDER` (The actor endorsed the document to order of the recipient, allowing the recipient to further endorse the eBL to another party)
- `TRANSFER` (The actor transferred the possession of the eBL to the recipient)
- `SURRENDERED` (The actor acknowledged that the eBL has been accepted and the lifecycle of the eBL is accomplished)

Not all actions are applicable to all surrender requests. The combination and order of endorsement chain entries may differ by eBL Solution Provider, based on their rulebook and/or bilateral agreements with carriers.
""",
      example = "ISSUE",
      maxLength = 50)
  private String actionCode;

  @Schema(
      description =
          "An identifier issued by the eBL Solution Provider, used for auditing purposes to verify that the endorsement chain action has been securely recorded. The format of this identifier may vary depending on the technology employed by the eBL Solution Provider.",
      example = "AUDIT0007",
      maxLength = 100)
  private String auditReference;
}
