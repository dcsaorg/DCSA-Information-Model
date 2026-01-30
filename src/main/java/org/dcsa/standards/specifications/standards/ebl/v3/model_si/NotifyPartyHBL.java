package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyContactDetailHBL;

@Schema(
    description = """
The person to be notified when a shipment arrives at its destination.

**Condition:** Mandatory for To Order HBLs (HouseBL `isToOrder=true`)
""",
    title = "Notify Party (House B/L)",
    requiredProperties = {"partyName", "partyContactDetails", "typeOfPerson"})
@Data
public class NotifyPartyHBL {

  @Schema(
      description = """
Name of the party.
""",
      example = "IKEA Denmark",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

  @Schema(
      description = """
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

  @Schema(description = """
A list of `Tax References` for a `Party`
""")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = """
A list of contact details
""")
  private List<PartyContactDetailHBL> partyContactDetails;
}
