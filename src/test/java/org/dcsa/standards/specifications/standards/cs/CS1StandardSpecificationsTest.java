package org.dcsa.standards.specifications.standards.cs;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.cs.v100.CsP2p100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.CsPs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.CsVs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.model.PointToPoint;
import org.dcsa.standards.specifications.standards.cs.v100.model.PortSchedule;
import org.dcsa.standards.specifications.standards.cs.v100.model.ServiceSchedule;
import org.junit.jupiter.api.Test;

@Slf4j
class CS1StandardSpecificationsTest {

  @Test
  void testCS1StandardSpecifications() {
    buildAndCheckV100();
  }

  private static void buildAndCheckV100() {
    CsP2p100StandardSpecification csP2p100StandardSpecification =
        new CsP2p100StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PointToPoint.class.getSimpleName(),
        "./src/main/resources/standards/cs/v100/CS_v1.0.0.yaml",
        csP2p100StandardSpecification);
    csP2p100StandardSpecification.generateArtifacts();

    CsPs100StandardSpecification csPs100StandardSpecification = new CsPs100StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PortSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v100/CS_v1.0.0.yaml",
        csPs100StandardSpecification);
    csPs100StandardSpecification.generateArtifacts();

    CsVs100StandardSpecification csVs100StandardSpecification = new CsVs100StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v100/CS_v1.0.0.yaml",
        csVs100StandardSpecification);
    csVs100StandardSpecification.generateArtifacts();
  }
}
