package org.dcsa.standards.specifications.standards.cs.v103;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v102.CsP2p102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.model.*;

/** CS 1.0.3 Point-to-Point Routings standard specification module. */
public class CsP2p103StandardSpecification extends CsP2p102StandardSpecification {

  public CsP2p103StandardSpecification() {
    this("1.0.3", "1.0.2");
  }

  protected CsP2p103StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            PointToPoint.class,
            PlaceOfReceipt.class,
            PlaceOfDelivery.class,
            PlaceOfArrival.class,
            PlaceOfDeparture.class,
            Leg.class,
            VesselTransport.class,
            IntermediateCall.class,
            SolutionFootprint.class,
            Footprint.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return new org.dcsa.standards.specifications.standards.cs.v102
        .GetPointToPointRoutesV102Endpoint();
  }
}
