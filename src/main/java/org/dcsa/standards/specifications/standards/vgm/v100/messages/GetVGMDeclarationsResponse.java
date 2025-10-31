package org.dcsa.standards.specifications.standards.vgm.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;

@Data
@Schema(
    description =
"""
API response to a `GET VGM declarations` request
""")
public class GetVGMDeclarationsResponse {

  @Schema(
      name = "VGMDeclarations",
      description =
"""
The list of VGM declarations matching the filter specified by the request query parameters
""")
  private List<VGMDeclaration> vgmDeclarations;

  @Schema(
      description =
"""
Optional list of feedback elements indicating how the request was processed
""")
  private List<FeedbackElement> feedbackElements;
}
