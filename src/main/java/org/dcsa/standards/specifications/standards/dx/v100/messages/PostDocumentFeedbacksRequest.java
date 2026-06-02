package org.dcsa.standards.specifications.standards.dx.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "API message containing a list of document feedback elements.")
public class PostDocumentFeedbacksRequest {

  @Schema(description = "List of document feedback elements.")
  private List<FeedbackElement> documentFeedbacks;
}
