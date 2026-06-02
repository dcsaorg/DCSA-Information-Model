package org.dcsa.standards.specifications.standards.dx.v100;

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
import org.dcsa.standards.specifications.standards.dx.v100.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.dx.v100.messages.GetDocumentFeedbacksResponse;
import org.dcsa.standards.specifications.standards.dx.v100.messages.GetDocumentsError;
import org.dcsa.standards.specifications.standards.dx.v100.messages.GetDocumentsResponse;
import org.dcsa.standards.specifications.standards.dx.v100.messages.PostDocumentFeedbacksRequest;
import org.dcsa.standards.specifications.standards.dx.v100.messages.PostDocumentFeedbacksResponse;
import org.dcsa.standards.specifications.standards.dx.v100.messages.PostDocumentsError;
import org.dcsa.standards.specifications.standards.dx.v100.messages.PostDocumentsRequest;
import org.dcsa.standards.specifications.standards.dx.v100.messages.PostDocumentsResponse;
import org.dcsa.standards.specifications.standards.dx.v100.model.Document;

public class DX100StandardSpecification extends StandardSpecification {

  public static final String TAG_DX_PRODUCERS = "DX Producer Endpoints";
  public static final String TAG_DX_CONSUMERS = "DX Consumer Endpoints";

  private final GetDocumentsEndpoint getDocumentsEndpoint;

  public DX100StandardSpecification() {
    super("Document Exchange", "1.0.0", "dx", "dx");

    openAPI.addTagsItem(
        new Tag().name(TAG_DX_PRODUCERS).description("Endpoints implemented by the DX Producers"));
    openAPI.addTagsItem(
        new Tag().name(TAG_DX_CONSUMERS).description("Endpoints implemented by the DX Consumers"));

    openAPI.path(
        "/documents", new PathItem().get(operationDocumentsGet()).post(operationDocumentsPost()));

    openAPI.path(
        "/document-feedbacks",
        new PathItem().get(operationDocumentFeedbacksGet()).post(operationDocumentFeedbacksPost()));

    getDocumentsEndpoint = new GetDocumentsEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata("Document Exchange", "1.0.0", "", "", 4);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Document.class,
        FeedbackElement.class,
        GetDocumentFeedbacksResponse.class,
        GetDocumentsError.class,
        GetDocumentsResponse.class,
        PostDocumentFeedbacksRequest.class,
        PostDocumentFeedbacksResponse.class,
        PostDocumentsError.class,
        PostDocumentsRequest.class,
        PostDocumentsResponse.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(Document.class.getSimpleName());
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
        .collect(Collectors.toMap(Map.Entry::getKey, _ -> List.of()));
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
    return getDocumentsEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationDocumentsGet() {
    return new Operation()
        .summary("Retrieves a list of documents")
        .description(readResourceFile("openapi-get-documents-description.md"))
        .operationId("get-documents")
        .tags(Collections.singletonList(TAG_DX_PRODUCERS))
        .parameters(
            Stream.concat(
                    new GetDocumentsEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of documents matching the query parameters")
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
                                        (_, b) -> b,
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
                                                        GetDocumentsResponse.class))))))
                .addApiResponse("default", createErrorResponse(GetDocumentsError.class)));
  }

  private Operation operationDocumentsPost() {
    return new Operation()
        .summary("Sends a list of documents")
        .description(readResourceFile("openapi-post-documents-description.md"))
        .operationId("post-documents")
        .tags(Collections.singletonList(TAG_DX_CONSUMERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of documents")
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
                                                PostDocumentsRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("Documents response")
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
                                                        PostDocumentsResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostDocumentsError.class)));
  }

  private Operation operationDocumentFeedbacksGet() {
    return new Operation()
        .summary("Retrieves a list of document feedbacks")
        .description(readResourceFile("openapi-get-document-feedbacks-description.md"))
        .operationId("get-document-feedbacks")
        .tags(Collections.singletonList(TAG_DX_CONSUMERS))
        .parameters(
            Stream.concat(
                    new GetDocumentsEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description(
                            "List of document feedback elements matching the query parameters")
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
                                        (_, b) -> b,
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
                                                        GetDocumentFeedbacksResponse.class))))))
                .addApiResponse("default", createErrorResponse(GetDocumentsError.class)));
  }

  private Operation operationDocumentFeedbacksPost() {
    return new Operation()
        .summary("Sends a list of document feedbacks")
        .description(readResourceFile("openapi-post-document-feedbacks-description.md"))
        .operationId("post-document-feedbacks")
        .tags(Collections.singletonList(TAG_DX_PRODUCERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of document feedback elements")
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
                                                PostDocumentFeedbacksRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("Document feedbacks response")
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
                                                        PostDocumentFeedbacksResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostDocumentsError.class)));
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
