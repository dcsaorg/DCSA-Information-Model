package org.dcsa.standards.specifications.standards.dx.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `GET document feedbacks` request
""")
public class GetDocumentFeedbacksResponse {

  @Schema(
      description =
"""
The list of document feedback elements matching the filter specified by the request query parameters
""")
  private List<FeedbackElement> documentFeedbacks;
}
