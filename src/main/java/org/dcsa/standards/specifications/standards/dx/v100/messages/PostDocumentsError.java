package org.dcsa.standards.specifications.standards.dx.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Error API response to a `POST documents` request.

If some of the documents in the request were successfully processed,
a regular `PostDocumentsResponse` is expected to be used instead,
with `feedbackElements` indicating which documents were not processed and why.
""")
public class PostDocumentsError {

  @Schema(
      description =
"""
Feedback elements indicating why request could not be processed.

At least one feedback element of severity `ERROR` is expected.
Lower severity feedback elements may also be included.

When sent in response to a request that contains an array of documents,
the order of the `feedbackElements` is not related to the list of request documents.
Instead, the relevant request document is indicated by the `propertyPath` of each `FeedbackElement`.
""")
  private List<FeedbackElement> feedbackElements;
}
