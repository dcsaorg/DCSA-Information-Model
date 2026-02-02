package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = ContainerPositioningEstimated.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ContainerPositioningEstimated {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An object to capture the `Location` together with an optional `Date and Time`.";

  @Schema(
      description = "The estimated date and time for the positioning of the container(s) at the `Container Positioning Location` (CPO), if provided, or the `Place of Receipt` (PRE) if CPO location is not provided.\n\n**Condition:** Only applicable to carrier haulage service at origin (`Receipt type at origin = 'SD'`).",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String estimatedDateTime;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The container positioning location.")
  private ContainerPositioningLocation location;
}
