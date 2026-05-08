package org.dcsa.standards.specifications.standards.booking.v204;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.booking.v203.Booking203StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v204.model.BookingAgent;
import org.dcsa.standards.specifications.standards.booking.v204.model.CarrierBookingOffice;
import org.dcsa.standards.specifications.standards.booking.v204.model.Charge;
import org.dcsa.standards.specifications.standards.booking.v204.model.Commodity;
import org.dcsa.standards.specifications.standards.booking.v204.model.Consignee;
import org.dcsa.standards.specifications.standards.booking.v204.model.ContainerPositioningLocation;
import org.dcsa.standards.specifications.standards.booking.v204.model.DangerousGoods;
import org.dcsa.standards.specifications.standards.booking.v204.model.DischargeLocation;
import org.dcsa.standards.specifications.standards.booking.v204.model.EmptyContainerDepotReleaseLocation;
import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.booking.v204.model.InnerPackaging;
import org.dcsa.standards.specifications.standards.booking.v204.model.IssueToParty;
import org.dcsa.standards.specifications.standards.booking.v204.model.LoadLocation;
import org.dcsa.standards.specifications.standards.booking.v204.model.Location;
import org.dcsa.standards.specifications.standards.booking.v204.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.model.NetExplosiveContent;
import org.dcsa.standards.specifications.standards.booking.v204.model.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.booking.v204.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.booking.v204.model.Party;
import org.dcsa.standards.specifications.standards.booking.v204.model.ServiceContractOwner;
import org.dcsa.standards.specifications.standards.booking.v204.model.ShipmentLocation;
import org.dcsa.standards.specifications.standards.booking.v204.model.Shipper;
import org.dcsa.standards.specifications.standards.booking.v204.model.Transport;
import org.dcsa.standards.specifications.standards.booking.v204.model.Vessel;
import org.dcsa.standards.specifications.standards.booking.v204.model.VesselVoyage;

public class Booking204StandardSpecification extends Booking203StandardSpecification {

  public Booking204StandardSpecification() {
    super("2.0.4", "2.0.3");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            BookingAgent.class,
            CarrierBookingOffice.class,
            Charge.class,
            Commodity.class,
            Consignee.class,
            ContainerPositioningLocation.class,
            DangerousGoods.class,
            DischargeLocation.class,
            EmptyContainerDepotReleaseLocation.class,
            ExtendedNationalCommodityCode.class,
            IdentifyingCode.class,
            InnerPackaging.class,
            IssueToParty.class,
            LoadLocation.class,
            Location.class,
            NationalCommodityCode.class,
            NetExplosiveContent.class,
            OtherDocumentParty.class,
            OuterPackaging.class,
            Party.class,
            ServiceContractOwner.class,
            ShipmentLocation.class,
            Shipper.class,
            Transport.class,
            Vessel.class,
            VesselVoyage.class));
  }
}
