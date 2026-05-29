package org.dcsa.standards.specifications.standards.ovs.v302;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.generator.QueryParametersFilterEndpoint;
import org.dcsa.standards.specifications.standards.ovs.v301.OVS301StandardSpecification;
import org.dcsa.standards.specifications.standards.ovs.v302.model.Address;
import org.dcsa.standards.specifications.standards.ovs.v302.model.Timestamp;
import org.dcsa.standards.specifications.standards.ovs.v302.model.TransportCall;

/** OVS 3.0.2 standard specification building upon OVS 3.0.1. */
public class OVS302StandardSpecification extends OVS301StandardSpecification {

  private final GetServiceSchedulesV302Endpoint getServiceSchedulesV302Endpoint;

  public OVS302StandardSpecification() {
    super("3.0.2", "3.0.1");
    getServiceSchedulesV302Endpoint = new GetServiceSchedulesV302Endpoint();
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), Set.of(Address.class, Timestamp.class, TransportCall.class));
  }

  @Override
  protected QueryParametersFilterEndpoint getQueryParametersFilterEndpoint() {
    return getServiceSchedulesV302Endpoint;
  }
}
