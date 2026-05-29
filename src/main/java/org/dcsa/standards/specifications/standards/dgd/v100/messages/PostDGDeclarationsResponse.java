package org.dcsa.standards.specifications.standards.dgd.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `POST DG declarations` request
""")
public class PostDGDeclarationsResponse {

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed.

There can be zero, one or more feedback elements per declaration in the request.
The order of `feedbackElements` is not related to the order of DG declarations in the request.
Instead, the relevant request DG declaration is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
