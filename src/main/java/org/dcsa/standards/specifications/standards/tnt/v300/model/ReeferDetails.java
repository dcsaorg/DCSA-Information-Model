package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.tnt.v300.types.ReeferPowerStatusCode;

@Schema(description = "Reefer-specific details")
@Data
public class ReeferDetails {

  @Schema protected ReeferPowerStatusCode powerStatus;

  @Schema(
      description =
"""
Unit in which all temperature values are expressed:
- `CEL` (Celsius)
- `FAH` (Fahrenheit)
""",
      example = "CEL")
  protected String temperatureUnit;

  @Schema(
      description = "Internal setpoint temperature value expressed in `temperatureUnit`",
      example = "-15",
      format = "float")
  protected Double internalSetpointTemperature;

  @Schema(description = "Measurements collected from each temperature probe")
  private List<TemperatureProbeMeasurement> temperatureProbeMeasurements;

  @Schema(
      description = "Ambient measured temperature value expressed in `temperatureUnit`",
      example = "-15",
      format = "float")
  protected Double ambientMeasuredTemperature;

  @Schema(description = "Non-temperature setpoint parameters")
  private ReeferParameters setpointParameters;

  @Schema(description = "Non-temperature measured parameters")
  private ReeferParameters measuredParameters;
}
