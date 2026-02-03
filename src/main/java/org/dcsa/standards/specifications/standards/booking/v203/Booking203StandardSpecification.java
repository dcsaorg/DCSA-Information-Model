package org.dcsa.standards.specifications.standards.booking.v203;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.booking.v2.Booking2StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v202.Booking202StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v203.model.DocumentParties;
import org.dcsa.standards.specifications.standards.booking.v203.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.booking.v203.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.booking.v203.model.IssueToParty;
import org.dcsa.standards.specifications.standards.booking.v203.model.ShipmentLocation;

public class Booking203StandardSpecification extends Booking2StandardSpecification {

  public Booking203StandardSpecification() {
    super("2.0.3", "2.0.2");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    Stream<Class<?>> v202ModelClassesStream =
        modelClassesStreamWithReplacementClasses(
            super.modelClassesStream(), Booking202StandardSpecification.getReplacementClasses());
    return modelClassesStreamWithReplacementClasses(
        v202ModelClassesStream,
        Set.of(
            DocumentParties.class,
            GeoCoordinate.class,
            IdentifyingCode.class,
            IssueToParty.class,
            ShipmentLocation.class));
  }
}
