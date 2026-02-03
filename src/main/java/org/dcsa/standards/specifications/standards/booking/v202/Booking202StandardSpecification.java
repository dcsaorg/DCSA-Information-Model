package org.dcsa.standards.specifications.standards.booking.v202;

import java.util.Set;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.dcsa.standards.specifications.standards.booking.v2.Booking2StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v201.model.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.booking.v202.model.Address;
import org.dcsa.standards.specifications.standards.booking.v202.model.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.booking.v202.model.Booking;
import org.dcsa.standards.specifications.standards.booking.v202.model.ConfirmedEquipment;
import org.dcsa.standards.specifications.standards.booking.v202.model.CustomsReference;
import org.dcsa.standards.specifications.standards.booking.v202.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.booking.v202.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v202.model.OriginEmptyContainerPickup;
import org.dcsa.standards.specifications.standards.booking.v202.model.PartyAddress;
import org.dcsa.standards.specifications.standards.booking.v202.model.PlaceOfBLIssue;
import org.dcsa.standards.specifications.standards.booking.v202.model.RequestedEquipment;
import org.dcsa.standards.specifications.standards.booking.v202.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.booking.v202.model.Transport;

public class Booking202StandardSpecification extends Booking2StandardSpecification {

  public Booking202StandardSpecification() {
    super("2.0.2", "2.0.1");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), getReplacementClasses());
  }

  public static @NonNull Set<Class<?>> getReplacementClasses() {
    return Set.of(
        // 2.0.1
        OtherDocumentParty.class,
        // 2.0.2
        Address.class,
        AdvanceManifestFiling.class,
        Booking.class,
        ConfirmedEquipment.class,
        CustomsReference.class,
        IdentifyingCode.class,
        NationalCommodityCode.class,
        OriginEmptyContainerPickup.class,
        PartyAddress.class,
        PlaceOfBLIssue.class,
        RequestedEquipment.class,
        TaxLegalReference.class,
        Transport.class);
  }
}
