package org.dcsa.standards.specifications.standards.portcall.v200.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
An individual piece of feedback (potentially out of several) providing information on how an API request is processed
""")
public class FeedbackElement {

  @Schema() private FeedbackSeverityCode severity;

  @Schema(
      maxLength = 5000,
      example = "The facility SMDG code does not match the location UN/LOCODE",
      description =
"""
Human readable feedback message that can be programmatically relayed to a user of the event publishing organization
""")
  private String message;

  @Schema(
      maxLength = 1000,
      example = "$.events[2].location",
      description =
"""
[JSONPath](https://github.com/json-path/JsonPath) within the request message of the specific property
about which the feedback is provided (if applicable)
""")
  private String propertyPath;
}
