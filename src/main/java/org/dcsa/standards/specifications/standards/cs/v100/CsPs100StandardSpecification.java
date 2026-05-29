package org.dcsa.standards.specifications.standards.cs.v100;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.standards.cs.v1.Cs1StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.messages.DetailedError;
import org.dcsa.standards.specifications.standards.cs.v100.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v100.model.*;

/** CS 1.0.0 Port Schedules standard specification module. */
public class CsPs100StandardSpecification extends Cs1StandardSpecification {

  public static final String TAG_PS = "Port Schedule";

  private final GetPortSchedulesEndpoint getPortSchedulesEndpoint;

  public CsPs100StandardSpecification() {
    this("1.0.0", "");
  }

  protected CsPs100StandardSpecification(String versionNumber, String baselineVersion) {
    super("Port Schedule", "ps", versionNumber, baselineVersion);

    getPortSchedulesEndpoint = new GetPortSchedulesEndpoint();

    openAPI.addTagsItem(new Tag().name(TAG_PS).description(" "));

    openAPI.path("/v1/port-schedules", new PathItem().get(operationPortSchedulesGet()));
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        CutOffTime.class,
        DetailedError.class,
        ErrorResponse.class,
        PortSchedule.class,
        PortScheduleLocation.class,
        Schedule.class,
        ServicePartnerSchedule.class,
        Timestamp.class,
        Vessel.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(PortSchedule.class.getSimpleName());
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getPortSchedulesEndpoint;
  }

  private Operation operationPortSchedulesGet() {
    return new Operation()
        .summary("Port Schedule")
        .description(
"""
Provides, for a required specific port and starting date, the set of all vessels arriving and departing from the port with the corresponding estimated timestamps.

The port must be identified by its UN Location Code.

The required query parameters are `UNLocationCode` and `date`.

If the requested port (identified with `UNLocationCode`) has multiple terminals (identified with `facilitySMDGCode`), the response will include a sorted list that provides all the arrivals and departures of the vessels for each terminal of the port (`UNLocationCode`).

The `GET /v1/port-schedules` endpoint can be implemented independently of having implemented the  `GET /v1/point-to-point-routes`  and `GET /v1/vessel-schedules`  endpoints.
""")
        .operationId("get-v1-port-schedules")
        .tags(Collections.singletonList(TAG_PS))
        .parameters(
            Stream.concat(
                    getPortSchedulesEndpoint.getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("OK")
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
                                            new ArraySchema()
                                                .items(
                                                    new Schema<>()
                                                        .$ref(
                                                            SpecificationToolkit
                                                                .getComponentSchema$ref(
                                                                    PortSchedule.class)))))))
                .addApiResponse("400", createErrorResponse("Bad Request"))
                .addApiResponse("500", createErrorResponse("Internal Server Error")));
  }

  private ApiResponse createErrorResponse(String description) {
    return new ApiResponse()
        .description(description)
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
                                        ErrorResponse.class)))));
  }
}
