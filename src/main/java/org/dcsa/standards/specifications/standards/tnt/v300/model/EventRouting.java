package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v104.model.Party;

@Schema(
    description =
"""
Routing of an event from the originating party (or parties), optionally via one or more forwarding parties, to the destination party (or parties).
""")
@Data
public class EventRouting {

  @Schema(description = "The party (or parties) with which the event (data) originated.")
  private List<Party> originatingParties;

  @Schema(
      description =
          "Ordered list of parties forwarding the event between the originating and destination party or parties.")
  private List<Party> forwardingParties;

  @Schema(description = "The party (or parties) intended as final recipients of the event.")
  private List<Party> destinationParties;
}
