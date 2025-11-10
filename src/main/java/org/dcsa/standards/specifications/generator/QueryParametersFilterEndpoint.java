package org.dcsa.standards.specifications.generator;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class QueryParametersFilterEndpoint {
  public abstract List<Parameter> getQueryParameters();

  public abstract Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters();

  protected static List<List<Parameter>> allCombinationsOf(
      List<List<Parameter>> leftListList, List<List<Parameter>> rightListList) {
    return rightListList.stream()
        .flatMap(
            rightList ->
                leftListList.stream()
                    .map(leftList -> Stream.concat(leftList.stream(), rightList.stream()).toList()))
        .toList();
  }

  protected static Parameter createStringQueryParameter(
      String name, String example, String description) {
    return new Parameter()
        .in("query")
        .name(name)
        .example(example)
        .description(description)
        .schema(new Schema<String>().type("string"));
  }

  protected static Parameter createStringListQueryParameter(
      String name, List<String> example, String description) {
    return new Parameter()
        .in("query")
        .name(name)
        .schema(new ArraySchema().items(new StringSchema()))
        .explode(Boolean.FALSE)
        .description(description)
        .example(String.join(",", example));
  }

  protected Parameter createDateQueryParameter(String name, String example, String description) {
    return new Parameter()
        .in("query")
        .name(name)
        .description(description)
        .example(example)
        .schema(new Schema<String>().type("string").format("date"));
  }

  protected Parameter createDateTimeQueryParameter(String name, String description) {
    return new Parameter()
        .in("query")
        .name(name)
        .description(description)
        .example("2025-01-23T01:23:45Z")
        .schema(new Schema<String>().type("string").format("date-time"));
  }

  protected Parameter createIntegerQueryParameter(String name, int example, String description) {
    return createIntegerQueryParameter(name, example, description, Function.identity());
  }

  protected Parameter createIntegerQueryParameter(
      String name,
      int example,
      String description,
      Function<Schema<?>, Schema<?>> schemaPostProcessor) {
    return new Parameter()
        .in("query")
        .name(name)
        .description(description)
        .example(example)
        .schema(schemaPostProcessor.apply(new Schema<Integer>().type("integer").format("int32")));
  }

  protected Parameter createBooleanQueryParameter(
      String name, boolean example, String description) {
    return new Parameter()
        .in("query")
        .name(name)
        .description(description)
        .example(example)
        .schema(new Schema<Boolean>().type("boolean"));
  }
}
