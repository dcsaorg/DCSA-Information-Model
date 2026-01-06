package org.dcsa.standards.specifications.standards.vgm.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Party;

@Schema(
    description =
"""
Technical routing of a VGM declaration from the originating party, via forwarding parties, to the destination party.
""")
@Data
public class RoutingDetails {

  @Schema(
      description =
"""
The initial party with which the VGM declaration originated.
""")
  private Party originatingParty;

  @Schema(
      description =
"""
Ordered list of parties forwarding the VGM declaration between the originating and destination party.
""")
  private List<Party> forwardingParties;

  @Schema(
      description =
"""
The final party intended as destination of the VGM declaration.
""")
  private Party destinationParty;
}
