package org.dcsa.standards.specifications.standards.an.v101;

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
import org.dcsa.standards.specifications.standards.an.v101.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.an.v101.messages.GetArrivalNoticesError;
import org.dcsa.standards.specifications.standards.an.v101.messages.GetArrivalNoticesResponse;
import org.dcsa.standards.specifications.standards.an.v101.messages.PostArrivalNoticeNotificationsRequest;
import org.dcsa.standards.specifications.standards.an.v101.messages.PostArrivalNoticesError;
import org.dcsa.standards.specifications.standards.an.v101.messages.PostArrivalNoticesRequest;
import org.dcsa.standards.specifications.standards.an.v101.messages.PostArrivalNoticesResponse;
import org.dcsa.standards.specifications.standards.an.v101.model.ActiveReeferSettings;
import org.dcsa.standards.specifications.standards.an.v101.model.ArrivalNotice;
import org.dcsa.standards.specifications.standards.an.v101.model.ArrivalNoticeNotification;
import org.dcsa.standards.specifications.standards.an.v101.model.CargoItem;
import org.dcsa.standards.specifications.standards.an.v101.model.Charge;
import org.dcsa.standards.specifications.standards.an.v101.model.ConsignmentItem;
import org.dcsa.standards.specifications.standards.an.v101.model.CustomsClearance;
import org.dcsa.standards.specifications.standards.an.v101.model.CustomsReference;
import org.dcsa.standards.specifications.standards.an.v101.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.an.v101.model.DocumentParty;
import org.dcsa.standards.specifications.standards.an.v101.model.EmbeddedDocument;
import org.dcsa.standards.specifications.standards.an.v101.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.an.v101.model.Equipment;
import org.dcsa.standards.specifications.standards.an.v101.model.ExportLicense;
import org.dcsa.standards.specifications.standards.an.v101.model.FreeTime;
import org.dcsa.standards.specifications.standards.an.v101.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.an.v101.model.ImmediateTransportationEntry;
import org.dcsa.standards.specifications.standards.an.v101.model.ImportLicense;
import org.dcsa.standards.specifications.standards.an.v101.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.an.v101.model.Leg;
import org.dcsa.standards.specifications.standards.an.v101.model.Limits;
import org.dcsa.standards.specifications.standards.an.v101.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.an.v101.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.an.v101.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.an.v101.model.PaymentRemittance;
import org.dcsa.standards.specifications.standards.an.v101.model.PickupInformation;
import org.dcsa.standards.specifications.standards.an.v101.model.Reference;
import org.dcsa.standards.specifications.standards.an.v101.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.an.v101.model.ReleaseInformation;
import org.dcsa.standards.specifications.standards.an.v101.model.ReturnInformation;
import org.dcsa.standards.specifications.standards.an.v101.model.Seal;
import org.dcsa.standards.specifications.standards.an.v101.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.an.v101.model.Transport;
import org.dcsa.standards.specifications.standards.an.v101.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.an.v101.model.VesselVoyage;
import org.dcsa.standards.specifications.standards.an.v101.types.FreeTimeTimeUnitCode;
import org.dcsa.standards.specifications.standards.an.v101.types.FreeTimeTypeCode;
import org.dcsa.standards.specifications.standards.core.v103.model.Address;
import org.dcsa.standards.specifications.standards.core.v103.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v103.model.ClassifiedDateTime;
import org.dcsa.standards.specifications.standards.core.v103.model.Facility;
import org.dcsa.standards.specifications.standards.core.v103.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v103.model.Location;
import org.dcsa.standards.specifications.standards.core.v103.model.Weight;
import org.dcsa.standards.specifications.standards.core.v103.types.CountryCode;
import org.dcsa.standards.specifications.standards.core.v103.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v103.types.IsoEquipmentCode;
import org.dcsa.standards.specifications.standards.core.v103.types.ModeOfTransportCode;
import org.dcsa.standards.specifications.standards.core.v103.types.UniversalVoyageReference;
import org.dcsa.standards.specifications.standards.core.v103.types.VesselIMONumber;
import org.dcsa.standards.specifications.standards.dt.v100.model.Volume;

public class ANStandardSpecification extends StandardSpecification {

  public static final String TAG_ARRIVAL_NOTICE_PUBLISHERS = "AN Publisher Endpoints";
  public static final String TAG_ARRIVAL_NOTICE_SUBSCRIBERS = "AN Subscriber Endpoints";

  private final GetArrivalNoticesEndpoint getArrivalNoticesEndpoint;

