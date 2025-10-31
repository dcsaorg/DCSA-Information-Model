package org.dcsa.standards.specifications.standards.vgm.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Error API response to a `GET VGM declarations` request
""")
public class GetVGMDeclarationsError {

  @Schema(
      description =
"""
Feedback elements indicating why the request could not be processed.

At least one feedback element of severity `ERROR` is expected.
Lower severity feedback elements may also be included.
""")
  private List<FeedbackElement> feedbackElements;
}
