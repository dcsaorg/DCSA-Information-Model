package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PointToPoint
    extends org.dcsa.standards.specifications.standards.cs.v101.model.PointToPoint {

  @Schema(description = "Carbon footprint emission calculation for the full routing solution.")
  private SolutionFootprint solutionFootprint;
}
