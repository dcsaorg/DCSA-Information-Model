package org.dcsa.standards.specifications.standards.vgm.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.vgm.v101.types.OtherReferenceTypeCode;

@Data
@Schema(description = "Other reference")
public class OtherReference {

  @Schema() private OtherReferenceTypeCode typeCode;

  @Schema(description = "The actual reference value", example = "A456C", maxLength = 100)
  private String reference;
}
