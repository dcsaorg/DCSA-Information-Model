package org.dcsa.standards.specifications.standards.vgm.v100;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;

import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetVGMDeclarationsEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter carrierBookingReference =
      createStringQueryParameter(
          "carrierBookingReference",
          "ABC709951",
          "Reference of the booking for which to return the associated VGM declarations");

  private final Parameter transportDocumentReference =
      createStringQueryParameter(
          "transportDocumentReference",
          "HHL71800000",
          "Reference of the transport document for which to return the associated VGM declarations");

  private final Parameter equipmentReference =
      createStringQueryParameter(
          "equipmentReference",
          "APZU4812090",
          "Reference of the equipment for which to return the associated VGM declarations");

  private final Parameter declarationDateTimeMin =
      createDateTimeQueryParameter(
          "declarationDateTimeMin",
          "Retrieve VGM declarations with a `declarationDateTime` at or after this timestamp");

  private final Parameter declarationDateTimeMax =
      createDateTimeQueryParameter(
          "declarationDateTimeMax",
          "Retrieve VGM declarations with a `declarationDateTime` at or before this timestamp");

  private final Parameter limit =
      createIntegerQueryParameter(
          "limit",
          10,
          "Maximum number of VGM declarations to include in each page of the response.");

  private final Parameter cursor =
      createStringQueryParameter(
          "cursor",
          "ExampleNextPageCursor",
          "Set to the value of the `Next-Page-Cursor` header of the previous response to retrieve the next page.");

  @Override
  public List<Parameter> getQueryParameters() {
    return List.of(
        carrierBookingReference,
        transportDocumentReference,
        equipmentReference,
        declarationDateTimeMin,
        declarationDateTimeMax,
        limit,
        cursor);
  }

  @Override
  public Map<Boolean, List<List<Parameter>>> getRequiredAndOptionalFilters() {
    return Map.ofEntries(
        Map.entry(
            Boolean.TRUE,
            allCombinationsOf(
                List.of(
                    List.of(carrierBookingReference),
                    List.of(carrierBookingReference, equipmentReference),
                    List.of(transportDocumentReference),
                    List.of(transportDocumentReference, equipmentReference)),
                List.of(
                    List.of(declarationDateTimeMin),
                    List.of(declarationDateTimeMax),
                    List.of(declarationDateTimeMin, declarationDateTimeMax)))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
