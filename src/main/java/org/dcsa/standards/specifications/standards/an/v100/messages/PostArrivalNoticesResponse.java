package org.dcsa.standards.specifications.standards.an.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
API response to a `POST ArrivalNotices` or `POST ArrivalNoticeNotifications` request
""")
public class PostArrivalNoticesResponse {

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed.

There can be zero, one or more feedback elements per arrival notice (notification) in the request.
The order of `feedbackElements` is not related to the order of arrival notice (notification) objects in the request.
Instead, the relevant request object is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
