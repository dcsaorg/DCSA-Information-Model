package org.dcsa.standards.specifications.standards.tnt.v300;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.dataoverview.QueryFiltersSheet;
import org.dcsa.standards.specifications.dataoverview.QueryParametersSheet;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.dcsa.standards.specifications.standards.core.v101.model.ActiveReeferParameters;
import org.dcsa.standards.specifications.standards.core.v101.model.Address;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDateTime;
import org.dcsa.standards.specifications.standards.core.v101.model.DocumentReference;
import org.dcsa.standards.specifications.standards.core.v101.model.DocumentReferenceReplacement;
import org.dcsa.standards.specifications.standards.core.v101.model.Facility;
import org.dcsa.standards.specifications.standards.core.v101.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.core.v101.model.Party;
import org.dcsa.standards.specifications.standards.core.v101.model.Seal;
import org.dcsa.standards.specifications.standards.core.v101.model.ServiceCodeOrReference;
import org.dcsa.standards.specifications.standards.core.v101.model.ShipmentReference;
import org.dcsa.standards.specifications.standards.core.v101.model.ShipmentReferenceReplacement;
import org.dcsa.standards.specifications.standards.core.v101.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.GetEventsError;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.GetEventsResponse;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.PostEventsError;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.PostEventsRequest;
import org.dcsa.standards.specifications.standards.tnt.v300.messages.PostEventsResponse;
import org.dcsa.standards.specifications.standards.tnt.v300.model.EquipmentDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.Event;
import org.dcsa.standards.specifications.standards.tnt.v300.model.EventClassification;
import org.dcsa.standards.specifications.standards.tnt.v300.model.EventRouting;
import org.dcsa.standards.specifications.standards.tnt.v300.model.IotDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.RailTransport;
import org.dcsa.standards.specifications.standards.tnt.v300.model.ReeferDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.ShipmentDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.TransportCall;
import org.dcsa.standards.specifications.standards.tnt.v300.model.TransportDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.TruckTransport;
import org.dcsa.standards.specifications.standards.tnt.v300.model.VehicleDetails;
import org.dcsa.standards.specifications.standards.tnt.v300.model.VesselTransport;

public class TNTStandardSpecification extends StandardSpecification {

  public static final String TAG_EVENT_PRODUCERS = "Event Producer Endpoints";
  public static final String TAG_EVENT_CONSUMERS = "Event Consumer Endpoints";

  private final GetEventsEndpoint getEventsEndpoint;

