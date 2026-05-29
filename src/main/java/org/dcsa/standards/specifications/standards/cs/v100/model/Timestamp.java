package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Timestamp information for transport events.")
@Data
public class Timestamp {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Identifier for type of transportEvent.

- `ARRI` (Arrived)
- `DEPA` (Departed)
""",
      allowableValues = {"ARRI", "DEPA"},
      example = "ARRI")
  private String eventTypeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code for the event classifier. Values can vary depending on eventType.

Possible values are:
- `ACT` (Actual)
- `EST` (Estimated)
- `PLN` (Planned)
""",
      allowableValues = {"PLN", "EST", "ACT"},
      example = "PLN")
  private String eventClassifierCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The local date and time, when the event takes place, in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format specifying the offset.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String eventDateTime;
}
