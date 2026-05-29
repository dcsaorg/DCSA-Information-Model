package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "Point to Point routing information.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PointToPoint
    extends org.dcsa.standards.specifications.standards.cs.v102.model.PointToPoint {

  @Schema(description = "Estimated footprint emission calculation for the full routing solution.")
  private SolutionFootprint solutionFootprint;
}
