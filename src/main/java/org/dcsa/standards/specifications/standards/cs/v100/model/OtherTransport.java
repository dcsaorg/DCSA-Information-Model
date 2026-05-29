package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Other Transport modes.")
@Data
public class OtherTransport {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The mode of transport as defined by DCSA. The allowed values for the Other Transport mode are:
- `RAIL` (When the transport mode is Rail)
- `TRUCK`(When the transport mode is Truck)
- `RAIL_TRUCK`(When rail and truck are expected to be the mode of transport in a leg of a proposed routing)
- `BARGE_TRUCK`(When barge and truck are expected to be the mode of transport in a leg of a proposed routing)
- `BARGE_RAIL`(When barge and rail are expected to be the mode of transport in a leg of a proposed routing)
- `MULTIMODAL`(When mode of transport is not really defined or unknown at this stage)
""",
      allowableValues = {"RAIL_TRUCK", "BARGE_TRUCK", "BARGE_RAIL", "MULTIMODAL", "RAIL", "TRUCK"},
      example = "RAIL")
  private String modeOfTransport;
}
