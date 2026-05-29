package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "Limits for the `Dangerous Goods`. The same `Temperature Unit` must apply to all attributes in this structure.")
@Data
public class Limits {

  @Schema(
      name = "SADT",
      description =
          "Lowest temperature in which self-accelerating decomposition may occur in a substance",
      example = "54.1",
      format = "float")
  private Double sadt;

  @Schema(
      name = "SAPT",
      description =
          "Lowest temperature in which self-accelerating polymerization may occur in a substance",
      example = "70.0",
      format = "float")
  private Double sapt;

  @Schema(
      description =
          "Lowest temperature at which a chemical can vaporize to form an ignitable mixture in air.",
      example = "42.0",
      format = "float")
  private Double flashPoint;

  @Schema(
      description =
"""
The unit for **all attributes in the limits structure** in Celsius or Fahrenheit

- `CEL` (Celsius)
- `FAH` (Fahrenheit)
""",
      example = "CEL",
      maxLength = 3)
  private String temperatureUnit;

  @Schema(
      description =
          "Maximum temperature at which certain substance (such as organic peroxides and self-reactive and related substances) can be safely transported for a prolonged period.",
      example = "24.1",
      format = "float")
  private Double transportControlTemperature;

  @Schema(
      description = "Temperature at which emergency procedures shall be implemented",
      example = "74.1",
      format = "float")
  private Double transportEmergencyTemperature;
}
