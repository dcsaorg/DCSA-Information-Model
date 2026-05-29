package org.dcsa.standards.specifications.standards.cs.v101;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v100.CsVs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v101.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v101.model.*;

/** CS 1.0.1 Vessel Schedules standard specification module. */
public class CsVs101StandardSpecification extends CsVs100StandardSpecification {

  private final GetVesselSchedulesV101Endpoint getVesselSchedulesV101Endpoint;

  public CsVs101StandardSpecification() {
    this("1.0.1", "1.0.0");
  }

  protected CsVs101StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getVesselSchedulesV101Endpoint = new GetVesselSchedulesV101Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(Timestamp.class, CutOffTime.class, Vessel.class, ErrorResponse.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getVesselSchedulesV101Endpoint;
  }
}
