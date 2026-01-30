package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.GeoCoordinate
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class GeoCoordinate
    extends org.dcsa.standards.specifications.standards.dt.v100.model.GeoCoordinate {

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
