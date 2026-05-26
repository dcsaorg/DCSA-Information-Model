package org.dcsa.standards.specifications.standards.ovs.v300;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.ovs.v300.model.ServiceSchedule;
import org.junit.jupiter.api.Test;

class OVS300StandardSpecificationTest {
  @Test
  void testOVSStandardSpecification() {
    OVS3StandardSpecification ovsStandardSpecification = new OVS3StandardSpecification();
    ovsStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/ovs/v300/OVS_v3.0.0.yaml",
        ovsStandardSpecification);
  }
}
