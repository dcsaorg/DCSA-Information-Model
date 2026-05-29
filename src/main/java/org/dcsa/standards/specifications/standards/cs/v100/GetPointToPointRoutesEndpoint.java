package org.dcsa.standards.specifications.standards.cs.v100;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetPointToPointRoutesEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter placeOfReceipt =
      new Parameter()
          .in("query")
          .name("placeOfReceipt")
          .required(true)
          .example("NLAMS")
          .description("The `UNLocationCode` specifying where the place is located.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^[A-Z]{2}[A-Z2-9]{3}$")
                  .minLength(5)
                  .maxLength(5));

  private final Parameter placeOfDelivery =
      new Parameter()
          .in("query")
          .name("placeOfDelivery")
          .required(true)
          .example("NLAMS")
          .description("The `UNLocationCode` specifying where the place is located.")
          .schema(
              new Schema<String>()
                  .type("string")
                  .pattern("^[A-Z]{2}[A-Z2-9]{3}$")
                  .minLength(5)
                  .maxLength(5));

  private final Parameter departureStartDate =
      createDateQueryParameter(
          "departureStartDate",
          "2021-04-01",
          "Limit the result based on the earliest departureDate. If provided without departureEndDate, returns all routings with departures from the specified departureStartDate onwards.");

  private final Parameter departureEndDate =
      createDateQueryParameter(
          "departureEndDate",
          "2021-05-01",
          "Limit the result based on the latest departureDate. If provided without departureStartDate, returns all routings with departures up to the specified departureEndDate.");

  private final Parameter arrivalStartDate =
      createDateQueryParameter(
          "arrivalStartDate",
          "2021-04-01",
          "Limit the result based on the earliest arrivalDate. If provided without arrivalEndDate, returns all routings with arrivals from the specified arrivalStartDate.");

  private final Parameter arrivalEndDate =
      createDateQueryParameter(
          "arrivalEndDate",
          "2021-05-01",
          "Limit the result based on the latest arrivalDate. If provided without arrivalStartDate, returns all routings with arrivals up to the specified arrivalEndDate.");

  private final Parameter maxTranshipment =
      new Parameter()
          .in("query")
          .name("maxTranshipment")
          .example(1)
          .description(
              "Specifies the maximum number of transhipments that the proposed routings can have in the response. By default, transhipments are allowed and the responses can have either direct routings or routings with transhipment. The default value of maximum transhipments is defined by the carrier for when the consumer does not provide a value. If the user sets the number of transhipments to 0, only direct routings can be returned in the response.")
          .schema(new Schema<Integer>().type("integer").format("int32").minimum(BigDecimal.ZERO));

  private final Parameter receiptTypeAtOrigin =
      new Parameter()
          .in("query")
          .name("receiptTypeAtOrigin")
          .example("CY")
          .description(
              "Indicates the type of service offered at Origin. **Carriers can choose to define a default value when the consumer does not provide it.**");

  private final Parameter deliveryTypeAtDestination =
      new Parameter()
          .in("query")
          .name("deliveryTypeAtDestination")
          .example("CY")
          .description(
              "Indicates the type of service offered at Destination. **Carriers can choose to define a default value when the consumer does not provide it.**");

  private final Parameter limit =
      new Parameter()
          .in("query")
          .name("limit")
          .example(100)
          .description("Specifies the maximum number of `Point-to-Point` objects to return.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "fE9mZnNldHw9MTAmbGltaXQ9MTA",
          "A server generated value to specify a specific point in a collection result, used for pagination.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(
        placeOfReceipt,
        placeOfDelivery,
        departureStartDate,
        departureEndDate,
        arrivalStartDate,
        arrivalEndDate,
        maxTranshipment,
        receiptTypeAtOrigin,
        deliveryTypeAtDestination,
        limit,
        cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(Boolean.TRUE, List.of(List.of(placeOfReceipt, placeOfDelivery))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
