package org.dcsa.standards.specifications.standards.tnt.v300.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Error API response to a `POST Events` request.

If some of the events in the request were successfully processed,
a regular `PostEventsResponse` is expected to be used instead,
with `feedbackElements` indicating which events were not processed and why.
""")
public class PostEventsError {

  @Schema(
      description =
"""
Feedback elements indicating why request could not be processed.

At least one feedback element of severity `ERROR` is expected.
Lower severity feedback elements may also be included.

When sent in response to a request that contains an array of events,
the order of the `feedbackElements` is not related to the list of request events.
Instead, the relevant request event is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
