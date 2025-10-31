package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.types.DelayReasonCode;

import java.util.List;

@Schema(description = "Transport-specific details")
@Data
public class TransportDetails {

  @Schema(description = "Codes identifying the reasons for a delay")
  private List<DelayReasonCode> delayReasonCodes;

  @Schema(
      example = "Bad weather",
      description =
"""
Free text information provided by the vessel operator regarding the reasons for the change in schedule
and/or plans to mitigate schedule slippage.
""",
      maxLength = 250)
  private String changeRemark;

  @Schema() private TransportCall transportCall;
}
