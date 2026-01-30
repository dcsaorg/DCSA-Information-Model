package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress;

@Schema(
    description =
"""
The requestor of whoever submits the `Shipping Instructions`.
**Condition:** Either the `address` or a party `identifyingCode` must be provided.
""",
    title = "Shipping Instructions Requestor")
@Data
public class ShippingInstructionsRequestor {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "Asseco Denmark",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema
  private PartyAddress address;

  @Schema
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;
}
