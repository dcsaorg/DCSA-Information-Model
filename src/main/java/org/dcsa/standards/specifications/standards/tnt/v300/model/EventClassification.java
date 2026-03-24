package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.EventClassifier;
import org.dcsa.standards.specifications.standards.tnt.v300.types.EquipmentEventType;
import org.dcsa.standards.specifications.standards.tnt.v300.types.EventType;
import org.dcsa.standards.specifications.standards.tnt.v300.types.IotEventType;
import org.dcsa.standards.specifications.standards.tnt.v300.types.ReeferEventType;
import org.dcsa.standards.specifications.standards.tnt.v300.types.ShipmentEventType;
import org.dcsa.standards.specifications.standards.tnt.v300.types.TransportEventType;

@Schema(description = "Type, subtype and classifier of an event")
@Data
public class EventClassification {

  @Schema() private EventType eventType;

  @Schema() private EventClassifier eventClassifier;

  @Schema() private ShipmentEventType shipmentEventType;

  @Schema() private TransportEventType transportEventType;

  @Schema() private EquipmentEventType equipmentEventType;

  @Schema() private IotEventType iotEventType;

  @Schema() private ReeferEventType reeferEventType;
}
