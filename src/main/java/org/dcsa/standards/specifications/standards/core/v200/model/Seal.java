package org.dcsa.standards.specifications.standards.core.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.SealPlacement;
import org.dcsa.standards.specifications.standards.core.v200.types.SealSource;
import org.dcsa.standards.specifications.standards.core.v200.types.SealType;

@Schema(description = "Seal-related information associated with the equipment.")
@Data
public class Seal {

  @Schema(
      description = "Identifies a seal affixed to the container.",
      example = "VET123",
      maxLength = 15)
  protected String number;

  @Schema() protected SealSource source;

  @Schema() protected SealType type;

  @Schema() protected SealPlacement placement;
}
