package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = ActiveReeferSettings.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ActiveReeferSettings {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "The specifications for a Reefer equipment. Only applicable when `isNonOperatingReefer` is false.";

  @Schema(
      description =
          "Target value of the temperature for the Reefer based on the cargo requirement.",
      example = "-15",
      format = "float")
  protected Double temperatureSetpoint;

  @Schema(
      description =
"""
The unit for temperature in Celsius or Fahrenheit

- `CEL` (Celsius)
- `FAH` (Fahrenheit)

**Condition:** Mandatory if `temperatureSetpoint` is provided. If `temperatureSetpoint` is not provided, this field must be empty.
""",
      example = "CEL")
  protected String temperatureUnit;

  @Schema(
      description = "The percentage of the controlled atmosphere O<sub>2</sub> target value",
      example = "25",
      minimum = "0",
      maximum = "100",
      format = "float")
  protected Double o2Setpoint;

  @Schema(
      description = "The percentage of the controlled atmosphere CO<sub>2</sub> target value",
      example = "25",
      minimum = "0",
      maximum = "100",
      format = "float")
  protected Double co2Setpoint;

  @Schema(
      description = "The percentage of the controlled atmosphere humidity target value",
      example = "95.6",
      minimum = "0",
      maximum = "100",
      format = "float")
  protected Double humiditySetpoint;

  @Schema(
      description =
"""
Target value for the air exchange rate which is the rate at which outdoor air replaces indoor air within a Reefer container
""",
      example = "15.4",
      minimum = "0",
      format = "float")
  protected Double airExchangeSetpoint;

  @Schema(
      description =
"""
The unit for `airExchange` in metrics- or imperial- units per hour
- `MQH` (Cubic metre per hour)
- `FQH` (Cubic foot per hour)

**Condition:** Mandatory if `airExchange` is provided. If `airExchange` is not provided, this field must be empty.
""",
      example = "MQH")
  protected String airExchangeUnit;

  @Schema(
      description =
          "If `true` the ventilation orifice is `Open` - if `false` the ventilation orifice is `closed`",
      example = "true")
  protected Boolean isVentilationOpen;

  @Schema(description = "Is drain holes open on the container", example = "true")
  protected Boolean isDrainholesOpen;

  @Schema(
      description = "Is special container setting for handling flower bulbs active",
      example = "true")
  protected Boolean isBulbMode;

  @Schema(
      description =
          "Indicator whether cargo requires cold treatment prior to loading at origin or during transit, but prior arrival at POD",
      example = "true")
  protected Boolean isColdTreatmentRequired;

  @Schema(
      description = "Indicator of whether cargo requires Controlled Atmosphere.",
      example = "true")
  protected Boolean isControlledAtmosphereRequired;
}
