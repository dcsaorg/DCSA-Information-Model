package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "Estimated footprint emission values for this particular leg.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Footprint extends org.dcsa.standards.specifications.standards.cs.v102.model.Footprint {

  @Schema(
      description = "CO<sub>2</sub>e (Carbon Dioxide equivalent) emissions in metric tonnes (t).",
      format = "double",
      example = "520.7")
  private Double co2e;
}
