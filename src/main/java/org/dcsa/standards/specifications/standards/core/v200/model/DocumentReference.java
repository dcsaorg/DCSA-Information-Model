package org.dcsa.standards.specifications.standards.core.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.DocumentReferenceType;

@Data
@Schema(description = "Referenced document")
public class DocumentReference {

  @Schema() private DocumentReferenceType referenceType;

  @Schema(
      description = "Reference identifying the document",
      example = "123e4567e89b",
      maxLength = 100)
  private String reference;
}
