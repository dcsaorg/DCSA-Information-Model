package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

@Schema(description = "Refers to a company or a legal entity. Either `address` or an `identifyingCode` must be provided.")
@Data
public class Party {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the party.", example = "Asseco Denmark", maxLength = 70, pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Address of the party.")
  private PartyAddress address;

  @Schema(
      description =
"""
**Condition:** Either the `address` or a party `identifyingCode` must be provided.
""")
  @ArraySchema(
      schema =
          @Schema(
              description = "Party identifying codes. Either this or address must be provided."))
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  @ArraySchema(schema = @Schema(description = "Tax references for the party."))
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  @ArraySchema(schema = @Schema(description = "A list of contact details"))
  private List<PartyContactDetail> partyContactDetails;

  @Schema(description = "A reference linked to the `Party`.", example = "HHL007", maxLength = 35, pattern = "^\\S(?:.*\\S)?$")
  private String reference;
}
