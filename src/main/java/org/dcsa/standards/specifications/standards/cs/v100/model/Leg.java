package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "Leg of the Point-to-Point routing. The property `Leg` can be conformed by as many leg as needed and this leg must be identified with a sequence number.")
@Data
public class Leg {

  @Schema(description = "Sequence number of the leg.", format = "int32", example = "1")
  private Integer sequenceNumber;

  @Schema(
      description = "The mode of transport as defined by DCSA.",
      oneOf = {VesselTransport.class, BargeTransport.class, OtherTransport.class})
  private Object transport;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PlaceOfDeparture departure;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PlaceOfArrival arrival;
}
