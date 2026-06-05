package org.dcsa.standards.specifications.standards.cs.v103;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.cs.v102.CsPs102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.model.*;

/** CS 1.0.3 Port Schedules standard specification module. */
public class CsPs103StandardSpecification extends CsPs102StandardSpecification {

  public CsPs103StandardSpecification() {
    this("1.0.3", "1.0.2");
  }

  protected CsPs103StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), Set.of(Timestamp.class, TransportCall.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return new org.dcsa.standards.specifications.standards.cs.v102.GetPortSchedulesV102Endpoint();
  }
}
