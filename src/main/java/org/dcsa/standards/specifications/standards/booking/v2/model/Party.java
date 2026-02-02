package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;

@Schema(description = Party.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Party {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Refers to a company or a legal entity.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(description = "Party address.")
  private PartyAddress address;

  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;

  @Schema(
      description = "A reference linked to the `Party`.",
      example = "HHL007",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  private String reference;
}
