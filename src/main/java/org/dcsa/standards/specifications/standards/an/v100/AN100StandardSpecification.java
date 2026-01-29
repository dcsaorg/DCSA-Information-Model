package org.dcsa.standards.specifications.standards.an.v100;

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
import org.dcsa.standards.specifications.standards.an.v100.messages.FeedbackElement;
import org.dcsa.standards.specifications.standards.an.v100.messages.GetArrivalNoticesError;
import org.dcsa.standards.specifications.standards.an.v100.messages.GetArrivalNoticesResponse;
import org.dcsa.standards.specifications.standards.an.v100.messages.PostArrivalNoticeNotificationsRequest;
import org.dcsa.standards.specifications.standards.an.v100.messages.PostArrivalNoticesError;
import org.dcsa.standards.specifications.standards.an.v100.messages.PostArrivalNoticesRequest;
import org.dcsa.standards.specifications.standards.an.v100.messages.PostArrivalNoticesResponse;
import org.dcsa.standards.specifications.standards.an.v100.model.ActiveReeferSettings;
import org.dcsa.standards.specifications.standards.an.v100.model.ArrivalNotice;
import org.dcsa.standards.specifications.standards.an.v100.model.ArrivalNoticeNotification;
import org.dcsa.standards.specifications.standards.an.v100.model.CargoItem;
import org.dcsa.standards.specifications.standards.an.v100.model.Charge;
import org.dcsa.standards.specifications.standards.an.v100.model.ConsignmentItem;
import org.dcsa.standards.specifications.standards.an.v100.model.CustomsClearance;
import org.dcsa.standards.specifications.standards.an.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.an.v100.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.an.v100.model.DocumentParty;
import org.dcsa.standards.specifications.standards.an.v100.model.EmbeddedDocument;
import org.dcsa.standards.specifications.standards.an.v100.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.an.v100.model.Equipment;
import org.dcsa.standards.specifications.standards.an.v100.model.ExportLicense;
import org.dcsa.standards.specifications.standards.an.v100.model.FreeTime;
import org.dcsa.standards.specifications.standards.an.v100.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.an.v100.model.ImmediateTransportationEntry;
import org.dcsa.standards.specifications.standards.an.v100.model.ImportLicense;
import org.dcsa.standards.specifications.standards.an.v100.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.an.v100.model.Leg;
import org.dcsa.standards.specifications.standards.an.v100.model.Limits;
import org.dcsa.standards.specifications.standards.an.v100.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.an.v100.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.an.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.an.v100.model.PaymentRemittance;
import org.dcsa.standards.specifications.standards.an.v100.model.PickupInformation;
import org.dcsa.standards.specifications.standards.an.v100.model.Reference;
import org.dcsa.standards.specifications.standards.an.v100.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.an.v100.model.ReleaseInformation;
import org.dcsa.standards.specifications.standards.an.v100.model.ReturnInformation;
import org.dcsa.standards.specifications.standards.an.v100.model.Seal;
import org.dcsa.standards.specifications.standards.an.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.an.v100.model.Transport;
import org.dcsa.standards.specifications.standards.an.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.an.v100.model.VesselVoyage;
import org.dcsa.standards.specifications.standards.an.v100.types.FreeTimeTimeUnitCode;
import org.dcsa.standards.specifications.standards.an.v100.types.FreeTimeTypeCode;
import org.dcsa.standards.specifications.standards.core.v101.model.Address;
import org.dcsa.standards.specifications.standards.core.v101.model.Facility;
import org.dcsa.standards.specifications.standards.core.v101.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;
import org.dcsa.standards.specifications.standards.core.v101.types.CountryCode;
import org.dcsa.standards.specifications.standards.core.v101.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v101.types.IsoEquipmentCode;
import org.dcsa.standards.specifications.standards.core.v101.types.ModeOfTransportCode;
import org.dcsa.standards.specifications.standards.core.v101.types.UniversalVoyageReference;
import org.dcsa.standards.specifications.standards.core.v101.types.VesselIMONumber;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDateTime;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.dt.v100.model.Volume;

public class AN100StandardSpecification extends StandardSpecification {

  public static final String TAG_ARRIVAL_NOTICE_PUBLISHERS = "AN Publisher Endpoints";
  public static final String TAG_ARRIVAL_NOTICE_SUBSCRIBERS = "AN Subscriber Endpoints";

  private final GetArrivalNoticesEndpoint getArrivalNoticesEndpoint;

  public AN100StandardSpecification() {
    super("Arrival Notice", "1.0.0", "an", "an");

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
        "Arrival Notice", "1.0.0-20251121-beta", "AN", "1.0.0-20251107-beta", 4);
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
                              "db86454af300a5df090f37a057eb64d5d2633a7f" +
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