  public TNTStandardSpecification() {
    super("Track and Trace", "3.0.0", "tnt", "tnt");

    openAPI.addTagsItem(
        new Tag()
            .name(TAG_EVENT_PRODUCERS)
            .description("Endpoints implemented by the Event Producers"));
    openAPI.addTagsItem(
        new Tag()
            .name(TAG_EVENT_CONSUMERS)
            .description("Endpoints implemented by the Event Consumers"));

    openAPI.path("/events", new PathItem().get(operationEventsGet()).post(operationEventsPost()));

    getEventsEndpoint = new GetEventsEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(
        "Track and Trace", "3.0.0-20251121-design", "TNT", "3.0.0-20251107-design", 4);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        ActiveReeferParameters.class,
        Address.class,
        ClassifiedDateTime.class,
        DocumentReference.class,
        DocumentReferenceReplacement.class,
        EquipmentDetails.class,
        Event.class,
        EventClassification.class,
        EventRouting.class,
        Facility.class,
        FeedbackElement.class,
        GeoCoordinate.class,
        GetEventsError.class,
        GetEventsResponse.class,
        IotDetails.class,
        Location.class,
        Party.class,
        PostEventsError.class,
        PostEventsRequest.class,
        PostEventsResponse.class,
        RailTransport.class,
        ReeferDetails.class,
        Seal.class,
        ServiceCodeOrReference.class,
        ShipmentDetails.class,
        ShipmentReference.class,
        ShipmentReferenceReplacement.class,
        TransportCall.class,
        TransportDetails.class,
        TruckTransport.class,
        VehicleDetails.class,
        VesselTransport.class,
        VoyageNumberOrReference.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(Event.class.getSimpleName());
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass() {
    return Map.ofEntries(
            Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
            Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"),
            Map.entry(QueryParametersSheet.class, "query-parameters"),
            Map.entry(QueryFiltersSheet.class, "query-filters"))
        .entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry ->
                    DataOverviewSheet.importFromString(
                        SpecificationToolkit.readRemoteFile(
                            ("https://raw.githubusercontent.com/dcsaorg/DCSA-Information-Model/"
                                    + "0fdd496a598b89a578c6d75af0c3c9655c0b1577"
                                    + "/specifications/generated-resources/standards/tnt/v300/tnt-v3.0.0-data-overview-%s.csv")
                                .formatted(entry.getValue())))));
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, Map<String, String>>
      getChangedPrimaryKeyByOldPrimaryKeyBySheetClass() {
    return Map.ofEntries(
        Map.entry(AttributesHierarchicalSheet.class, Map.ofEntries()),
        Map.entry(AttributesNormalizedSheet.class, Map.ofEntries()),
        Map.entry(QueryFiltersSheet.class, Map.ofEntries()),
        Map.entry(QueryParametersSheet.class, Map.ofEntries()));
  }

  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getEventsEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationEventsGet() {
    return new Operation()
        .summary("Retrieves a list of events")
        .description(readResourceFile("openapi-get-events-description.md"))
        .operationId("get-events")
        .tags(Collections.singletonList(TAG_EVENT_PRODUCERS))
        .parameters(
            Stream.concat(
                    new GetEventsEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of events matching the query parameters")
                        .headers(
                            Stream.of(
                                    Map.entry(
                                        API_VERSION_HEADER,
                                        new Header().$ref(API_VERSION_HEADER_REF)),
                                    Map.entry(
                                        NEXT_PAGE_CURSOR_HEADER,
                                        new Header().$ref(NEXT_PAGE_CURSOR_HEADER_REF)))
                                .collect(
                                    Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (a, b) -> b,
                                        LinkedHashMap::new)))
                        .content(
                            new Content()
                                .addMediaType(
                                    "application/json",
                                    new MediaType()
                                        .schema(
                                            new Schema<>()
                                                .$ref(
                                                    SpecificationToolkit.getComponentSchema$ref(
                                                        GetEventsResponse.class))))))
                .addApiResponse("default", createErrorResponse(GetEventsError.class)));
  }

  private Operation operationEventsPost() {
    return new Operation()
        .summary("Sends a list of events")
        .description(readResourceFile("openapi-post-events-description.md"))
        .operationId("post-events")
        .tags(Collections.singletonList(TAG_EVENT_CONSUMERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of events")
                .required(true)
                .content(
                    new Content()
                        .addMediaType(
                            "application/json",
                            new MediaType()
                                .schema(
                                    new Schema<>()
                                        .$ref(
                                            SpecificationToolkit.getComponentSchema$ref(
                                                PostEventsRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("Events response")
                        .headers(
                            new LinkedHashMap<>(
                                Map.ofEntries(
                                    Map.entry(
                                        API_VERSION_HEADER,
                                        new Header().$ref(API_VERSION_HEADER_REF)))))
                        .content(
                            new Content()
                                .addMediaType(
                                    "application/json",
                                    new MediaType()
                                        .schema(
                                            new Schema<>()
                                                .$ref(
                                                    SpecificationToolkit.getComponentSchema$ref(
                                                        PostEventsResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostEventsError.class)));
  }

  private ApiResponse createErrorResponse(Class<?> errorMessageClass) {
    return new ApiResponse()
        .description("Error response")
        .headers(
            new LinkedHashMap<>(
                Map.ofEntries(
                    Map.entry(API_VERSION_HEADER, new Header().$ref(API_VERSION_HEADER_REF)))))
        .content(
            new Content()
                .addMediaType(
                    "application/json",
                    new MediaType()
                        .schema(
                            new Schema<>()
                                .$ref(
                                    SpecificationToolkit.getComponentSchema$ref(
                                        errorMessageClass)))));
  }
}
