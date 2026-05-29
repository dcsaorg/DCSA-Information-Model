package org.dcsa.standards.specifications.standards.cs.v101.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Error Response.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ErrorResponse
    extends org.dcsa.standards.specifications.standards.cs.v100.messages.ErrorResponse {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The DateTime corresponding to the error occurring.",
      format = "date-time",
      example = "2019-11-12T07:41:00Z")
  private String errorDateTime;
}
