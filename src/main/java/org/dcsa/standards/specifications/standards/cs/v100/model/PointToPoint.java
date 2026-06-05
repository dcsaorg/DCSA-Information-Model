package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
public class PointToPoint {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PlaceOfReceipt placeOfReceipt;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PlaceOfDelivery placeOfDelivery;

  @Schema(
      description =
"""
Indicates the type of service offered at `Origin`. The options are:
- `CY` (Container yard (incl. rail ramp))
- `SD` (Store Door)
- `CFS` (Container Freight Station)
""",
      allowableValues = {"CY", "SD", "CFS"},
      maxLength = 3,
      example = "CY")
  private String receiptTypeAtOrigin;

  @Schema(
      description =
"""
Indicates the type of service offered at `Destination`. The options are:
- `CY` (Container yard (incl. rail ramp))
- `SD` (Store Door)
- `CFS` (Container Freight Station)
""",
      allowableValues = {"CY", "SD", "CFS"},
      maxLength = 3,
      example = "CY")
  private String deliveryTypeAtDestination;

  @Schema(
      description =
          "A list of cut-offs times provided by the carrier when available. A cut-off time indicates the latest date and time by which a task must be completed. For example, the latest date and time by which a container must be delivered to a terminal to be loaded on a vessel, or where certain documentation must be provided by the Shipper.")
  private List<CutOffTime> cutOffTimes;

  @Schema(
      description =
          "Solution number, starting with 1. Used to group and identify similar or same routings in the response as per the carrier commercial definitions.",
      format = "int32",
      minimum = "1",
      example = "1")
  private Integer solutionNumber;

  @Schema(
      description =
          "The estimated total time in days that it takes a shipment to move from place of receipt to place of delivery. Transit time includes stop-over time during transhipments and waiting time at connection points, if applicable, thus can vary between the same locations.",
      format = "int32",
      example = "10")
  private Integer transitTime;

  @ArraySchema(minItems = 1)
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private List<Leg> legs;
}
