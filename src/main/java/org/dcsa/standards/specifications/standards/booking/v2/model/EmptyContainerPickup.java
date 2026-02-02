package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = EmptyContainerPickup.CLASS_SCHEMA_DESCRIPTION)
@Data
public class EmptyContainerPickup {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The date and time and location for the empty container pick-up.\n\n**Condition:** Only applicable to merchant haulage service at origin (`Receipt type at origin = 'CY'`).";

  @Schema(
      description = "The date and time for the pick-up of the empty container(s) at the Empty container depot release location, if provided.",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String dateTime;

  @Schema(description = "The location of the depot from which the empty container(s) will be released from")
  private EmptyContainerDepotReleaseLocation depotReleaseLocation;
}
