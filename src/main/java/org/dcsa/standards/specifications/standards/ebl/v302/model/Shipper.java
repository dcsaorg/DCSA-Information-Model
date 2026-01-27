package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.types.DisplayedAddressLine;
import org.dcsa.standards.specifications.standards.ebl.v302.types.PurchaseOrderReference;

@Schema(description = Shipper.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Shipper {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Party by whom or in whose name a contract of carriage by sea has been concluded. May also be the party who delivers the goods to the carrier.";

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the party.", example = "IKEA Denmark", maxLength = 70, pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(
      description =
"""
Can be one of the following values as per the Union Customs Code art. 5(4):
- `NATURAL_PERSON` (A person that is an individual living human being)
- `LEGAL_PERSON` (person (including a human being and public or private organizations) that can perform legal actions, such as own a property, sue and be sued)
- `ASSOCIATION_OF_PERSONS` (Not a legal person, but recognised under Union or National law as having the capacity to perform legal acts)
""",
      example = "NATURAL_PERSON",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String typeOfPerson;

  @Schema(description = "Party address.")
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
  @ArraySchema(maxItems = 6)
  private List<DisplayedAddressLine> displayedAddress;

  @Schema(
      description =
"""
**Condition:** Either the `address` or a party `identifyingCode` must be provided.
""")
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;

  @Schema(description = "A reference linked to the `Shipper`.", example = "HHL007", maxLength = 35, pattern = "^\\S(?:.*\\S)?$")
  private String reference;

  @Schema(description = "A list of `Purchase Order Reference`s linked to the `Shipper`.")
  private List<PurchaseOrderReference> purchaseOrderReferences;
}
