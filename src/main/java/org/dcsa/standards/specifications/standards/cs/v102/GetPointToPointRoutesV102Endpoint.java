package org.dcsa.standards.specifications.standards.cs.v102;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import org.dcsa.standards.specifications.standards.cs.v101.GetPointToPointRoutesV101Endpoint;

public class GetPointToPointRoutesV102Endpoint extends GetPointToPointRoutesV101Endpoint {

  private final Parameter departureStartDate =
      createDateQueryParameter(
          "departureStartDate",
          "2021-04-01",
          "Limit the result based on the earliest departureDate.\n- If provided without departureEndDate, returns all routings with departures from the specified departureStartDate onwards.\n- If provided with departureEndDate, returns all routings with departures within the specified date range, inclusive of both dates.\n- If the same date is provided for both departureStartDate and departureEndDate, returns all routings with departures on that specific date.");

  private final Parameter departureEndDate =
      createDateQueryParameter(
          "departureEndDate",
          "2021-05-01",
          "Limit the result based on the latest departureDate.\n- If provided without departureStartDate, returns all routings with departures up to the specified departureEndDate.\n- If provided with departureStartDate, returns all routings with departures within the specified date range, inclusive of both dates.\n- If the same date is provided for both departureStartDate and departureEndDate, returns all routings with departures on that specific date.");

  private final Parameter arrivalStartDate =
      createDateQueryParameter(
          "arrivalStartDate",
          "2021-04-01",
          "Limit the result based on the earliest arrivalDate.\n- If provided without arrivalEndDate, returns all routings with arrivals from the specified arrivalStartDate.\n- If provided with arrivalEndDate, returns all routings with arrivals within the specified date range, inclusive of both dates.\n- If the same date is provided for both arrivalStartDate and arrivalEndDate, returns all routings with arrivals on that specific date.");

  private final Parameter arrivalEndDate =
      createDateQueryParameter(
          "arrivalEndDate",
          "2021-05-01",
          "Limit the result based on the latest arrivalDate.\n- If provided without arrivalStartDate, returns all routings with arrivals up to the specified arrivalEndDate.\n- If provided with arrivalStartDate, returns all routings with arrivals within the specified date range, inclusive of both dates.\n- If the same date is provided for both arrivalStartDate and arrivalEndDate, returns all routings with arrivals on that specific date.");

  private final Parameter receiptTypeAtOrigin =
      new Parameter()
          .in("query")
          .name("receiptTypeAtOrigin")
          .example("CY")
          .description(
"""
Indicates the type of service offered at Origin. **Carriers can choose to define a default value when the consumer does not provide it.**
  - `CY` (Container yard (incl. rail ramp))
  - `SD` (Store Door)
  - `CFS` (Container Freight Station)
""")
          .schema(new StringSchema()._enum(List.of("CY", "SD", "CFS")).maxLength(3));

  private final Parameter deliveryTypeAtDestination =
      new Parameter()
          .in("query")
          .name("deliveryTypeAtDestination")
          .example("CY")
          .description(
"""
Indicates the type of service offered at Destination. **Carriers can choose to define a default value when the consumer does not provide it.**
  - `CY` (Container yard (incl. rail ramp))
  - `SD` (Store Door)
  - `CFS` (Container Freight Station)
""")
          .schema(new StringSchema()._enum(List.of("CY", "SD", "CFS")).maxLength(3));

  private final Parameter limit =
      new Parameter()
          .in("query")
          .name("limit")
          .example(100)
          .description("Specifies the maximum number of `Point-to-Point` objects to return.");

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
                  case "departureStartDate" -> departureStartDate;
                  case "departureEndDate" -> departureEndDate;
                  case "arrivalStartDate" -> arrivalStartDate;
                  case "arrivalEndDate" -> arrivalEndDate;
                  case "receiptTypeAtOrigin" -> receiptTypeAtOrigin;
                  case "deliveryTypeAtDestination" -> deliveryTypeAtDestination;
                  case "limit" -> limit;
                  case "cursor" -> cursor;
                  default -> p;
                })
        .toList();
  }
}
