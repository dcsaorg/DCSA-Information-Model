package org.dcsa.standards.specifications.standards.core.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.DocumentReferenceTypeCode;

@Data
@Schema(description = "Referenced document")
public class DocumentReference {

  @Schema() private DocumentReferenceTypeCode typeCode;

  @Schema(
      description = "Reference identifying the document",
      example = "123e4567e89b",
      maxLength = 100)
  private String reference;
}
