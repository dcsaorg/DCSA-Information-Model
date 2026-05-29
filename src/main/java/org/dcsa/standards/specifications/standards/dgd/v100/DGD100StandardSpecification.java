package org.dcsa.standards.specifications.standards.dgd.v100;

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
import org.dcsa.standards.specifications.standards.core.v200.model.Address;
import org.dcsa.standards.specifications.standards.core.v200.model.Facility;
import org.dcsa.standards.specifications.standards.core.v200.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.core.v200.model.Location;
import org.dcsa.standards.specifications.standards.core.v200.model.Seal;
import org.dcsa.standards.specifications.standards.core.v200.model.Volume;
import org.dcsa.standards.specifications.standards.core.v200.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.core.v200.model.Weight;
import org.dcsa.standards.specifications.standards.dgd.v100.messages.GetDGDeclarationsResponse;
import org.dcsa.standards.specifications.standards.dgd.v100.messages.PostDGDeclarationsRequest;
import org.dcsa.standards.specifications.standards.dgd.v100.messages.PostDGDeclarationsResponse;
import org.dcsa.standards.specifications.standards.dgd.v100.model.Activity;
import org.dcsa.standards.specifications.standards.dgd.v100.model.CargoItem;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ConsignmentItem;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ContainerPackingCertificate;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DGDeclaration;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DocumentParty;
import org.dcsa.standards.specifications.standards.dgd.v100.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.dgd.v100.model.Equipment;
import org.dcsa.standards.specifications.standards.dgd.v100.model.FissileMaterial;
import org.dcsa.standards.specifications.standards.dgd.v100.model.HaulageAcceptance;
import org.dcsa.standards.specifications.standards.dgd.v100.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.dgd.v100.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.dgd.v100.model.Limits;
import org.dcsa.standards.specifications.standards.dgd.v100.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.dgd.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dgd.v100.model.RadioactiveMaterial;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ReceivingOrganisationReceipt;
import org.dcsa.standards.specifications.standards.dgd.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ShipmentDetails;
import org.dcsa.standards.specifications.standards.dgd.v100.model.ShipperDeclaration;
import org.dcsa.standards.specifications.standards.dgd.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.dgd.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.dgd.v100.model.VesselVoyageDetails;

public class DGD100StandardSpecification extends StandardSpecification {

  public static final String TAG_DGD_PRODUCERS = "DGD Producer Endpoints";
  public static final String TAG_DGD_CONSUMERS = "DGD Consumer Endpoints";

  private final GetDGDeclarationsEndpoint getDGDeclarationsEndpoint;

  public DGD100StandardSpecification() {
    super("Dangerous Good Declaration", "1.0.0", "dgd", "dgd");

    openAPI.addTagsItem(
        new Tag()
            .name(TAG_DGD_PRODUCERS)
            .description("Endpoints implemented by the DGD Producers"));
    openAPI.addTagsItem(
        new Tag()
            .name(TAG_DGD_CONSUMERS)
            .description("Endpoints implemented by the DGD Consumers"));

    openAPI.path(
        "/dgds",
        new PathItem().get(operationDGDeclarationsGet()).post(operationDGDeclarationsPost()));

    getDGDeclarationsEndpoint = new GetDGDeclarationsEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata("Dangerous Good Declaration", "1.0.0", "", "", 4);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        Activity.class,
        Address.class,
        CargoItem.class,
        ConsignmentItem.class,
        ContainerPackingCertificate.class,
        DangerousGoods.class,
        DGDeclaration.class,
        DocumentParty.class,
        EmergencyContactDetails.class,
        Equipment.class,
        Facility.class,
        FissileMaterial.class,
        GeoCoordinate.class,
        GetDGDeclarationsResponse.class,
        HaulageAcceptance.class,
        IdentifyingCode.class,
        InnerPackaging.class,
        Limits.class,
        Location.class,
        OuterPackaging.class,
        PartyContactDetail.class,
        PostDGDeclarationsRequest.class,
        PostDGDeclarationsResponse.class,
        RadioactiveMaterial.class,
        ReceivingOrganisationReceipt.class,
        Reference.class,
        ReferenceConsignmentItem.class,
        Seal.class,
        ShipmentDetails.class,
        ShipperDeclaration.class,
        TaxLegalReference.class,
        UtilizedTransportEquipment.class,
        VesselVoyageDetails.class,
        Volume.class,
        VoyageNumberOrReference.class,
        Weight.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(DGDeclaration.class.getSimpleName());
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
    return getDGDeclarationsEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private Operation operationDGDeclarationsGet() {
    return new Operation()
        .summary("Retrieves a list of DG declarations")
        .description(readResourceFile("openapi-get-dg-declarations-description.md"))
        .operationId("get-dg-declarations")
        .tags(Collections.singletonList(TAG_DGD_PRODUCERS))
        .parameters(
            Stream.concat(
                    new GetDGDeclarationsEndpoint().getQueryParameters().stream(),
                    Stream.of(getApiVersionHeaderParameter()))
                .toList())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("List of DG declarations matching the query parameters")
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
                                                        GetDGDeclarationsResponse.class)))))));
  }

  private Operation operationDGDeclarationsPost() {
    return new Operation()
        .summary("Sends a list of DG declarations")
        .description(readResourceFile("openapi-post-dg-declarations-description.md"))
        .operationId("post-dg-declarations")
        .tags(Collections.singletonList(TAG_DGD_CONSUMERS))
        .parameters(List.of(getApiVersionHeaderParameter()))
        .requestBody(
            new RequestBody()
                .description("List of DG declarations")
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
                                                PostDGDeclarationsRequest.class))))))
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description("DG declarations response")
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
                                                        PostDGDeclarationsResponse.class)))))));
  }
}
