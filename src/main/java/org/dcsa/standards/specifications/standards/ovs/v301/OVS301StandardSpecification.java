package org.dcsa.standards.specifications.standards.ovs.v301;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.ovs.v300.OVS3StandardSpecification;
import org.dcsa.standards.specifications.standards.ovs.v301.model.ServiceSchedule;
import org.dcsa.standards.specifications.standards.ovs.v301.model.Timestamp;
import org.dcsa.standards.specifications.standards.ovs.v301.model.TransportCall;
import org.dcsa.standards.specifications.standards.ovs.v301.model.VesselSchedule;

/** OVS 3.0.1 standard specification building upon OVS 3.0.0. */
public class OVS301StandardSpecification extends OVS3StandardSpecification {

  private final GetServiceSchedulesV301Endpoint getServiceSchedulesV301Endpoint;

  public OVS301StandardSpecification() {
    this("3.0.1", "3.0.0");
  }

  protected OVS301StandardSpecification(String versionNumber, String baselineVersion) {
    super(versionNumber, baselineVersion);
    getServiceSchedulesV301Endpoint = new GetServiceSchedulesV301Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(TransportCall.class, Timestamp.class, VesselSchedule.class, ServiceSchedule.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getServiceSchedulesV301Endpoint;
  }
}
