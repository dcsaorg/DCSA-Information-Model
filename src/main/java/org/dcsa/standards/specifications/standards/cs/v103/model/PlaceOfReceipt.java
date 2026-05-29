package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "The Location specifying where the place of receipt is located.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaceOfReceipt
    extends org.dcsa.standards.specifications.standards.cs.v100.model.PlaceOfReceipt {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The local date and time, when the event will take place.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String dateTime;
}
