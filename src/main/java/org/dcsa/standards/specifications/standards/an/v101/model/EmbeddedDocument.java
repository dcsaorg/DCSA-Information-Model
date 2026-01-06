package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Name, Base64-encoded content and content type of an embedded document")
public class EmbeddedDocument {

  @Schema(
      type = "string",
      example = "20250123 AN XYZ01234.docx",
      description = "Document name",
      maxLength = 255)
  private String name;

  @Schema(
      type = "string",
      example = "RXhhbXBsZSBBTiBjb250ZW50",
      description = "Base64 representation of the binary document content")
  private String content;

  @Schema(
      type = "string",
      defaultValue = "application/pdf",
      example = "application/msword",
      description = "Content type of document",
      maxLength = 255)
  private String contentType;
}
