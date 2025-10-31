package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An object used to express a location using `latitude` and `longitude`.")
@Data
public class GeoCoordinate {

  @Schema(
      description =
"""
Geographic coordinate that specifies the north–south position of a point on the Earth's surface.
""",
      example = "48.8585500",
      maxLength = 10)
  protected String latitude;

  @Schema(
      description =
"""
Geographic coordinate that specifies the east–west position of a point on the Earth's surface.
""",
      example = "2.294492036",
      maxLength = 11)
  protected String longitude;
}
