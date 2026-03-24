package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "EQUIPMENT",
    description = "The type of a Track and Trace event")
@AllArgsConstructor
public enum EventType implements EnumBase {
  SHIPMENT("Shipment event"),
  TRANSPORT("Transport event"),
  EQUIPMENT("Equipment event"),
  IOT("IoT event"),
  REEFER("Reefer event");

  private final String valueDescription;
}
