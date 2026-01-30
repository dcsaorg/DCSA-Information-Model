package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Party;

@Schema(
    description =
        "A list of document parties that can be optionally provided in the `Shipping Instructions`.",
    title = "Other Document Party (Shipping Instructions)")
@Data
public class OtherDocumentPartyShippingInstructions {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
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
