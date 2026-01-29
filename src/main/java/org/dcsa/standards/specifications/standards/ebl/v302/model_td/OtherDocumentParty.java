package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Party;

@Schema(
    description =
        "A document party optionally provided in the Shipping Instructions or Transport Document, with a specified role.")
@Data
public class OtherDocumentParty {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The party involved in the document.")
  private Party party;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Specifies the role of the party in a given context. Possible values are:

- `SCO` (Service Contract Owner)
- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `COW` (Invoice payer on behalf of the consignor (shipper))
- `COX` (Invoice payer on behalf of the consignee)
""",
      example = "DDS",
      maxLength = 3)
  private String partyFunction;
}
