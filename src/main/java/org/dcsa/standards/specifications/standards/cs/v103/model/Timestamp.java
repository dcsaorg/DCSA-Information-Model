package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Timestamp defined by a type (Arrival or Departure), a classifier (Planned, Estimated or Actual) and a date and time.
""")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Timestamp extends org.dcsa.standards.specifications.standards.cs.v102.model.Timestamp {

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
}
