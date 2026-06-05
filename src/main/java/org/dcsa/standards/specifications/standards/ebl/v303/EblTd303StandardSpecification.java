package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedHSCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.City;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.Transports;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v303.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v303.model.Reference;
import org.dcsa.standards.specifications.standards.ebl.v303.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.ebl.v303.model.UtilizedTransportEquipmentHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.CargoItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.CarriersAgentAtDestination;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Charge;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.ConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.InnerPackaging;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.IssuingParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnBehalfOfConsignee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnBehalfOfShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnwardInlandRouting;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OuterPackaging;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Party;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.PlaceOfDelivery;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.PlaceOfReceipt;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.VesselVoyage;

public class EblTd303StandardSpecification extends Ebl303StandardSpecification {

  public EblTd303StandardSpecification() {
    super("Transport Document", "td");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.1
            // 3.0.2
            City.class,
            OtherDocumentParty.class,
            TransportDocument.class,
            // 3.0.3
            CargoItem.class,
            CarriersAgentAtDestination.class,
            Charge.class,
            Consignee.class,
            ConsignmentItem.class,
            DangerousGoods.class,
            DocumentParties.class,
            Endorsee.class,
            ExtendedHSCode.class,
            ExtendedNationalCommodityCode.class,
            IdentifyingCode.class,
            InnerPackaging.class,
            IssuingParty.class,
            NationalCommodityCode.class,
            NetExplosiveContent.class,
            NotifyParty.class,
            OnBehalfOfConsignee.class,
            OnBehalfOfShipper.class,
            OnwardInlandRouting.class,
            OuterPackaging.class,
            Party.class,
            PartyAddress.class,
            PlaceOfDelivery.class,
            PlaceOfReceipt.class,
            Reference.class,
            ReferenceConsignmentItem.class,
            Shipper.class,
            Transports.class,
            UtilizedTransportEquipment.class,
            UtilizedTransportEquipmentHBL.class,
            VesselVoyage.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
