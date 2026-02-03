package org.dcsa.standards.specifications.standards.booking.v203.model;

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
Latitude expressed as decimal degrees using the ISO 6709 data interchange numeric format.
""",
      example = "48.8585500",
      maxLength = 10)
  protected String latitude;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Longitude expressed as decimal degrees using the ISO 6709 data interchange numeric format.
""",
      example = "2.294492036",
      maxLength = 11)
  protected String longitude;
}
