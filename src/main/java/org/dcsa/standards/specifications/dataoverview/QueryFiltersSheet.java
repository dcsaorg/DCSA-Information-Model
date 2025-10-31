package org.dcsa.standards.specifications.dataoverview;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryFiltersSheet extends DataOverviewSheet {
  protected QueryFiltersSheet(
      Map<Boolean, List<List<Parameter>>> requiredAndOptionalFilters,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    super(
        "Query filters",
        "QueryFiltersTable",
        1,
        List.of("FilterParameters", "Required"),
        List.of(128, 16),
        List.of(true, false),
        Stream.of(Boolean.TRUE, Boolean.FALSE)
            .flatMap(
                required ->
                    requiredAndOptionalFilters.get(required).stream()
                        .map(
                            parameterList ->
                                List.of(
                                    parameterList.stream()
                                        .map(Parameter::getName)
                                        .collect(Collectors.joining(", ")),
                                    required ? "yes" : "")))
            .toList(),
        oldDataValuesBySheetClass,
        changedPrimaryKeyByOldPrimaryKeyBySheetClass,
        swapOldAndNew);
  }
}
