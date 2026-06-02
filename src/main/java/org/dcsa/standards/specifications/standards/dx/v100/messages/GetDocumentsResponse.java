package org.dcsa.standards.specifications.standards.dx.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dx.v100.model.Document;

@Data
@Schema(
    description =
"""
API response to a `GET documents` request
""")
public class GetDocumentsResponse {

  @Schema(
      description =
"""
The list of documents matching the filter specified by the request query parameters
""")
  private List<Document> documents;
}
