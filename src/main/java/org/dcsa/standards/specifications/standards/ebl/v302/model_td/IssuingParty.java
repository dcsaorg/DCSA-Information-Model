package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;

@Schema(description = "The company or a legal entity issuing the `Transport Document`.")
@Data
public class IssuingParty {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the party.", example = "Asseco Denmark", maxLength = 70, pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Physical address of the issuing party.")
  private PartyAddress address;

  @Schema()
  private List<IdentifyingCode> identifyingCodes;

  @Schema(description = "A list of `Tax References` for a `Party`")
  private List<TaxLegalReference> taxLegalReferences;

  @Schema(description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;
}
