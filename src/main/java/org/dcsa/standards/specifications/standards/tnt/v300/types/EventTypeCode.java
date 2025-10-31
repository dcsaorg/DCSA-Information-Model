package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "EQUIPMENT",
    description = "Code used to denote the type of an event")
@AllArgsConstructor
public enum EventTypeCode implements EnumBase {
  SHIPMENT("Shipment event"),
  TRANSPORT("Transport event"),
  EQUIPMENT("Equipment event"),
  IOT("IoT event"),
  REEFER("Reefer event");

  private final String valueDescription;
}
