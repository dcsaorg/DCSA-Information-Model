package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v104.types.FormattedDateTime;
import org.dcsa.standards.specifications.standards.tnt.v300.types.PowerStatusCode;

@Schema(description = "Reefer-specific details")
@Data
public class ReeferDetails {

  @Schema protected PowerStatusCode powerStatus;

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

  @Schema(description = "Supply air temperature measurement")
  private AirTemperatureMeasurement supplyAirTemperatureMeasurement;

  @Schema(description = "Return air temperature measurements")
  private AirTemperatureMeasurement returnAirTemperatureMeasurement;

  @Schema(
      description = "Ambient measured temperature value expressed in `temperatureUnit`",
      example = "-15",
      format = "float")
  protected Double ambientMeasuredTemperature;

  @Schema(description = "Non-temperature setpoint parameters")
  private ReeferParameters setpointParameters;

  @Schema(description = "Non-temperature measured parameters")
  private ReeferParameters measuredParameters;

  @Schema(description = "The date and time when the last defrost was completed")
  private FormattedDateTime lastDefrostDateTime;

  @Schema(description = "The list of alarms that are active, or that were active within a relevant time frame")
  private List<ReeferAlarm> alarms;

  @Schema(description = "The number of alarms that are currently active")
  private Integer numberOfActiveAlarms;

  @Schema(maxLength = 100, description = "Manufacturer name of the reefer unit")
  private String unitManufacturer;

  @Schema(maxLength = 100, description = "Model of the reefer unit")
  private String unitModel;

  @Schema(maxLength = 100, description = "Model of the reefer controller")
  private String controllerModel;
}
