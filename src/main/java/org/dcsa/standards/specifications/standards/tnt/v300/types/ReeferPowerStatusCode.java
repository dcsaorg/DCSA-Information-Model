package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "MEAS",
    description = "Code indicating the power status of a reefer container")
@AllArgsConstructor
public enum ReeferPowerStatusCode implements EnumBase {
  INACTIVE(
      "The reefer container is inactive (not operational / not commissioned). Power availability is not assumed and refrigeration is not running."),
  POWER_OFF(
      "The reefer container is connected to a power source but switched off. Refrigeration is not running."),
  POWER_ON(
      "The reefer container is connected to a power source and powered on. Refrigeration is running."),
  UNKNOWN("The power state of the reefer container cannot be determined from the available data.");

  private final String valueDescription;
}
