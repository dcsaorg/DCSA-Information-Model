package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Location;
import org.dcsa.standards.specifications.standards.core.v103.types.FormattedDateTime;

@Data
@Schema(description = "Track and Trace event")
public class Event {

  @Schema() private EventRouting eventRouting;

  @Schema(
      maxLength = 500,
      example = "event-HHL71800000-APZU4812090-IoT-DRO-2025-01-23T01:23:45Z",
      description =
"""
ID of the event, unique among all the events published by a T&T Event Producer.

An event overrides any other event that has the same `eventID` and an earlier `eventUpdatedDateTime`.

Each event is uniquely identified within each T&T 3.x standard ecosystem of connected implementers
by a composite key including:
- `eventRouting.originatingParty.partyCode`
- `eventRouting.originatingParty.codeListProvider`
- `eventRouting.originatingParty.codeListName`
- `eventID`
""")
  private String eventID;

  @Schema(
      description =
"""
Flag indicating that the event is retracted.

The data in this and all previously transmitted events with the same `eventID` must be discarded or ignored.

If this flag is set, any event data other than the `eventID` is irrelevant (if present).
""")
  private Boolean isRetracted;

  @Schema(description = "The date and time when the real-world event has occurred or will occur.")
  private FormattedDateTime eventDateTime;

  @Schema(description = "The date and time when the technical event was last updated.")
  private FormattedDateTime eventUpdatedDateTime;

  @Schema(description = "The location of the event.")
  private Location eventLocation;

  @Schema() private EventClassification eventClassification;

  @Schema() private ShipmentDetails shipmentDetails;

  @Schema() private TransportDetails transportDetails;

  @Schema() private EquipmentDetails equipmentDetails;

  @Schema() private IotDetails iotDetails;

  @Schema() private ReeferDetails reeferDetails;
}
