package org.dcsa.standards.specifications.standards.booking.v2;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetBookingEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter carrierBookingReference =
      new Parameter()
          .in("path")
          .name("carrierBookingReference")
          .description("Carrier reference of the booking to return")
          .example("BKG0123456")
          .schema(new Schema<String>().type("string"));

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(carrierBookingReference);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(
            Boolean.TRUE,
            List.of(
                // CBR only
                List.of(carrierBookingReference))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
