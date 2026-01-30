package org.dcsa.standards.specifications.standards.ebl.v3;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
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
import org.dcsa.standards.specifications.standards.dt.v100.model.ActiveReeferSettings;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.Charge;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.Facility;
import org.dcsa.standards.specifications.standards.dt.v100.model.ImportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.dt.v100.model.Limits;
import org.dcsa.standards.specifications.standards.dt.v100.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dt.v100.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.ebl.v3.model.CargoGrossVolume;
import org.dcsa.standards.specifications.standards.ebl.v3.model.CargoGrossWeight;
import org.dcsa.standards.specifications.standards.ebl.v3.model.CargoItem;
import org.dcsa.standards.specifications.standards.ebl.v3.model.CargoNetVolume;
import org.dcsa.standards.specifications.standards.ebl.v3.model.CargoNetWeight;
import org.dcsa.standards.specifications.standards.ebl.v3.model.City;
import org.dcsa.standards.specifications.standards.ebl.v3.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Equipment;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Feedback;
import org.dcsa.standards.specifications.standards.ebl.v3.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.ebl.v3.model.GrossWeight;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NetVolume;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NetWeight;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model.OnBehalfOfShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Party;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyContactDetailHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyContactDetailWithPattern;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model.PlaceOfIssue;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model.TareWeight;
import org.dcsa.standards.specifications.standards.ebl.v3.model.UtilizedTransportEquipmentHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.SupportingDocument;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.Buyer;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.BuyerHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.CargoItemHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.CargoItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsigneeHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsignmentItemHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsignmentItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.DestinationChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.DocumentPartiesHouseBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.DocumentPartiesShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.EndorseeShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ExportLicenseShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.HouseBillOfLading;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ImportLicenseShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.InvoicePayableAtShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.NotifyPartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OriginChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OtherDocumentPartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OtherDocumentPartyShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OuterPackagingHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.OuterPackagingShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.PlaceOfAcceptance;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.PlaceOfFinalDelivery;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.Seller;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.SellerHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShipperHBL;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructionsRequestor;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.UtilizedTransportEquipmentShipper;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.CarriersAgentAtDestination;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.ConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.InvoicePayableAt;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.IssuingParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.OnBehalfOfConsignee;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.OnwardInlandRouting;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.PlaceOfDelivery;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.PlaceOfReceipt;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.PortOfDischarge;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.PortOfLoading;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.Transports;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.VesselVoyage;

/** eBL 3.x standard specification created for maintaining and exporting a new-style IM and DO. */
public class Ebl3StandardSpecification extends StandardSpecification {

  protected final String modulePrefix;

  private final GetTransportDocumentEndpoint getTransportDocumentEndpoint;

  public Ebl3StandardSpecification(String moduleName, String modulePrefix, String versionNumber) {
    super("Bill of Lading - " + moduleName, versionNumber, "ebl", "ebl-" + modulePrefix);
    this.modulePrefix = modulePrefix;

    openAPI.path(
        "/v3/transport-documents/{transportDocumentReference}",
        new PathItem().get(operationTransportDocumentGet()));

    getTransportDocumentEndpoint = new GetTransportDocumentEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "", "", 2);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        ActiveReeferSettings.class,
        Address.class,
        AdvanceManifestFiling.class,
        Buyer.class,
        BuyerHBL.class,
        CargoGrossVolume.class,
        CargoGrossWeight.class,
        CargoItem.class,
        CargoItemHBL.class,
        CargoItemShipper.class,
        CargoNetVolume.class,
        CargoNetWeight.class,
        CarriersAgentAtDestination.class,
        Charge.class,
        City.class,
        Consignee.class,
        ConsigneeHBL.class,
        ConsigneeShipper.class,
        ConsignmentItem.class,
        ConsignmentItemHBL.class,
        ConsignmentItemShipper.class,
        CustomsReference.class,
        DangerousGoods.class,
        DestinationChargesPaymentTerm.class,
        DocumentParties.class,
        DocumentPartiesHouseBL.class,
        DocumentPartiesShippingInstructions.class,
        EmergencyContactDetails.class,
        Endorsee.class,
        EndorseeShipper.class,
        Equipment.class,
        ExportLicense.class,
        ExportLicenseShipper.class,
        Facility.class,
        Feedback.class,
        GeoCoordinate.class,
        GrossWeight.class,
        HouseBillOfLading.class,
        IdentifyingCode.class,
        ImportLicense.class,
        ImportLicenseShipper.class,
        InnerPackaging.class,
        InvoicePayableAt.class,
        InvoicePayableAtShippingInstructions.class,
        IssuanceRequest.class,
        IssueToParty.class,
        IssuingParty.class,
        Limits.class,
        NationalCommodityCode.class,
        NetExplosiveContent.class,
        NetVolume.class,
        NetWeight.class,
        NotifyParty.class,
        NotifyPartyHBL.class,
        OnBehalfOfConsignee.class,
        OnBehalfOfShipper.class,
        OnwardInlandRouting.class,
        OriginChargesPaymentTerm.class,
        OtherDocumentParty.class,
        OtherDocumentPartyHBL.class,
        OtherDocumentPartyShippingInstructions.class,
        OuterPackaging.class,
        OuterPackagingHBL.class,
        OuterPackagingShipper.class,
        Party.class,
        PartyAddress.class,
        PartyContactDetail.class,
        PartyContactDetailHBL.class,
        PartyContactDetailWithPattern.class,
        PartyHBL.class,
        PlaceOfAcceptance.class,
        PlaceOfDelivery.class,
        PlaceOfFinalDelivery.class,
        PlaceOfIssue.class,
        PlaceOfReceipt.class,
        PortOfDischarge.class,
        PortOfLoading.class,
        Reference.class,
        ReferenceConsignmentItem.class,
        Seal.class,
        Seller.class,
        SellerHBL.class,
        Shipper.class,
        ShipperHBL.class,
        ShippingInstructions.class,
        ShippingInstructionsRequestor.class,
        SupportingDocument.class,
        TareWeight.class,
        TaxLegalReference.class,
        TransportDocument.class,
        Transports.class,
        UtilizedTransportEquipment.class,
        UtilizedTransportEquipmentHBL.class,
        UtilizedTransportEquipmentShipper.class,
        VesselVoyage.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(
        ShippingInstructions.class.getSimpleName(), TransportDocument.class.getSimpleName());
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass() {
    return Map.ofEntries(
            Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
            Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(Map.Entry::getKey, _ -> List.of()));
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, Map<String, String>>
      getChangedPrimaryKeyByOldPrimaryKeyBySheetClass() {
    return Map.ofEntries(
        Map.entry(AttributesHierarchicalSheet.class, Map.ofEntries()),
        Map.entry(AttributesNormalizedSheet.class, Map.ofEntries()));
  }

  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getTransportDocumentEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private static Operation operationTransportDocumentGet() {
    return new Operation()
        .summary("Retrieves a transport document")
        .description("")
        .operationId("get-transport-document")
        .parameters(new GetTransportDocumentEndpoint().getQueryParameters())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description(
                            "Transport document with the reference matching the URL parameter")
                        .headers(
                            new LinkedHashMap<>(
                                Map.ofEntries(
                                    Map.entry(
                                        "API-Version",
                                        new Header().$ref("#/components/headers/API-Version")))))
                        .content(
                            new Content()
                                .addMediaType(
                                    "application/json",
                                    new MediaType()
                                        .schema(
                                            new Schema<>()
                                                .$ref(
                                                    SpecificationToolkit.getComponentSchema$ref(
                                                        TransportDocument.class)))))));
  }
}
