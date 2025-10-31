package org.dcsa.standards.specifications.standards.an.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v100.model.ArrivalNotice;

@Data
@Schema(
    description =
"""
API response to a `GET ArrivalNotices` request
""")
public class GetArrivalNoticesResponse {

  @Schema(
      description =
"""
The list of arrival notices matching the filter specified by the request query parameters
""")
  private List<ArrivalNotice> arrivalNotices;

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed
""")
  private List<FeedbackElement> feedbackElements;
}
