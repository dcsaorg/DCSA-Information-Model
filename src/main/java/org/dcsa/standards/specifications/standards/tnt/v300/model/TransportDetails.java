package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v104.types.DelayReasonCode;

import java.util.List;

@Schema(description = "Transport-specific details")
@Data
public class TransportDetails {

  @Schema(description = "Codes identifying the reasons for a delay")
  private List<DelayReasonCode> delayReasonCodes;

  @Schema() private TransportCall transportCall;
}
