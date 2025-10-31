package org.dcsa.standards.specifications.standards.tnt.v300;

import io.swagger.v3.oas.models.parameters.Parameter;
import java.util.List;
import java.util.Map;

import org.dcsa.standards.specifications.standards.tnt.v300.types.EventTypeCode;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class GetEventsEndpoint extends QueryParametersFilterEndpoint {

  private final Parameter carrierBookingReference =
      createStringQueryParameter(
          "carrierBookingReference",
          "ABC709951",
          "Reference of the booking for which to return the associated events");

  private final Parameter transportDocumentReference =
      createStringQueryParameter(
          "transportDocumentReference",
          "HHL71800000",
          "Reference of the transport document for which to return the associated events");

  private final Parameter equipmentReference =
      createStringQueryParameter(
          "equipmentReference",
          "APZU4812090",
          "Reference of the equipment for which to return the associated events");

  private final Parameter eventTypes =
      createStringListQueryParameter(
          "eventTypes",
          List.of(
              EventTypeCode.EQUIPMENT.name(),
              EventTypeCode.IOT.name(),
              EventTypeCode.REEFER.name()),
          "Retrieve only events whose `eventType` is in this list");

  private final Parameter eventUpdatedDateTimeMin =
      createDateTimeQueryParameter(
          "eventUpdatedDateTimeMin",
          "Retrieve events with an `eventUpdatedDateTime` at or after this timestamp");

  private final Parameter eventUpdatedDateTimeMax =
      createDateTimeQueryParameter(
          "eventUpdatedDateTimeMax",
          "Retrieve events with an `eventUpdatedDateTime` at or before this timestamp");

  private final Parameter limit =
      createIntegerQueryParameter(
          "limit", 10, "Maximum number of events to include in each page of the response.");

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
        eventTypes,
        eventUpdatedDateTimeMin,
        eventUpdatedDateTimeMax,
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
                    List.of(transportDocumentReference, equipmentReference),
                    List.of(equipmentReference)),
                allCombinationsOf(
                    List.of(List.of(), List.of(eventTypes)),
                    List.of(
                        List.of(),
                        List.of(eventUpdatedDateTimeMin),
                        List.of(eventUpdatedDateTimeMax),
                        List.of(eventUpdatedDateTimeMin, eventUpdatedDateTimeMax))))),
        Map.entry(Boolean.FALSE, List.of()));
  }
}