  public ANStandardSpecification() {
    super("Arrival Notice", "1.0.1", "an", "an");

    openAPI.addTagsItem(
        new Tag()
            .name(TAG_ARRIVAL_NOTICE_PUBLISHERS)
            .description("Endpoints implemented by the adopters who publish arrival notices"));
    openAPI.addTagsItem(
        new Tag()
            .name(TAG_ARRIVAL_NOTICE_SUBSCRIBERS)
            .description("Endpoints implemented by the adopters who receive arrival notices"));

    openAPI.path(
        "/arrival-notices",
        new PathItem().get(operationArrivalNoticesGet()).post(operationArrivalNoticesPost()));
    openAPI.path(
        "/arrival-notice-notifications",
        new PathItem().post(operationArrivalNoticeNotificationsPost()));

    getArrivalNoticesEndpoint = new GetArrivalNoticesEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(
        "Arrival Notice", "1.0.1-20260116", "AN", "1.0.0", 4);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        ActiveReeferSettings.class,
        Address.class,
        ArrivalNotice.class,
        ArrivalNoticeNotification.class,
        CargoItem.class,
        Charge.class,
        ClassifiedDate.class,
        ClassifiedDateTime.class,
        ConsignmentItem.class,
        CountryCode.class,
        CustomsClearance.class,
        CustomsReference.class,
        DangerousGoods.class,
        DocumentParty.class,
        EmbeddedDocument.class,
        EmergencyContactDetails.class,
        Equipment.class,
        EquipmentReference.class,
        GetArrivalNoticesError.class,
        ExportLicense.class,
        Facility.class,
        FeedbackElement.class,
        FreeTime.class,
        FreeTimeTimeUnitCode.class,
        FreeTimeTypeCode.class,
        GeoCoordinate.class,
        GetArrivalNoticesResponse.class,
        IdentifyingCode.class,
        ImmediateTransportationEntry.class,
        ImportLicense.class,
        InnerPackaging.class,
        IsoEquipmentCode.class,
        Leg.class,
        Limits.class,
        Location.class,
        ModeOfTransportCode.class,
        NationalCommodityCode.class,
        OuterPackaging.class,
        PartyContactDetail.class,
        PaymentRemittance.class,
        PickupInformation.class,
        PostArrivalNoticesError.class,
        PostArrivalNoticeNotificationsRequest.class,
        PostArrivalNoticesRequest.class,
        PostArrivalNoticesResponse.class,
        Reference.class,
        ReferenceConsignmentItem.class,
        ReleaseInformation.class,
        ReturnInformation.class,
        Seal.class,
        TaxLegalReference.class,
        Transport.class,
        UniversalVoyageReference.class,
        UtilizedTransportEquipment.class,
        VesselIMONumber.class,
        VesselVoyage.class,
        Volume.class,
        Weight.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(
        ArrivalNotice.class.getSimpleName(),
        ArrivalNoticeNotification.class.getSimpleName(),
        FeedbackElement.class.getSimpleName());
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
                            ("https://raw.githubusercontent.com/dcsaorg/DCSA-Information-Model/" +
                              "c1fd6e8fc737796301d6c77369e96109a3a60973" +
                              "/generated-resources/standards/an/v100/an-v1.0.0-data-overview-%s.csv")
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
    return getArrivalNoticesEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationArrivalNoticesGet() {
    return new Operation()
        .summary("Retrieves a list of arrival notices")
        .description(readResourceFile("openapi-get-ans-description.md"))
        .operationId("get-arrival-notices")
        .tags(Collections.singletonList(TAG_ARRIVAL_NOTICE_PUBLISHERS))
        .parameters(
            Stream.concat(
                    new GetArrivalNoticesEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of arrival notices matching the query parameters")
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
                                                        GetArrivalNoticesResponse.class))))))
                .addApiResponse("default", createErrorResponse(GetArrivalNoticesError.class)));
  }

  private Operation operationArrivalNoticesPost() {
    return new Operation()
        .summary("Sends a list of arrival notices")
        .description(readResourceFile("openapi-post-ans-description.md"))
        .operationId("post-arrival-notices")
        .tags(Collections.singletonList(TAG_ARRIVAL_NOTICE_SUBSCRIBERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of arrival notices")
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
                                                PostArrivalNoticesRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("Arrival notices response")
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
                                                        PostArrivalNoticesResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostArrivalNoticesError.class)));
  }

  private Operation operationArrivalNoticeNotificationsPost() {
    return new Operation()
        .summary("Sends a list of arrival notice lightweight notifications")
        .description(readResourceFile("openapi-post-anns-description.md"))
        .operationId("post-arrival-notice-notifications")
        .tags(Collections.singletonList(TAG_ARRIVAL_NOTICE_SUBSCRIBERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of arrival notice lightweight notifications")
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
                                                PostArrivalNoticeNotificationsRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("Arrival notice notifications response")
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
                                                        PostArrivalNoticesResponse.class))))))
                .addApiResponse("default", createErrorResponse(PostArrivalNoticesError.class)));
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
