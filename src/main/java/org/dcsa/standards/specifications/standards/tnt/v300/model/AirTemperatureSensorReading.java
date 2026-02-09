package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Physical reading from one air temperature sensor of a reefer container")
@Data
public class AirTemperatureSensorReading {

  @Schema(
      description = "Sensor temperature expressed in `ReeferDetails.temperatureUnit`.",
      example = "-12.3",
      format = "float")
  protected Double temperatureValue;

  @Schema(
      example = "P16",
      description =
"""
Label of the physical air temperature sensor (a COA property id, a vendor-specific id, or another identifier).
""",
      maxLength = 100)
  private String sensorLabel;
}
