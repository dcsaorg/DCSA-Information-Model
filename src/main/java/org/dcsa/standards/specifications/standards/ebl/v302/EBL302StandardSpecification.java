package org.dcsa.standards.specifications.standards.ebl.v302;

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
import org.dcsa.standards.specifications.standards.dt.v100.model.Charge;
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
import org.dcsa.standards.specifications.standards.dt.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Address;
import org.dcsa.standards.specifications.standards.ebl.v302.model.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Buyer;
import org.dcsa.standards.specifications.standards.ebl.v302.model.BuyerHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoGrossVolume;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoGrossWeight;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoItem;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoItemHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoNetVolume;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoNetWeight;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CarriersAgentAtDestination;
import org.dcsa.standards.specifications.standards.ebl.v302.model.City;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ConsigneeHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ConsignmentItemHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ConsignmentItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CustomsReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v302.model.DestinationChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.ebl.v302.model.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v302.model.DocumentPartiesHouseBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.DocumentPartiesShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v302.model.EndorseeShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Equipment;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ExportLicenseShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Feedback;
import org.dcsa.standards.specifications.standards.ebl.v302.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.ebl.v302.model.GrossWeight;
import org.dcsa.standards.specifications.standards.ebl.v302.model.HouseBillOfLading;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ImportLicenseShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.InvoicePayableAt;
import org.dcsa.standards.specifications.standards.ebl.v302.model.InvoicePayableAtShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssuingParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NetVolume;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NetWeight;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NotifyPartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OnBehalfOfConsignee;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OnBehalfOfShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OnwardInlandRouting;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OriginChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OtherDocumentPartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OtherDocumentPartyShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OuterPackagingHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.OuterPackagingShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Party;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyContactDetailHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyContactDetailWithPattern;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfAcceptance;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfDelivery;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfFinalDelivery;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfIssue;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfReceipt;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PortOfDischarge;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PortOfLoading;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Seller;
import org.dcsa.standards.specifications.standards.ebl.v302.model.SellerHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ShipperHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ShippingInstructionsRequestor;
import org.dcsa.standards.specifications.standards.ebl.v302.model.SupportingDocument;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TareWeight;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Transports;
import org.dcsa.standards.specifications.standards.ebl.v302.model.UtilizedTransportEquipmentHBL;
import org.dcsa.standards.specifications.standards.ebl.v302.model.UtilizedTransportEquipmentShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.VesselVoyage;

/** eBL 302 standard specification created for maintaining and exporting a new-style IM and DO. */
public class EBL302StandardSpecification extends StandardSpecification {

  private final GetTransportDocumentEndpoint getTransportDocumentEndpoint;

  public EBL302StandardSpecification(String moduleName, String modulePrefix) {
    super("Bill of Lading - " + moduleName, "3.0.2", "ebl", "ebl-" + modulePrefix);

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
