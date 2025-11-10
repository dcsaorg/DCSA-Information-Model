package org.dcsa.standards.specifications.standards.portcall.v200;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
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

import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.dcsa.standards.specifications.standards.core.v101.model.Address;
import org.dcsa.standards.specifications.standards.core.v101.model.Facility;
import org.dcsa.standards.specifications.standards.core.v101.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.core.v101.model.ServiceCodeOrReference;
import org.dcsa.standards.specifications.standards.core.v101.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.GetEventsError;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.GetEventsResponse;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.PostEventsError;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.PostEventsRequest;
import org.dcsa.standards.specifications.standards.portcall.v200.messages.PostEventsResponse;
import org.dcsa.standards.specifications.standards.portcall.v200.model.ContainerCountBySize;
import org.dcsa.standards.specifications.standards.portcall.v200.model.ContainerCountByTypeAndSize;
import org.dcsa.standards.specifications.standards.portcall.v200.model.Event;
import org.dcsa.standards.specifications.standards.portcall.v200.model.MovesForecast;
import org.dcsa.standards.specifications.standards.portcall.v200.model.PortCall;
import org.dcsa.standards.specifications.standards.portcall.v200.model.PortCallService;
import org.dcsa.standards.specifications.standards.portcall.v200.model.TerminalCall;
import org.dcsa.standards.specifications.standards.portcall.v200.model.Timestamp;
import org.dcsa.standards.specifications.standards.portcall.v200.model.Vessel;
import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.dataoverview.QueryFiltersSheet;
import org.dcsa.standards.specifications.dataoverview.QueryParametersSheet;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class PortCallStandardSpecification extends StandardSpecification {

  private static final String TAG_EVENT_PUBLISHERS = "Event Publisher Endpoints";
  private static final String TAG_EVENT_SUBSCRIBERS = "Event Subscriber Endpoints";
  private static final String REQUEST_SENDING_PARTY_HEADER = "Request-Sending-Party";
  private static final String REQUEST_RECEIVING_PARTY_HEADER = "Request-Receiving-Party";
  private static final String RESPONSE_SENDING_PARTY_HEADER = "Response-Sending-Party";
  private static final String RESPONSE_RECEIVING_PARTY_HEADER = "Response-Receiving-Party";
  public static final String REQUEST_SENDING_PARTY_HEADER_DESCRIPTION =
      """
    When communicating through an optional system that acts as an application level Port Call communication proxy,
    forwarding API calls between Port Call service providers and Port Call service consumers,
    the API client sets this request header to identify itself to the Port Call proxy and to the API server
    as the original sending party of the API request.

    The assignment of party identifiers by the Port Call proxy and the distribution of identifiers
    to the parties connecting through the Port Call proxy are out of scope.
    """;
  public static final String REQUEST_RECEIVING_PARTY_HEADER_DESCRIPTION =
      """
    When communicating through an optional system that acts as an application level Port Call communication proxy,
    forwarding API calls between Port Call service providers and Port Call service consumers,
    the API client sets this request header to identify to the Port Call proxy the target receiving party of the API request.

    The assignment of party identifiers by the Port Call proxy and the distribution of identifiers
    to the parties connecting through the Port Call proxy are out of scope.
    """;
  public static final String RESPONSE_SENDING_PARTY_HEADER_DESCRIPTION =
      """
    When communicating through an optional system that acts as an application level Port Call communication proxy,
    forwarding API calls between Port Call service providers and Port Call service consumers,
    the API server sets this response header to identify itself to the Port Call proxy and to the API client
    as the original sending party of the API response.

    The value of this response header must be the same as the value of the request header `Request-Receiving-Party`.

    The assignment of party identifiers by the Port Call proxy and the distribution of identifiers
    to the parties connecting through the Port Call proxy are out of scope.
    """;
  public static final String RESPONSE_RECEIVING_PARTY_HEADER_DESCRIPTION =
      """
    When communicating through an optional system that acts as an application level Port Call communication proxy,
    forwarding API calls between Port Call service providers and Port Call service consumers,
    the API server sets this response header to identify to the Port Call proxy the target receiving party of the API response.

    The value of this response header must be the same as the value of the request header `Request-Sending-Party`.

    The assignment of party identifiers by the Port Call proxy and the distribution of identifiers
    to the parties connecting through the Port Call proxy are out of scope.
    """;
  public static final String REQUEST_SENDING_PARTY_HEADER_EXAMPLE = "Carrier-123";
  public static final String REQUEST_RECEIVING_PARTY_HEADER_EXAMPLE = "Terminal-456";

  private final GetEventsEndpoint getEventsEndpoint;

  public PortCallStandardSpecification() {
    super("Port Call", "2.0.0", "portcall", "port-call");

    openAPI.addTagsItem(
        new Tag()
            .name(TAG_EVENT_PUBLISHERS)
            .description("Endpoints implemented by the adopters who publish events"));
    openAPI.addTagsItem(
        new Tag()
            .name(TAG_EVENT_SUBSCRIBERS)
            .description("Endpoints implemented by the adopters who receive events"));

    openAPI.path("/events", new PathItem().get(operationEventsGet()).post(operationEventsPost()));

    getEventsEndpoint = new GetEventsEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata("Port Call", "2.0.0-20251121-beta", "PC", "2.0.0-20251107-beta", 3);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        ContainerCountBySize.class,
        ContainerCountByTypeAndSize.class,
        Event.class,
        Facility.class,
        FeedbackElement.class,
        GeoCoordinate.class,
        GetEventsError.class,
        GetEventsResponse.class,
        Location.class,
        MovesForecast.class,
        PortCall.class,
        PortCallService.class,
        PostEventsError.class,
        PostEventsRequest.class,
        PostEventsResponse.class,
        ServiceCodeOrReference.class,
        TerminalCall.class,
        Timestamp.class,
        Vessel.class,
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
                                    + "db86454af300a5df090f37a057eb64d5d2633a7f"
                                    + "/generated-resources/standards/portcall/v200/port-call-v2.0.0-data-overview-%s.csv")
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

  @Override
  protected Stream<Map.Entry<String, Header>> getCustomHeaders() {
    return Stream.concat(getRequestCustomHeaders(), getResponseCustomHeaders());
  }

  private Stream<Map.Entry<String, Header>> getRequestCustomHeaders() {
    return Stream.of(
        Map.entry(
            REQUEST_SENDING_PARTY_HEADER,
            new Header()
                .description(REQUEST_SENDING_PARTY_HEADER_DESCRIPTION)
                .schema(
                    new Schema<>().type("string").example(REQUEST_SENDING_PARTY_HEADER_EXAMPLE))),
        Map.entry(
            REQUEST_RECEIVING_PARTY_HEADER,
            new Header()
                .description(REQUEST_RECEIVING_PARTY_HEADER_DESCRIPTION)
                .schema(
                    new Schema<>()
                        .type("string")
                        .example(REQUEST_RECEIVING_PARTY_HEADER_EXAMPLE))));
  }

  private Stream<Map.Entry<String, Header>> getResponseCustomHeaders() {
    return Stream.of(
        Map.entry(
            RESPONSE_SENDING_PARTY_HEADER,
            new Header()
                .description(RESPONSE_SENDING_PARTY_HEADER_DESCRIPTION)
                .schema(new Schema<>().type("string").example("Terminal-456"))),
        Map.entry(
            RESPONSE_RECEIVING_PARTY_HEADER,
            new Header()
                .description(RESPONSE_RECEIVING_PARTY_HEADER_DESCRIPTION)
                .schema(new Schema<>().type("string").example("Carrier-123"))));
  }

  private Operation operationEventsGet() {
    return new Operation()
        .summary("Retrieves a list of events")
        .description(readResourceFile("openapi-get-events-description.md"))
        .operationId("get-events")
        .tags(Collections.singletonList(TAG_EVENT_PUBLISHERS))
        .parameters(
            Stream.concat(
                    new GetEventsEndpoint().getQueryParameters().stream(),
                    Stream.concat(
                        Stream.of(getApiVersionHeaderParameter()),
                        getRequestCustomHeaders()
                            .map(
                                nameAndHeader ->
                                    new Parameter()
                                        .in("header")
                                        .name(nameAndHeader.getKey())
                                        .description(nameAndHeader.getValue().getDescription())
                                        .required(false)
                                        .schema(nameAndHeader.getValue().getSchema())
                                        .example(nameAndHeader.getValue().getExample()))))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of events matching the query parameters")
                        .headers(
                            createRefHeadersMap(
                                API_VERSION_HEADER,
                                NEXT_PAGE_CURSOR_HEADER,
                                RESPONSE_SENDING_PARTY_HEADER,
                                RESPONSE_RECEIVING_PARTY_HEADER))
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
        .tags(Collections.singletonList(TAG_EVENT_SUBSCRIBERS))
        .parameters(
            List.of(
                getApiVersionHeaderParameter(),
                createHeaderParameter(
                    REQUEST_SENDING_PARTY_HEADER,
                    REQUEST_SENDING_PARTY_HEADER_EXAMPLE,
                    REQUEST_SENDING_PARTY_HEADER_DESCRIPTION),
                createHeaderParameter(
                    REQUEST_RECEIVING_PARTY_HEADER,
                    REQUEST_RECEIVING_PARTY_HEADER_EXAMPLE,
                    REQUEST_RECEIVING_PARTY_HEADER_DESCRIPTION)))
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
                            createRefHeadersMap(
                                API_VERSION_HEADER,
                                RESPONSE_SENDING_PARTY_HEADER,
                                RESPONSE_RECEIVING_PARTY_HEADER))
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

  protected Parameter createHeaderParameter(String name, String example, String description) {
    return new Parameter()
        .in("header")
        .name(name)
        .description(description)
        .required(false)
        .schema(new Schema<String>().type("string"))
        .example(example);
  }
}
