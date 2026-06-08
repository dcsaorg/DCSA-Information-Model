package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.UniversallyUniqueID;

@Schema(
    description = "Reference to a document exchanged through the DCSA Document Exchange (DX) API.")
@Data
public class DocumentExchangeReference {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Universally unique identifier of the document exchanged through the Document Exchange (DX) API.")
  private UniversallyUniqueID documentID;

  @Schema(
      type = "string",
      example = "VISUALIZATION",
      description =
"""
Role of the document within the context of a DG declaration:
 - `ATTACHMENT` (Supporting document attached to the DG declaration)
 - `VISUALIZATION` (Human-friendly visualization of the structured data of the DG declaration)
""",
      maxLength = 256)
  private String documentRole;
}
