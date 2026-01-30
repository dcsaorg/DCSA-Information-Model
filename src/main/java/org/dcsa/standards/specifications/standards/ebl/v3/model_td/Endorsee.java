package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.dt.v100.types.DisplayedAddressLine;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress;

@Schema(
    description =
      Endorsee.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Endorsee {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The party to whom the title to the goods is transferred by means of endorsement. Only applicable for negotiable BLs (`isToOrder=true`).";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Physical address of the party.")
  private PartyAddress address;

  @Schema(
      description =
"""
The address of the party to be displayed on the `Transport Document`. The displayed address may be used to match the address provided in the `Letter of Credit`.

**Conditions:** If provided:
  - the displayed address must be included in the `Transport Document`.
  - for physical BL (`isElectronic=false`), it is only allowed to provide max 2 lines of 35 characters
  - for electronic BL (`isElectronic=true`), the limit is 6 lines of 35 characters
  - the order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 6)
  private List<DisplayedAddressLine> displayedAddress;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  @ArraySchema(minItems = 1)
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;
}
