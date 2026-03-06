package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    maxLength = 20,
    example = "POWER_ON",
    description = "Code indicating the power status of an IoT device or reefer container")
@AllArgsConstructor
public enum PowerStatusCode implements EnumBase {
  INACTIVE("The device is inactive (not operational / not commissioned)."),
  POWER_OFF("The device is connected to a power source but switched off."),
  POWER_ON("The device is connected to a power source and powered on."),
  UNKNOWN("The power status of the device could not be determined.");

  private final String valueDescription;
}
