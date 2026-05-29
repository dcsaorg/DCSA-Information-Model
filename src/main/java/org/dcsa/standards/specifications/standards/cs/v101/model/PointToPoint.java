package org.dcsa.standards.specifications.standards.cs.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Point to Point routing information.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PointToPoint
    extends org.dcsa.standards.specifications.standards.cs.v100.model.PointToPoint {

  @Schema(
      description =
"""
A reference to be used when creating a **Booking** in the **Booking API**.
""",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 5000,
      example = "Route123")
  private String routingReference;
}
