package org.dcsa.standards.specifications.standards.portcall.v200.messages;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    maxLength = 50,
    example = "ERROR",
    description =
"""
Code denoting the severity of a `FeedbackElement`
""")
@AllArgsConstructor
public enum FeedbackSeverityCode implements EnumBase {
  ERROR("Error that fully or partially prevents the correct processing of the request"),
  WARN("Warning about the request being processed in a potentially unexpected way"),
  INFO("Informational message about how the request is processed");

  private final String valueDescription;
}
