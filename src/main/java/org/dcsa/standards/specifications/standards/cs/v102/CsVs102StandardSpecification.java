package org.dcsa.standards.specifications.standards.cs.v102;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v101.CsVs101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v102.model.*;

/** CS 1.0.2 Vessel Schedules standard specification module. */
public class CsVs102StandardSpecification extends CsVs101StandardSpecification {

  private final GetVesselSchedulesV102Endpoint getVesselSchedulesV102Endpoint;

  public CsVs102StandardSpecification() {
    this("1.0.2", "1.0.1");
  }

  protected CsVs102StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getVesselSchedulesV102Endpoint = new GetVesselSchedulesV102Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            Timestamp.class,
            Vessel.class,
            VesselSchedule.class,
            TransportCall.class,
            TransportCallLocation.class,
            ErrorResponse.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getVesselSchedulesV102Endpoint;
  }
}
