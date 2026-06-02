package org.dcsa.standards.specifications.standards.dx.v100.messages;

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
      example = "The document content type does not match the document name extension",
      description =
"""
Human readable feedback message that can be programmatically relayed to a user of the DX publishing organization
""")
  private String message;

  @Schema(
      maxLength = 1000,
      example = "$.documents[2].contentType",
      description =
"""
[JSONPath](https://github.com/json-path/JsonPath) within the request message of the specific property about which the feedback is provided (if applicable)
""")
  private String propertyPath;
}
