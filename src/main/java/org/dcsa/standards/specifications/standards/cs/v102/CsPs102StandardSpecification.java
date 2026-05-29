package org.dcsa.standards.specifications.standards.cs.v102;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v101.CsPs101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.messages.ErrorResponse;
import org.dcsa.standards.specifications.standards.cs.v102.model.*;

/** CS 1.0.2 Port Schedules standard specification module. */
public class CsPs102StandardSpecification extends CsPs101StandardSpecification {

  private final GetPortSchedulesV102Endpoint getPortSchedulesV102Endpoint;

  public CsPs102StandardSpecification() {
    this("1.0.2", "1.0.1");
  }

  protected CsPs102StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getPortSchedulesV102Endpoint = new GetPortSchedulesV102Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(Timestamp.class, Vessel.class, PortScheduleLocation.class, ErrorResponse.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getPortSchedulesV102Endpoint;
  }
}
