package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.model.EmptyContainerDepotReleaseLocation;

@Schema(description = OriginEmptyContainerPickup.CLASS_SCHEMA_DESCRIPTION)
@Data
public class OriginEmptyContainerPickup {

  public static final String CLASS_SCHEMA_DESCRIPTION =
"""
The date, time and location of the depot from which the empty container(s) will be released from.

**Note:** Applicable to **all** `Receipt type at origin`.
""";

  @Schema(
      description =
          "The date and time for the pick-up of the empty container(s) at the Empty container depot release location, if provided.",
      example = "2024-09-04T09:41:00Z",
      format = "date-time")
  private String dateTime;

  @Schema(
      description =
          "The location of the depot from which the empty container(s) will be released from")
  private EmptyContainerDepotReleaseLocation depotReleaseLocation;
}
