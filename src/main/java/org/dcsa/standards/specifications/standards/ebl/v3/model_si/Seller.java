package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyContactDetailWithPattern;

@Schema(
    description =
"""
The seller is the last known entity by whom the goods are sold or agreed to be sold to the buyer. If the goods are to be imported otherwise than in pursuance of a purchase, the details of the owner of the goods shall be provided.

**Condition:** Buyer and Seller are mandatory if `isCargoDeliveredInICS2Zone=true` and `manifestTypeCode='ENS'` and `advancedManifestFilingPerformedBy='CARRIER'` and `isHouseBillOfLadingsIssued=false`.
""",
    title = "Seller")
@Data
public class Seller {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Can be one of the following values as per the Union Customs Code art. 5(4):
- `NATURAL_PERSON` (A person that is an individual living human being)
- `LEGAL_PERSON` (person (including a human being and public or private organizations) that can perform legal actions, such as own a property, sue and be sued)
- `ASSOCIATION_OF_PERSONS` (Not a legal person, but recognised under Union or National law as having the capacity to perform legal acts)
""",
      example = "NATURAL_PERSON",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50)
  private String typeOfPerson;

  @Schema
  private Address address;

  @Schema
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetailWithPattern> partyContactDetails;
}
