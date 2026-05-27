package org.dcsa.standards.specifications.standards.ovs;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.ovs.v300.OVS3StandardSpecification;
import org.dcsa.standards.specifications.standards.ovs.v300.model.ServiceSchedule;
import org.dcsa.standards.specifications.standards.ovs.v301.OVS301StandardSpecification;
import org.dcsa.standards.specifications.standards.ovs.v302.OVS302StandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class OVS3StandardSpecificationsTest {

  @Test
  void testOVS3StandardSpecifications() {
    // in this order, for correct diff calculation
    buildAndCheckV300();
    buildAndCheckV301();
    buildAndCheckV302();
  }

  private static void buildAndCheckV300() {
    OVS3StandardSpecification ovs300StandardSpecification = new OVS3StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/ovs/v300/OVS_v3.0.0.yaml",
        ovs300StandardSpecification);
    ovs300StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV301() {
    OVS301StandardSpecification ovs301StandardSpecification = new OVS301StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/ovs/v301/OVS_v3.0.1.yaml",
        ovs301StandardSpecification);
    ovs301StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV302() {
    OVS302StandardSpecification ovs302StandardSpecification = new OVS302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/ovs/v302/OVS_v3.0.2.yaml",
        ovs302StandardSpecification);
    ovs302StandardSpecification.generateArtifacts();
  }
}
