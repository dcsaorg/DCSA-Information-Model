package org.dcsa.standards.specifications.standards.dx.v100;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetDocumentsEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter documentID =
      createStringQueryParameter(
          "documentID",
          "3910eb91-8791-4699-8029-8bba8cedb6f5",
          "Universally unique identifier of the document.");

  private final Parameter limit =
      createIntegerQueryParameter(
          "limit", 10, "Maximum number of documents to include in each page of the response.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "ExampleNextPageCursor",
          "Set to the value of the `Next-Page-Cursor` header of the previous response to retrieve the next page.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(documentID, limit, cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    List<List<Parameter>> mandatoryFilterLists = List.of(List.of(documentID));
    return Map.ofEntries(
        Map.entry(Boolean.TRUE, mandatoryFilterLists), Map.entry(Boolean.FALSE, List.of()));
  }
}
