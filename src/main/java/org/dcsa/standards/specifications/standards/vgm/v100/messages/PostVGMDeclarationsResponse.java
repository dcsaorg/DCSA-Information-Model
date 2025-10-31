package org.dcsa.standards.specifications.standards.vgm.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `POST VGM declarations` request
""")
public class PostVGMDeclarationsResponse {

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed.

There can be zero, one or more feedback elements per event in the request.
The order of `feedbackElements` is not related to the order of VGM declarations in the request.
Instead, the relevant request VGM declaration is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
