package org.dcsa.standards.specifications.standards.cs.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "The Location specifying where the place of arrival is located.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaceOfArrival
    extends org.dcsa.standards.specifications.standards.cs.v100.model.PlaceOfArrival {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The local date and time, when the event will take place.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String dateTime;
}
