package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.types.DisplayedAddressLine;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;

@Schema(
    description =
"""
The party to whom the title to the goods is transferred by means of endorsement.

**Condition:** Can only be provided for negotiable BLs (`isToOrder=true`). If a negotiable BL does not have an `Endorsee`, the BL is said to be "blank endorsed". Note `Consignee` and `Endorsee` are mutually exclusive.

**Condition:** Either the `address` or a party `identifyingCode` must be provided in the `Shipping Instructions`. If a `displayedAddress` is provided, it must be included in the `Transport Document` instead of the `address`.
""",
    title = "Endorsee (Shipper provided)")
@Data
public class EndorseeShipper {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema
  private PartyAddress address;

  @Schema(
      description =
"""
The address of the party to be displayed on the `Transport Document`. The displayed address may be used to match the address provided in the `Letter of Credit`.

**Conditions:** If provided:
  - the displayed address must be included in the `Transport Document`.
  - for physical BL (`isElectronic=false`), it is only allowed to provide max 2 lines of 35 characters. **Note:** Some carriers may choose to allow more lines, please consult the carrier's API documentation to check if this is the case.
  - for electronic BL (`isElectronic=true`), the limit is 6 lines of 35 characters
  - the order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(
      maxItems = 6,
      schema = @Schema(description = "A single address line", example = "Strawinskylaan 4117", maxLength = 35))
  private List<DisplayedAddressLine> displayedAddress;

  @Schema(description = "**Condition:** Either the `address` or a party `identifyingCode` must be provided.")
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;
}
