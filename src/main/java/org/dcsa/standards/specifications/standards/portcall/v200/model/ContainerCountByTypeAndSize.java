package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
Number of containers by type (laden, empty, plugged reefer, out-of-gauge) or in total,
further categorized by size (20-foot, 40-foot, 45-foot) or in total.
""")
public class ContainerCountByTypeAndSize {

  @Schema(
      description =
"""
The total number of containers (relevant only if specific numbers for each container type are not provided).
""")
  private ContainerCountBySize totalUnits;

  @Schema(description = "The number of laden containers.")
  private ContainerCountBySize ladenUnits;

  @Schema(description = "The number of empty containers.")
  private ContainerCountBySize emptyUnits;

  @Schema(description = "The number of plugged reefer containers.")
  private ContainerCountBySize pluggedReeferUnits;

  @Schema(description = "The number of out-of-gauge containers.")
  private ContainerCountBySize outOfGaugeUnits;
}
