package org.dcsa.standards.specifications.standards.booking.v2;

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
import org.dcsa.standards.specifications.standards.booking.v2.model.ActiveReeferSettings;
import org.dcsa.standards.specifications.standards.booking.v2.model.Address;
import org.dcsa.standards.specifications.standards.booking.v2.model.CargoGrossVolume;
import org.dcsa.standards.specifications.standards.booking.v2.model.CargoGrossWeight;
import org.dcsa.standards.specifications.standards.booking.v2.model.CargoNetVolume;
import org.dcsa.standards.specifications.standards.booking.v2.model.CargoNetWeight;
import org.dcsa.standards.specifications.standards.booking.v2.model.Charge;
import org.dcsa.standards.specifications.standards.booking.v2.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.booking.v2.model.ExportLicense;
import org.dcsa.standards.specifications.standards.booking.v2.model.Facility;
import org.dcsa.standards.specifications.standards.booking.v2.model.GrossWeight;
import org.dcsa.standards.specifications.standards.booking.v2.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.booking.v2.model.ImportLicense;
import org.dcsa.standards.specifications.standards.booking.v2.model.Limits;
import org.dcsa.standards.specifications.standards.booking.v2.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v2.model.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.booking.v2.model.NetVolume;
import org.dcsa.standards.specifications.standards.booking.v2.model.NetWeight;
import org.dcsa.standards.specifications.standards.booking.v2.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.booking.v2.model.PartyAddress;
import org.dcsa.standards.specifications.standards.booking.v2.model.Reference;
import org.dcsa.standards.specifications.standards.booking.v2.model.TareWeight;
import org.dcsa.standards.specifications.standards.dt.v100.model.CargoItem;
import org.dcsa.standards.specifications.standards.dt.v100.model.ConsignmentItem;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.EmergencyContactDetails;
import org.dcsa.standards.specifications.standards.dt.v100.model.Equipment;
import org.dcsa.standards.specifications.standards.dt.v100.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.dt.v100.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;
import org.dcsa.standards.specifications.standards.dt.v100.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.dt.v100.model.Volume;
import org.dcsa.standards.specifications.standards.booking.v2.model.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.booking.v2.model.Booking;
import org.dcsa.standards.specifications.standards.booking.v2.model.BookingAgent;
import org.dcsa.standards.specifications.standards.booking.v2.model.CargoGrossWeightReq;
import org.dcsa.standards.specifications.standards.booking.v2.model.CarrierBookingOffice;
import org.dcsa.standards.specifications.standards.booking.v2.model.Commodity;
import org.dcsa.standards.specifications.standards.booking.v2.model.ConfirmedEquipment;
import org.dcsa.standards.specifications.standards.booking.v2.model.Consignee;
import org.dcsa.standards.specifications.standards.booking.v2.model.ContainerPositioning;
import org.dcsa.standards.specifications.standards.booking.v2.model.ContainerPositioningEstimated;
import org.dcsa.standards.specifications.standards.booking.v2.model.ContainerPositioningLocation;
import org.dcsa.standards.specifications.standards.booking.v2.model.DestinationChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.booking.v2.model.DischargeLocation;
import org.dcsa.standards.specifications.standards.booking.v2.model.DocumentParties;
import org.dcsa.standards.specifications.standards.booking.v2.model.EmptyContainerDepotReleaseLocation;
import org.dcsa.standards.specifications.standards.booking.v2.model.EmptyContainerPickup;
import org.dcsa.standards.specifications.standards.booking.v2.model.Feedback;
import org.dcsa.standards.specifications.standards.booking.v2.model.InvoicePayableAt;
import org.dcsa.standards.specifications.standards.booking.v2.model.LoadLocation;
import org.dcsa.standards.specifications.standards.booking.v2.model.Location;
import org.dcsa.standards.specifications.standards.booking.v2.model.OriginChargesPaymentTerm;
import org.dcsa.standards.specifications.standards.booking.v2.model.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.booking.v2.model.Party;
import org.dcsa.standards.specifications.standards.booking.v2.model.PlaceOfBLIssue;
import org.dcsa.standards.specifications.standards.booking.v2.model.RequestedEquipment;
import org.dcsa.standards.specifications.standards.booking.v2.model.ServiceContractOwner;
import org.dcsa.standards.specifications.standards.booking.v2.model.ShipmentCutOffTime;
import org.dcsa.standards.specifications.standards.booking.v2.model.ShipmentLocation;
import org.dcsa.standards.specifications.standards.booking.v2.model.Shipper;
import org.dcsa.standards.specifications.standards.booking.v2.model.Transport;
import org.dcsa.standards.specifications.standards.booking.v2.model.Vessel;

