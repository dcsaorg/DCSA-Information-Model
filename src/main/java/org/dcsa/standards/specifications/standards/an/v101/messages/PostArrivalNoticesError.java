package org.dcsa.standards.specifications.standards.an.v101.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Error API response to a `POST ArrivalNotices` or `POST ArrivalNoticeNotifications` request.

If some of the arrival notice (notification) objects in the request were successfully processed,
a regular `PostArrivalNoticesResponse` is expected to be used instead,
with `feedbackElements` indicating which objects were not processed and why.
""")
public class PostArrivalNoticesError {

  @Schema(
      description =
"""
Feedback elements indicating why request could not be processed.

At least one feedback element of severity `ERROR` is expected.
Lower severity feedback elements may also be included.

When sent in response to a request that contains an array of arrival notice (notification) objects,
the order of the `feedbackElements` is not related to the list of request objects.
Instead, the relevant request object is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
