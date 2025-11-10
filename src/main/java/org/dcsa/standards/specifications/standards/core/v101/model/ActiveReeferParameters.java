package org.dcsa.standards.specifications.standards.core.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
Parameters of a Reefer equipment, usable as setpoint or as measured values.
""")
@Data
public class ActiveReeferParameters {

  @Schema(
      description = "Internal temperature value expressed in `temperatureUnit`",
      example = "-15",
      format = "float")
  protected Double temperatureValue;

  @Schema(
      description =
"""
Unit in which the `temperatureValue` and `ambientTemperatureValue` are expressed:
- `CEL` (Celsius)
- `FAH` (Fahrenheit)
""",
      example = "CEL")
  protected String temperatureUnit;

  @Schema(
      description = "Ambient temperature value expressed in `temperatureUnit`",
      example = "-15",
      format = "float")
  protected Double ambientTemperatureValue;

  @Schema(description = "O<sub>2</sub> percentage", example = "25", format = "float")
  protected Double o2Percentage;

  @Schema(description = "CO<sub>2</sub> percentage", example = "25", format = "float")
  protected Double co2Percentage;

  @Schema(description = "Humidity percentage", example = "50", format = "float")
  protected Double humidityPercentage;

  @Schema(
      description = "Air exchange rate expressed in `airExchangeUnit`",
      example = "15.4",
      format = "float")
  protected Double airExchangeValue;

  @Schema(
      description =
"""
Unit in which the `airExchangeValue` is expressed:
- `MQH` (Cubic metre per hour)
- `FQH` (Cubic foot per hour)
""",
      example = "MQH")
  protected String airExchangeUnit;

  @Schema(description = "Flag indicating whether the ventilation orifice is open", example = "true")
  protected Boolean isVentilationOpen;

  @Schema(description = "Flag indicating whether drainholes are open", example = "true")
  protected Boolean isDrainholesOpen;

  @Schema(description = "Flag indicating whether bulb mode is active", example = "true")
  protected Boolean isBulbMode;

  @Schema(
      description =
"""
Flag indicating whether cargo cold treatment is required prior to loading at origin,
or during transit but prior arrival at POD.
""",
      example = "true")
  protected Boolean isColdTreatmentRequired;

  @Schema(
      description = "Flag indicating whether controlled atmosphere is required",
      example = "true")
  protected Boolean isControlledAtmosphereRequired;
}
