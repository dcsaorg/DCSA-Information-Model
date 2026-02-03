package org.dcsa.standards.specifications.standards.booking.v2;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.booking.v2.model.Booking;
import org.dcsa.standards.specifications.standards.booking.v200.Booking200StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v201.Booking201StandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class Booking2StandardSpecificationsTest {

  @Test
  void testEbl3StandardSpecifications() {
    // in this order, for correct diff calculation
    buildAndCheckV200();
    buildAndCheckV201();
  }

  private static void buildAndCheckV200() {
    Booking200StandardSpecification booking200StandardSpecification =
        new Booking200StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        Booking.class.getSimpleName(),
        "./src/main/resources/standards/booking/v200/booking_v2.0.0.yaml",
        booking200StandardSpecification);
    booking200StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV201() {
    Booking201StandardSpecification booking201StandardSpecification =
        new Booking201StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        Booking.class.getSimpleName(),
        "./src/main/resources/standards/booking/v201/booking_v2.0.1.yaml",
        booking201StandardSpecification);
    booking201StandardSpecification.generateArtifacts();
  }
}
