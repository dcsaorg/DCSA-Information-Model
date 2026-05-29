package org.dcsa.standards.specifications.standards.cs.v100;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetPortSchedulesEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter unLocationCode =
      new Parameter()
          .in("query")
          .name("UNLocationCode")
          .required(true)
          .example("NLAMS")
          .description(
              "The UN Location code specifying where the place is located. Specifying this filter will only return the set of all vessels arriving and departing from the port and its terminals.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^[A-Z]{2}[A-Z2-9]{3}$")
                  .minLength(5)
                  .maxLength(5));

  private final Parameter date =
      new Parameter()
          .in("query")
          .name("date")
          .required(true)
          .example("2023-07-01")
          .description(
              "The date since when the estimated arrival and departures of vessels in a given port is required.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .format("date")
                  .pattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"));

  private final Parameter limit =
      new Parameter()
          .in("query")
          .name("limit")
          .example(100)
          .description("Specifies the maximum number of `Port Schedule` objects to return.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "fE9mZnNldHw9MTAmbGltaXQ9MTA",
          "A server generated value to specify a specific point in a collection result, used for pagination.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(unLocationCode, date, limit, cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(Boolean.TRUE, List.of(List.of(unLocationCode, date))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