/**
 * Booking 2.x standard specification created for maintaining and exporting a new-style IM and DO.
 */
public class Booking2StandardSpecification extends StandardSpecification {

  private final GetBookingEndpoint getBookingEndpoint;

  public Booking2StandardSpecification(String versionNumber) {
    super("Booking", versionNumber, "booking", "booking");

    openAPI.path(
        "/v2/bookings/{carierBookingReference}", new PathItem().get(operationBookingGet()));

    getBookingEndpoint = new GetBookingEndpoint();
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "", "", 2);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        // dt.v100.model
        CargoItem.class,
        ConsignmentItem.class,
        CustomsReference.class,
        EmergencyContactDetails.class,
        Equipment.class,
        GeoCoordinate.class,
        InnerPackaging.class,
        PartyContactDetail.class,
        ReferenceConsignmentItem.class,
        Seal.class,
        TaxLegalReference.class,
        UtilizedTransportEquipment.class,
        Volume.class,
        // booking.v2.model
        ActiveReeferSettings.class,
        Address.class,
        AdvanceManifestFiling.class,
        Booking.class,
        BookingAgent.class,
        CargoGrossVolume.class,
        CargoGrossWeight.class,
        CargoGrossWeightReq.class,
        CargoNetVolume.class,
        CargoNetWeight.class,
        CarrierBookingOffice.class,
        Charge.class,
        Commodity.class,
        ConfirmedEquipment.class,
        Consignee.class,
        ContainerPositioning.class,
        ContainerPositioningEstimated.class,
        ContainerPositioningLocation.class,
        DangerousGoods.class,
        DestinationChargesPaymentTerm.class,
        DischargeLocation.class,
        DocumentParties.class,
        EmptyContainerDepotReleaseLocation.class,
        EmptyContainerPickup.class,
        ExportLicense.class,
        Facility.class,
        Feedback.class,
        GrossWeight.class,
        IdentifyingCode.class,
        ImportLicense.class,
        InvoicePayableAt.class,
        Limits.class,
        LoadLocation.class,
        Location.class,
        NationalCommodityCode.class,
        NetExplosiveContent.class,
        NetVolume.class,
        NetWeight.class,
        OriginChargesPaymentTerm.class,
        OtherDocumentParty.class,
        OuterPackaging.class,
        Party.class,
        PartyAddress.class,
        PlaceOfBLIssue.class,
        Reference.class,
        RequestedEquipment.class,
        ServiceContractOwner.class,
        ShipmentCutOffTime.class,
        ShipmentLocation.class,
        Shipper.class,
        TareWeight.class,
        Transport.class,
        Vessel.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(Booking.class.getSimpleName());
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
    return getBookingEndpoint;
  }

  @Override
  protected boolean swapOldAndNewInDataOverview() {
    return false;
  }

  private static Operation operationBookingGet() {
    return new Operation()
        .summary("Retrieves a booking")
        .description("")
        .operationId("get-booking")
        .parameters(new GetBookingEndpoint().getQueryParameters())
        .responses(
            new ApiResponses()
                .addApiResponse(
                    "200",
                    new ApiResponse()
                        .description(
                            "Booking with the carrier reference matching the URL parameter")
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
                                                        Booking.class)))))));
  }
}
