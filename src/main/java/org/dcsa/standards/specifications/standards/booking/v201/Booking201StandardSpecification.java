package org.dcsa.standards.specifications.standards.booking.v201;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.booking.v2.Booking2StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v201.model.Booking;
import org.dcsa.standards.specifications.standards.booking.v201.model.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.booking.v201.model.ShipmentLocation;
import org.dcsa.standards.specifications.standards.booking.v201.model.Vessel;

public class Booking201StandardSpecification extends Booking2StandardSpecification {

  public Booking201StandardSpecification() {
    super("2.0.1", "2.0.0");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(Booking.class, OtherDocumentParty.class, ShipmentLocation.class, Vessel.class));
  }
}
