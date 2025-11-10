package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.ActiveReeferParameters;

@Schema(description = "Reefer-specific details")
@Data
public class ReeferDetails {

  @Schema(description = "Setpoint active reefer parameters")
  private ActiveReeferParameters setpointParameters;

  @Schema(description = "Measured active reefer parameters")
  private ActiveReeferParameters measuredParameters;
}
