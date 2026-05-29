package org.dcsa.standards.specifications.standards.cs.v103;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v102.CsVs102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.model.*;

/** CS 1.0.3 Vessel Schedules standard specification module. */
public class CsVs103StandardSpecification extends CsVs102StandardSpecification {

  private final GetVesselSchedulesV103Endpoint getVesselSchedulesV103Endpoint;

  public CsVs103StandardSpecification() {
    this("1.0.3", "1.0.2");
  }

  protected CsVs103StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getVesselSchedulesV103Endpoint = new GetVesselSchedulesV103Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), Set.of(Timestamp.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getVesselSchedulesV103Endpoint;
  }
}
