package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Party;

@Schema(
    description =
      OtherDocumentParty.CLASS_SCHEMA_DESCRIPTION)
@Data
public class OtherDocumentParty {

  public static final String CLASS_SCHEMA_DESCRIPTION = "A document party optionally provided in the Shipping Instructions or Transport Document, with a specified role.";

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
- `CS` (Consolidator)
- `MF` (Manufacturer)
- `WH` (Warehouse Keeper)
""",
      example = "DDS",
      maxLength = 3)
  private String partyFunction;
}
