package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description =
"""
The party allowed to act on behalf of the shipper for documentation purposes.

**Condition:** Either the `address` or a party `identifyingCode` must be provided in the `Shipping Instructions`.
""")
@Data
public class OnBehalfOfShipper {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Party address.")
  private PartyAddress address;

  @Schema(
      description =
"""
**Condition:** Either the `address` or a party `identifyingCode` must be provided.
""")
  private List<IdentifyingCode> identifyingCodes;
}
