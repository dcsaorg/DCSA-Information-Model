package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedHSCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.PlaceOfAcceptance;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.PlaceOfFinalDelivery;
import org.dcsa.standards.specifications.standards.ebl.v303.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v303.model.Reference;
import org.dcsa.standards.specifications.standards.ebl.v303.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.ebl.v303.model.UtilizedTransportEquipment;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.AddressHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.Buyer;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.BuyerHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.CargoItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ConsignmentItemHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ConsignmentItemShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.DocumentPartiesHouseBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.DocumentPartiesShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.EndorseeShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.NotifyPartyHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.NotifyPartyShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.OnBehalfOfConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.OnBehalfOfShipperShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.OtherDocumentPartyShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.OuterPackagingHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.OuterPackagingShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.PartyShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.Seller;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.SellerHBL;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ShipperShipper;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.ShippingInstructionsRequestor;
import org.dcsa.standards.specifications.standards.ebl.v303.model_si.UtilizedTransportEquipmentShipper;

public class EblSi303StandardSpecification extends Ebl303StandardSpecification {

  public EblSi303StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.2
            AdvanceManifestFiling.class,
            PlaceOfAcceptance.class,
            PlaceOfFinalDelivery.class,
            // 3.0.3
            AddressHBL.class,
            Buyer.class,
            BuyerHBL.class,
            CargoItemShipper.class,
            ConsigneeShipper.class,
            ConsignmentItemHBL.class,
            ConsignmentItemShipper.class,
            DocumentPartiesHouseBL.class,
            DocumentPartiesShippingInstructions.class,
            EndorseeShipper.class,
            ExtendedHSCode.class,
            ExtendedNationalCommodityCode.class,
            IdentifyingCode.class,
            IssueToParty.class,
            NationalCommodityCode.class,
            NotifyPartyHBL.class,
            NotifyPartyShipper.class,
            OnBehalfOfConsigneeShipper.class,
            OnBehalfOfShipperShipper.class,
            OtherDocumentPartyShippingInstructions.class,
            OuterPackagingShipper.class,
            OuterPackagingHBL.class,
            PartyAddress.class,
            PartyShipper.class,
            Reference.class,
            ReferenceConsignmentItem.class,
            Seller.class,
            SellerHBL.class,
            ShipperShipper.class,
            ShippingInstructions.class,
            ShippingInstructionsRequestor.class,
            UtilizedTransportEquipment.class,
            UtilizedTransportEquipmentShipper.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(ShippingInstructions.class.getSimpleName());
  }
}
