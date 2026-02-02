package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = OtherDocumentParty.CLASS_SCHEMA_DESCRIPTION)
@Data
public class OtherDocumentParty {

  public static final String CLASS_SCHEMA_DESCRIPTION = "A list of document parties that can be optionally provided at booking stage";

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The party information.")
  private Party party;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Specifies the role of the party in a given context. Possible values are:

- `DDR` (Consignor's freight forwarder)
- `DDS` (Consignee's freight forwarder)
- `COW` (Invoice payer on behalf of the consignor (shipper))
- `COX` (Invoice payer on behalf of the consignee)
- `N1` (First Notify Party)
- `N2` (Second Notify Party)
- `NI` (Other Notify Party)
""",
      example = "DDS",
      maxLength = 3)
  private String partyFunction;
}
