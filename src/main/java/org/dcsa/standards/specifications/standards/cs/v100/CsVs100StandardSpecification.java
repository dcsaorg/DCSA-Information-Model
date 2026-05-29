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

/** CS 1.0.0 Vessel Schedules standard specification module. */
public class CsVs100StandardSpecification extends Cs1StandardSpecification {

  public static final String TAG_VS = "Vessel Schedule";

  private final GetVesselSchedulesEndpoint getVesselSchedulesEndpoint;

  public CsVs100StandardSpecification() {
    super("Vessel Schedule", "vs", "1.0.0");

    getVesselSchedulesEndpoint = new GetVesselSchedulesEndpoint();

    openAPI.addTagsItem(new Tag().name(TAG_VS).description(" "));

    openAPI.path("/v1/vessel-schedules", new PathItem().get(operationVesselSchedulesGet()));
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        CutOffTime.class,
        DetailedError.class,
        ErrorResponse.class,
        ServiceSchedule.class,
        Timestamp.class,
        TransportCall.class,
        TransportCallLocation.class,
        Vessel.class,
        VesselSchedule.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(ServiceSchedule.class.getSimpleName());
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getVesselSchedulesEndpoint;
  }

  private Operation operationVesselSchedulesGet() {
    return new Operation()
        .summary("Vessel Schedule")
        .description(
"""
Provides, for a required specific service and/or voyage and/or vessel and/or location, the timetable of estimated departure and arrival times for each port call on the rotation of the vessel(s).

The list of schedules returned in the response can be tailored to specific needs by combining available query parameters.

A filter parameter or a combination of filter parameters MUST always be provided to call the endpoint.

The `GET /v1/vessel-schedules` endpoint can be implemented independently of having implemented the  `GET /v1/point-to-point-routes`  and `GET /v1/port-schedules`  endpoints.
""")
        .operationId("get-v1-vessel-schedule")
        .tags(Collections.singletonList(TAG_VS))
        .parameters(
            Stream.concat(
                    getVesselSchedulesEndpoint.getQueryParameters().stream(),
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
                                                                    ServiceSchedule.class)))))))
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
