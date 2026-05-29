package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Carbon footprint emission calculation for the full routing solution.")
@Data
public class SolutionFootprint {

  @Schema(
      description = "CO<sub>2</sub> (Carbon Dioxide) emissions in metric tonnes (t).",
      format = "double",
      example = "506.4")
  private Double co2;

  @Schema(
      description = "SO<sub>x</sub> (Sulphur Oxide) emissions in kilograms (kg).",
      format = "double",
      example = "12.4")
  private Double sox;

  @Schema(
      description = "NO<sub>x</sub> (Nitrogen Oxide) emissions in kilograms (kg).",
      format = "double",
      example = "15.4")
  private Double nox;

  @Schema(
      description = "PM<sub>10</sub> (Small Particle) emissions in kilograms (kg).",
      format = "double",
      example = "0.8")
  private Double pm10;
}
