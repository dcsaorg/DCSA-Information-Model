package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Limits.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Limits {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Limits for the `Dangerous Goods`. The same `Temperature Unit` must apply to all attributes in this structure.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unit for **all attributes in the limits structure** in Celsius or Fahrenheit
- `CEL` (Celsius)
- `FAH` (Fahrenheit)
""",
      example = "CEL")
  protected String temperatureUnit;

  @Schema(
      description =
"""
Lowest temperature at which a chemical can vaporize to form an ignitable mixture in air.

**Condition:** only applicable to specific hazardous goods according to the IMO IMDG Code.
""",
      example = "42.0",
      format = "float")
  protected Double flashPoint;

  @Schema(
      description =
"""
Maximum temperature at which certain substance (such as organic peroxides and self-reactive and related substances) can be safely transported for a prolonged period.
""",
      example = "24.1",
      format = "float")
  protected Double transportControlTemperature;

  @Schema(
      description = "Temperature at which emergency procedures shall be implemented",
      example = "74.1",
      format = "float")
  protected Double transportEmergencyTemperature;

  @Schema(
      name = "SADT",
      description =
          "Lowest temperature in which self-accelerating decomposition may occur in a substance",
      example = "54.1",
      format = "float")
  protected Double sadt;

  @Schema(
      name = "SAPT",
      description =
          "Lowest temperature in which self-accelerating polymerization may occur in a substance",
      example = "70.0",
      format = "float")
  protected Double sapt;
}
