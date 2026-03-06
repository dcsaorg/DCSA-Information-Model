package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "TELEMETRY",
    description = "Code used to denote the type of an IoT event")
@AllArgsConstructor
public enum IotEventTypeCode implements EnumBase {
  PAIRING("The IoT device is being paired with the equipment"),
  TELEMETRY("The IoT device is emitting telemetry data"),
  UNPAIRING("The IoT device is being unpaired from the equipment");

  private final String valueDescription;
}
