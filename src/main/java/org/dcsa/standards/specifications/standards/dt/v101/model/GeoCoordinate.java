package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = GeoCoordinate.CLASS_SCHEMA_DESCRIPTION)
@Data
public class GeoCoordinate {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "An object used to express a location using `latitude` and `longitude`.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Geographic coordinate that specifies the north–south position of a point on the Earth's surface.
""",
      example = "48.8585500",
      maxLength = 10)
  protected String latitude;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Geographic coordinate that specifies the east–west position of a point on the Earth's surface.
""",
      example = "2.294492036",
      maxLength = 11)
  protected String longitude;
}
