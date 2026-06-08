package org.dcsa.standards.specifications.standards.dx.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDateTime;
import org.dcsa.standards.specifications.standards.core.v200.types.UniversallyUniqueID;

@Data
@Schema(description = "Name, Base64-encoded content and content type of an embedded document")
public class Document {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Universally unique identifier of the document.")
  private UniversallyUniqueID documentID;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The date and time when the document was last updated.

A document with a newer `updatedDateTime` overwrites a document with the same `documentID` and an older `updatedDateTime`.
""")
  private FormattedDateTime updatedDateTime;

  @Schema(
      type = "string",
      example = "20250123 AN XYZ01234.docx",
      description = "Document title",
      maxLength = 1024)
  private String title;

  @Schema(
      type = "string",
      defaultValue = "application/pdf",
      example = "application/msword",
      description = "Content type of document",
      maxLength = 256)
  private String contentType;

  @Schema(
      type = "string",
      example = "RXhhbXBsZSBBTiBjb250ZW50",
      description = "Base64 representation of the binary document content")
  private String content;
}
