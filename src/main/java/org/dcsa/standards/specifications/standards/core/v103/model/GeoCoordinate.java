package org.dcsa.standards.specifications.standards.core.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An object used to express a location using its geographic coordinates.")
@Data
public class GeoCoordinate {

  @Schema(
      description =
          "Latitude expressed as decimal degrees using the ISO 6709 data interchange numeric format.",
      example = "33.629152",
      maxLength = 20)
  protected String latitude;

  @Schema(
      description =
          "Longitude expressed as decimal degrees using the ISO 6709 data interchange numeric format.",
      example = "-118.587187",
      maxLength = 20)
  protected String longitude;
}
