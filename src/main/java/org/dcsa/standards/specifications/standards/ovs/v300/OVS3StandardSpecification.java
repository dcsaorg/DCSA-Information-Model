package org.dcsa.standards.specifications.standards.ovs.v300;

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
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.generator.StandardSpecification;
import org.dcsa.standards.specifications.standards.ovs.v300.model.Address;
import org.dcsa.standards.specifications.standards.ovs.v300.model.AddressLocation;
import org.dcsa.standards.specifications.standards.ovs.v300.messages.DetailedError;
import org.dcsa.standards.specifications.standards.ovs.v300.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.ovs.v300.model.FacilitySMDGLocation;
import org.dcsa.standards.specifications.standards.ovs.v300.model.ServiceSchedule;
import org.dcsa.standards.specifications.standards.ovs.v300.model.Timestamp;
import org.dcsa.standards.specifications.standards.ovs.v300.model.TransportCall;
import org.dcsa.standards.specifications.standards.ovs.v300.model.UNLocationLocation;
import org.dcsa.standards.specifications.standards.ovs.v300.model.VesselSchedule;

/** OVS 3.0.0 standard specification created for maintaining and exporting a new-style IM and DO. */
public class OVS3StandardSpecification extends StandardSpecification {

  public static final String TAG_OVS = "Operational Vessel Schedules";

  protected final String baselineVersion;

  private final GetServiceSchedulesEndpoint getServiceSchedulesEndpoint;

  public OVS3StandardSpecification() {
    this("3.0.0", "");
  }

  protected OVS3StandardSpecification(String versionNumber, String baselineVersion) {
    super("OVS", versionNumber, "ovs", "ovs");
    this.baselineVersion = baselineVersion;

    getServiceSchedulesEndpoint = new GetServiceSchedulesEndpoint();

    openAPI.addTagsItem(new Tag().name(TAG_OVS).description(" "));

    openAPI.path("/v3/service-schedules", new PathItem().get(operationServiceSchedulesGet()));
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(
        "OVS", standardVersion, baselineVersion.isEmpty() ? "" : "OVS", baselineVersion, 2);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Address.class,
        AddressLocation.class,
        DetailedError.class,
        FacilitySMDGLocation.class,
        ServiceSchedule.class,
        Timestamp.class,
        TransportCall.class,
        UNLocationLocation.class,
        VesselSchedule.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(ServiceSchedule.class.getSimpleName());
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass() {
    return Map.ofEntries(
            Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
            Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"))
        .entrySet()
        .stream()
        .filter(
            entry ->
                baselineVersion.isEmpty()
                    || Files.isRegularFile(Path.of(getBaselineCsvFilePath(entry.getValue()))))
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry ->
                    baselineVersion.isEmpty()
                        ? List.of()
                        : DataOverviewSheet.importFromString(
                                SpecificationToolkit.readLocalFile(
                                    getBaselineCsvFilePath(entry.getValue())))
                            .stream()
                            .toList()));
  }

  protected String getBaselineCsvFilePath(String sheetName) {
    return "./generated-resources/standards/ovs/v%s/ovs-v%s-data-overview-%s.csv"
        .formatted(baselineVersion.replaceAll("\\.", ""), baselineVersion, sheetName);
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, Map<String, String>>
      getChangedPrimaryKeyByOldPrimaryKeyBySheetClass() {
    return Map.ofEntries(
        Map.entry(AttributesHierarchicalSheet.class, Map.ofEntries()),
        Map.entry(AttributesNormalizedSheet.class, Map.ofEntries()));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getServiceSchedulesEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationServiceSchedulesGet() {
    return new Operation()
        .summary("Get a list of Schedules")
        .description(readResourceFile("openapi-root.md"))
        .operationId("get-v3-service-schedules")
        .tags(Collections.singletonList(TAG_OVS))
        .parameters(
            Stream.concat(
                    getServiceSchedulesEndpoint.getQueryParameters().stream(),
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
