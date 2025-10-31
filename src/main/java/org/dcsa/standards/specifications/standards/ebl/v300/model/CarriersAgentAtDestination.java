package org.dcsa.standards.specifications.standards.ebl.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

@Schema(description = "The party on the import side assigned by the carrier to whom the customer should contact for cargo release.")
@Data
public class CarriersAgentAtDestination {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the party.", example = "IKEA Denmark", maxLength = 70, pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Physical address of the agent.")
  private Address address;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of contact details")
  private List<PartyContactDetail> partyContactDetails;
}
