package org.dcsa.standards.specifications.standards.ebl.v302;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetTransportDocumentEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter transportDocumentReference =
      new Parameter()
          .in("path")
          .name("transportDocumentReference")
          .description("Reference of the transport document to return")
          .example("TDR0123456")
          .schema(new Schema<String>().type("string"));

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(transportDocumentReference);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(
            Boolean.TRUE,
            List.of(
                // TDR only
                List.of(transportDocumentReference))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
