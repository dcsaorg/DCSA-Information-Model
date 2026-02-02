package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = ContainerPositioning.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ContainerPositioning {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An object to capture the `Location` together with an optional `Date and Time`.";

  @Schema(
      description = "The date and time requested by the shipper for the positioning of the container(s) at the Container positioning location (`CPO`), if provided, or the Place of Receipt (`PRE`) if `CPO` location is not provided.",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String dateTime;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The container positioning location.")
  private ContainerPositioningLocation location;
}
