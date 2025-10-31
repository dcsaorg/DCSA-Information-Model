package org.dcsa.standards.specifications.standards.vgm.v100;

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

import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.dcsa.standards.specifications.standards.core.v100.model.Address;
import org.dcsa.standards.specifications.standards.core.v100.model.ContactDetails;
import org.dcsa.standards.specifications.standards.core.v100.model.Facility;
import org.dcsa.standards.specifications.standards.core.v100.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v100.model.Location;
import org.dcsa.standards.specifications.standards.core.v100.model.Party;
import org.dcsa.standards.specifications.standards.core.v100.model.PartyDetails;
import org.dcsa.standards.specifications.standards.core.v100.model.Seal;
import org.dcsa.standards.specifications.standards.core.v100.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.GetVGMDeclarationsError;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.GetVGMDeclarationsResponse;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.PostVGMDeclarationsError;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.PostVGMDeclarationsRequest;
import org.dcsa.standards.specifications.standards.vgm.v100.messages.PostVGMDeclarationsResponse;
import org.dcsa.standards.specifications.standards.vgm.v100.model.EquipmentDetails;
import org.dcsa.standards.specifications.standards.vgm.v100.model.OtherReference;
import org.dcsa.standards.specifications.standards.vgm.v100.model.ShipmentDetails;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGM;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;
import org.dcsa.standards.specifications.standards.vgm.v100.model.RoutingDetails;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VesselVoyageDetails;
import org.dcsa.standards.specifications.standards.vgm.v100.model.Weight;
import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.dataoverview.QueryFiltersSheet;
import org.dcsa.standards.specifications.dataoverview.QueryParametersSheet;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;

public class VGMStandardSpecification extends StandardSpecification {

  public static final String TAG_VGM_PRODUCERS = "VGM Producer Endpoints";
  public static final String TAG_VGM_CONSUMERS = "VGM Consumer Endpoints";

  private final GetVGMDeclarationsEndpoint getVGMDeclarationsEndpoint;

  public VGMStandardSpecification() {
    super("Verified Gross Mass", "1.0.0", "vgm", "vgm");

    openAPI.addTagsItem(
        new Tag()
            .name(TAG_VGM_PRODUCERS)
            .description("Endpoints implemented by the VGM Producers"));
    openAPI.addTagsItem(
        new Tag()
            .name(TAG_VGM_CONSUMERS)
            .description("Endpoints implemented by the VGM Consumers"));

    openAPI.path(
        "/vgm-declarations",
        new PathItem().get(operationVGMDeclarationsGet()).post(operationVGMDeclarationsPost()));

    getVGMDeclarationsEndpoint = new GetVGMDeclarationsEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata("Verified Gross Mass", "1.0.0-20251024-design", "", "", 4);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        ContactDetails.class,
        EquipmentDetails.class,
        Facility.class,
        FeedbackElement.class,
        GeoCoordinate.class,
        GetVGMDeclarationsError.class,
        GetVGMDeclarationsResponse.class,
        Location.class,
        OtherReference.class,
        Party.class,
        PartyDetails.class,
        PostVGMDeclarationsError.class,
        PostVGMDeclarationsRequest.class,
        PostVGMDeclarationsResponse.class,
        RoutingDetails.class,
        Seal.class,
        ShipmentDetails.class,
        VesselVoyageDetails.class,
        VoyageNumberOrReference.class,
        VGM.class,
        VGMDeclaration.class,
        Weight.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(VGMDeclaration.class.getSimpleName());
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
                    System.currentTimeMillis() > 0
                        ? List.of()
                        : // TODO remove after first snapshot
                        DataOverviewSheet.importFromString(
                            SpecificationToolkit.readRemoteFile(
                                "https://raw.githubusercontent.com/dcsaorg/Conformance-Gateway/TODO/specifications/generated-resources/standards/vgm/v100/vgm-v1.0.0-data-overview-%s.csv"
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
    return getVGMDeclarationsEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationVGMDeclarationsGet() {
    return new Operation()
        .summary("Retrieves a list of VGM declarations")
        .description(readResourceFile("openapi-get-vgm-declarations-description.md"))
        .operationId("get-vgm-declarations")
        .tags(Collections.singletonList(TAG_VGM_PRODUCERS))
        .parameters(
            Stream.concat(
                    new GetVGMDeclarationsEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of VGM declarations matching the query parameters")
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
                                                        GetVGMDeclarationsResponse.class))))))
                .addApiResponse("default", createErrorResponse(GetVGMDeclarationsError.class)));
  }

  private Operation operationVGMDeclarationsPost() {
    return new Operation()
        .summary("Sends a list of VGM declarations")
        .description(readResourceFile("openapi-post-vgm-declarations-description.md"))
        .operationId("post-vgm-declarations")
        .tags(Collections.singletonList(TAG_VGM_CONSUMERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of VGM declarations")
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
                                                PostVGMDeclarationsRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("VGM declarations response")
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
                                                        PostVGMDeclarationsResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostVGMDeclarationsError.class)));
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
