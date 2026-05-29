package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Vessel schedule information including transport calls.")
@Data
public class VesselSchedule {

  @Schema() private Vessel vessel;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "This property can be set to `true` when no vessel has been assigned, indicating that the `vesselIMONumber` does not exist.")
  private Boolean isDummyVessel;

  @Schema() private List<TransportCall> transportCalls;
}
