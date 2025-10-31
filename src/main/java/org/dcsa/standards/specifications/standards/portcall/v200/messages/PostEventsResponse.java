package org.dcsa.standards.specifications.standards.portcall.v200.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `POST Events` request
""")
public class PostEventsResponse {

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed.

There can be zero, one or more feedback elements per event in the request.
The order of `feedbackElements` is not related to the order of events in the request.
Instead, the relevant request event is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
