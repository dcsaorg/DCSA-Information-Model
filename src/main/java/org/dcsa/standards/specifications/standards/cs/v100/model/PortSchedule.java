package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Port Schedule information.")
@Data
public class PortSchedule {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private PortScheduleLocation location;

  @Schema() private List<Schedule> vesselSchedules;
}
