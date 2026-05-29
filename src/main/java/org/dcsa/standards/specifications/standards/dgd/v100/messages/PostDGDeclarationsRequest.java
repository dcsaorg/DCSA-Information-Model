package org.dcsa.standards.specifications.standards.dgd.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DGDeclaration;

@Data
@Schema(description = "API message containing a list of DG declarations.")
public class PostDGDeclarationsRequest {

  @Schema(name = "DGDeclarations", description = "List of DG declarations.")
  private List<DGDeclaration> dgDeclarations;
}
