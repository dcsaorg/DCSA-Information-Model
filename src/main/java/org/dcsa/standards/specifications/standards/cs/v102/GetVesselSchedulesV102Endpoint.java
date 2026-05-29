package org.dcsa.standards.specifications.standards.cs.v102;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.dcsa.standards.specifications.standards.cs.v101.GetVesselSchedulesV101Endpoint;

public class GetVesselSchedulesV102Endpoint extends GetVesselSchedulesV101Endpoint {

  private final Parameter limit =
      new Parameter()
          .in("query")
          .name("limit")
          .example(100)
          .description("Specifies the maximum number of `Service Schedule` objects to return.");

  private final Parameter cursor =
      new Parameter()
          .in("query")
          .name("cursor")
          .example("fE9mZnNldHw9MTAmbGltaXQ9MTA")
          .description(
              "A server generated value to specify a specific point in a collection result, used for pagination.")
          .schema(new Schema<String>().type("string").maxLength(1024));

  @Override
  public List<Parameter> getQueryParameters() {
    return super.getQueryParameters().stream()
        .map(
            p ->
                switch (p.getName()) {
                  case "limit" -> limit;
                  case "cursor" -> cursor;
                  default -> p;
                })
        .toList();
  }
}
