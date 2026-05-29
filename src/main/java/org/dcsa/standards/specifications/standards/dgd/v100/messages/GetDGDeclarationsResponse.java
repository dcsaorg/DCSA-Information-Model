package org.dcsa.standards.specifications.standards.dgd.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DGDeclaration;

@Data
@Schema(
    description =
"""
API response to a `GET DG declarations` request
""")
public class GetDGDeclarationsResponse {

  @Schema(
      name = "DGDeclarations",
      description =
"""
The list of DG declarations matching the filter specified by the request query parameters
""")
  private List<DGDeclaration> dgDeclarations;
}
