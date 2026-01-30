package org.dcsa.standards.specifications.standards.ebl.v3.model_sur;

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

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private TransactionParty actor;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, name = "recipient")
  private TransactionParty recipientAsTransactionParty;
}
