package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Object used to indicate that one or more documents were replaced by one or more other documents.

Used in scenarios including booking split / combine or transport document void / reissue.
""")
public class DocumentReferenceReplacement {

  @Schema(
      description =
"""
References of the documents replaced by those with the `newReferences`.
""")
  private List<DocumentReference> oldReferences;

  @Schema(
      description =
"""
References of the documents replacing those with the `oldReferences`.
""")
  private List<DocumentReference> newReferences;
}
