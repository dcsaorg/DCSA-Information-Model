package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.FormattedDateTime;
import org.dcsa.standards.specifications.standards.core.v102.types.UniversallyUniqueID;

@Data
@Schema(
    description =
"""
Generic event used to exchange timestamps, move forecasts and related port call service information
between service providers and service consumers.
""")
public class Event {

  @Schema(description = "Universal unique identifier of the event")
  private UniversallyUniqueID eventID;

  @Schema(
      description =
"""
ID of an earlier event to which this event is a reply.

Used when sending a timestamp as a reply to another timestamp as part of
[the "ERP" pattern defined by the IMO](https://wwwcdn.imo.org/localresources/en/OurWork/Facilitation/FAL%20related%20nonmandatory%20documents/FAL.5-Circ.52.pdf).
""")
  private UniversallyUniqueID replyToEventID;

  @Schema(description = "The date and time when the technical event was last updated.")
  private FormattedDateTime eventUpdatedDateTime;

  @Schema(
      example = "false",
      description =
"""
Flag indicating that this event is primarily meant for another party - but is sent as a FYI (for your information).
""")
  private Boolean isFYI;

  @Schema() private PortCall portCall;

  @Schema() private TerminalCall terminalCall;

  @Schema() private PortCallService portCallService;

  @Schema() private Vessel vessel;

  @Schema() private Timestamp timestamp;

  @Schema(
      description =
          "The moves forecasts of a port call service with `portCallServiceTypeCode='MOVES'`")
  private List<MovesForecast> movesForecasts;
}
