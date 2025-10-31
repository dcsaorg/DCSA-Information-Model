package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
Number of containers by size (20-foot, 40-foot, 45-foot) or in total.
""")
public class ContainerCountBySize {

  @Schema(
      format = "int32",
      example = "123",
      description =
"""
The total number of containers (relevant only if specific numbers for each container size are not provided).
""")
  private Integer totalUnits;

  @Schema(format = "int32", example = "123", description = "The number of 20-foot containers.")
  private Integer size20Units;

  @Schema(format = "int32", example = "123", description = "The number of 40-foot containers.")
  private Integer size40Units;

  @Schema(format = "int32", example = "123", description = "The number of 45-foot containers.")
  private Integer size45Units;
}
