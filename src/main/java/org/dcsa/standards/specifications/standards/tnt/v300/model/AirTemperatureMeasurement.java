package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(
    description =
"""
Air temperature measurement for a reefer container, supporting a logical / primary value and optional physical sensor readings.
""")
@Data
public class AirTemperatureMeasurement {

  @Schema(
      description = "Logical / primary temperature (e.g., controller value) expressed in `ReeferDetails.temperatureUnit`.",
      example = "-12.3",
      format = "float")
  protected Double temperatureValue;

  @Schema protected List<AirTemperatureSensorReading> sensorReadings;
}
