package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.ActiveReeferSettings
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ActiveReeferSettings
    extends org.dcsa.standards.specifications.standards.dt.v100.model.ActiveReeferSettings {

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
      "Target value of the temperature for the Reefer based on the cargo requirement.",
    example = "-15",
    format = "float")
  protected Double temperatureSetpoint;

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
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
      description = "Indicator whether reefer container should be pre-cooled to the temperature setting required at time of release from depot",
      example = "true")
  private Boolean isPreCoolingRequired;

  @Schema(
      description = "Indicator whether reefer container should have a generator set attached at time of release from depot",
      example = "true")
  private Boolean isGeneratorSetRequired;
}
