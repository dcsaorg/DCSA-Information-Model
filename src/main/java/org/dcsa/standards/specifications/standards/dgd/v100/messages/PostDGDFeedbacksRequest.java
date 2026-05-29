package org.dcsa.standards.specifications.standards.dgd.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "API message containing a list of DGD feedback elements.")
public class PostDGDFeedbacksRequest {

  @Schema(name = "DGDFeedbacks", description = "List of DGD feedback elements.")
  private List<FeedbackElement> dgdFeedbacks;
}

