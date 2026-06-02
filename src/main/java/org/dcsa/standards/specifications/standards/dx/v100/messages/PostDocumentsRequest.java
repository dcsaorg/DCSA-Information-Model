package org.dcsa.standards.specifications.standards.dx.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dx.v100.model.Document;

@Data
@Schema(description = "API message containing a list of documents.")
public class PostDocumentsRequest {

  @Schema(description = "List of documents.")
  private List<Document> documents;
}
