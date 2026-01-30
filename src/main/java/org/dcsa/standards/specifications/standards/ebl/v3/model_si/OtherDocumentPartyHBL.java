package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyHBL;

@Schema(
    description = "A list of document parties that can be optionally provided in the `House B/L`.",
    title = "Other Document Party (House B/L)")
@Data
public class OtherDocumentPartyHBL {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PartyHBL party;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Specifies the role of the party in a given context. Possible values are:

- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `CS` (Consolidator)
- `MF` (Manufacturer)
- `WH` (Warehouse Keeper)
""",
      example = "DDS",
      maxLength = 3)
  private String partyFunction;
}
