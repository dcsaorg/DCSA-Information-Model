package org.dcsa.standards.specifications.standards.cs.v102;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v101.CsP2p101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v102.model.*;

/** CS 1.0.2 Point-to-Point Routings standard specification module. */
public class CsP2p102StandardSpecification extends CsP2p101StandardSpecification {

  private final GetPointToPointRoutesV102Endpoint getPointToPointRoutesV102Endpoint;

  public CsP2p102StandardSpecification() {
    this("1.0.2", "1.0.1");
  }

  protected CsP2p102StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getPointToPointRoutesV102Endpoint = new GetPointToPointRoutesV102Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            PointToPoint.class,
            PlaceOfArrival.class,
            PlaceOfDeparture.class,
            Leg.class,
            VesselTransport.class,
            BargeTransport.class,
            OtherTransport.class,
            Vessel.class,
            Barge.class,
            Location.class,
            SolutionFootprint.class,
            Footprint.class,
            ErrorResponse.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getPointToPointRoutesV102Endpoint;
  }
}
