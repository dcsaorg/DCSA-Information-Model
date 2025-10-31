package org.dcsa.standards.specifications.dataoverview;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;

public class QueryParametersSheet extends DataOverviewSheet {
  protected QueryParametersSheet(
      List<Parameter> queryParameters,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    super(
        "Query parameters",
        "QueryParametersTable",
        1,
        List.of("Name", "Type", "Description", "Example"),
        List.of(32, 16, 120, 32),
        List.of(false, false, true, false),
        queryParameters.stream()
            .map(
                queryParameter ->
                    List.of(
                        queryParameter.getName(),
                        queryParameter.getSchema().getType(),
                        queryParameter.getDescription().trim(),
                        String.valueOf(queryParameter.getExample()).trim()))
            .toList(),
        oldDataValuesBySheetClass,
        changedPrimaryKeyByOldPrimaryKeyBySheetClass,
        swapOldAndNew);
  }
}
