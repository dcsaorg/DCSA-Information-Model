package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description = """
Refers to a company or a legal entity.
""",
    title = "Party (House B/L)",
    requiredProperties = {"partyName"})
@Data
public class PartyHBL {

  @Schema(
      description = """
Name of the party.
""",
      example = "Asseco Denmark",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 70)
  private String partyName;

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

  @Schema(
      description = """
A reference linked to the `Party`.
""",
      example = "HHL007",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String reference;
}
