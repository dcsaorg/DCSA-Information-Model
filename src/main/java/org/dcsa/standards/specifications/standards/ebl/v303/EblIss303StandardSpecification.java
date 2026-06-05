package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedHSCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Address;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.SupportingDocument;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.City;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Facility;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model.Reference;
import org.dcsa.standards.specifications.standards.ebl.v303.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.IssuingParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.Party;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_iss.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.CargoItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.CarriersAgentAtDestination;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Charge;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.ConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.InnerPackaging;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnBehalfOfConsignee;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnBehalfOfShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OnwardInlandRouting;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.OuterPackaging;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.PlaceOfDelivery;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.PlaceOfReceipt;
import org.dcsa.standards.specifications.standards.ebl.v303.model_td.VesselVoyage;

public class EblIss303StandardSpecification extends Ebl303StandardSpecification {

  public EblIss303StandardSpecification() {
    super("Issuance", "iss");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.2
            Address.class,
            City.class,
            IssuanceRequest.class,
            OtherDocumentParty.class,
            SupportingDocument.class,
            // 3.0.3 TD
            CargoItem.class,
            CarriersAgentAtDestination.class,
            Charge.class,
            Consignee.class,
            ConsignmentItem.class,
            Endorsee.class,
            ExtendedHSCode.class,
            ExtendedNationalCommodityCode.class,
            Facility.class,
            InnerPackaging.class,
            IssuingParty.class,
            NationalCommodityCode.class,
            NetExplosiveContent.class,
            OnBehalfOfConsignee.class,
            OnBehalfOfShipper.class,
            OnwardInlandRouting.class,
            OuterPackaging.class,
            PartyAddress.class,
            PlaceOfDelivery.class,
            PlaceOfReceipt.class,
            Reference.class,
            ReferenceConsignmentItem.class,
            VesselVoyage.class,
            // 3.0.3 ISS
            DangerousGoods.class,
            DocumentParties.class,
            IdentifyingCode.class,
            IssueToParty.class,
            NotifyParty.class,
            Party.class,
            Shipper.class,
            TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(IssuanceRequest.class.getSimpleName());
  }
}
