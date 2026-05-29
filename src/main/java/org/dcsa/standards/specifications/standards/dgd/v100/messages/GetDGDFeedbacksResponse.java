package org.dcsa.standards.specifications.standards.dgd.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `GET DGD feedbacks` request
""")
public class GetDGDFeedbacksResponse {

  @Schema(
      name = "DGDFeedbacks",
      description =
"""
The list of DGD feedback elements matching the filter specified by the request query parameters
""")
  private List<FeedbackElement> dgdFeedbacks;
}

