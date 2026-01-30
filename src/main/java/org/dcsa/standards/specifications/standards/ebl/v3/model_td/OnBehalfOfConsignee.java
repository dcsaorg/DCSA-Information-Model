package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress;

@Schema(
    description =
"""
The party allowed to act on behalf of the consignee for documentation purposes.

**Condition:** Either the `address` or a party `identifyingCode` must be provided in the `Shipping Instructions`.
""")
@Data
public class OnBehalfOfConsignee {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Party address.")
  private PartyAddress address;

  @Schema()
  @ArraySchema(minItems = 1)
  private List<IdentifyingCode> identifyingCodes;
}
