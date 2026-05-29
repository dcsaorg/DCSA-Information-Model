package org.dcsa.standards.specifications.standards.cs.v101;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v100.CsP2p100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v101.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v101.model.*;

/** CS 1.0.1 Point-to-Point Routings standard specification module. */
public class CsP2p101StandardSpecification extends CsP2p100StandardSpecification {

  private final GetPointToPointRoutesV101Endpoint getPointToPointRoutesV101Endpoint;

  public CsP2p101StandardSpecification() {
    this("1.0.1", "1.0.0");
  }

  protected CsP2p101StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getPointToPointRoutesV101Endpoint = new GetPointToPointRoutesV101Endpoint();
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
            CutOffTime.class,
            Barge.class,
            Vessel.class,
            ServicePartner.class,
            ErrorResponse.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getPointToPointRoutesV101Endpoint;
  }
}
