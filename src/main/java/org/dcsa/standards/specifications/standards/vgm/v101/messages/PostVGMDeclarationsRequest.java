package org.dcsa.standards.specifications.standards.vgm.v101.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.vgm.v101.model.VGMDeclaration;

@Data
@Schema(description = "API message containing a list of VGM declarations.")
public class PostVGMDeclarationsRequest {

  @Schema(name = "VGMDeclarations", description = "List of VGM declarations.")
  private List<VGMDeclaration> vgmDeclarations;
}
