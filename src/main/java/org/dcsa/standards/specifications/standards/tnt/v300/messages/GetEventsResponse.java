package org.dcsa.standards.specifications.standards.tnt.v300.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.tnt.v300.model.Event;

@Data
@Schema(
    description =
"""
API response to a `GET Events` request
""")
public class GetEventsResponse {

  @Schema(
      description =
"""
The list of events matching the filter specified by the request query parameters
""")
  private List<Event> events;

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed
""")
  private List<FeedbackElement> feedbackElements;
}
