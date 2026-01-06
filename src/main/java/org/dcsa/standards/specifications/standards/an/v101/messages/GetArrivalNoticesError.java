package org.dcsa.standards.specifications.standards.an.v101.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Error API response to a `GET ArrivalNotices` request
""")
public class GetArrivalNoticesError {

  @Schema(
      description =
"""
Feedback elements indicating why the request could not be processed.

At least one feedback element of severity `ERROR` is expected.
Lower severity feedback elements may also be included.
""")
  private List<FeedbackElement> feedbackElements;
}
