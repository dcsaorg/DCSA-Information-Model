package org.dcsa.standards.specifications.standards.cs;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.cs.v100.CsP2p100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.CsPs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.CsVs100StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v100.model.PointToPoint;
import org.dcsa.standards.specifications.standards.cs.v100.model.PortSchedule;
import org.dcsa.standards.specifications.standards.cs.v100.model.ServiceSchedule;
import org.dcsa.standards.specifications.standards.cs.v101.CsP2p101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v101.CsPs101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v101.CsVs101StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.CsP2p102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.CsPs102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v102.CsVs102StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.CsP2p103StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.CsPs103StandardSpecification;
import org.dcsa.standards.specifications.standards.cs.v103.CsVs103StandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class CS1StandardSpecificationsTest {

  @Test
  void testCS1StandardSpecifications() {
    buildAndCheckV100();
    buildAndCheckV101();
    buildAndCheckV102();
    buildAndCheckV103();
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

  private static void buildAndCheckV101() {
    CsP2p101StandardSpecification csP2p101StandardSpecification =
        new CsP2p101StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PointToPoint.class.getSimpleName(),
        "./src/main/resources/standards/cs/v101/CS_v1.0.1.yaml",
        csP2p101StandardSpecification);
    csP2p101StandardSpecification.generateArtifacts();

    CsPs101StandardSpecification csPs101StandardSpecification = new CsPs101StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PortSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v101/CS_v1.0.1.yaml",
        csPs101StandardSpecification);
    csPs101StandardSpecification.generateArtifacts();

    CsVs101StandardSpecification csVs101StandardSpecification = new CsVs101StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v101/CS_v1.0.1.yaml",
        csVs101StandardSpecification);
    csVs101StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV102() {
    CsP2p102StandardSpecification csP2p102StandardSpecification =
        new CsP2p102StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PointToPoint.class.getSimpleName(),
        "./src/main/resources/standards/cs/v102/CS_v1.0.2.yaml",
        csP2p102StandardSpecification);
    csP2p102StandardSpecification.generateArtifacts();

    CsPs102StandardSpecification csPs102StandardSpecification = new CsPs102StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PortSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v102/CS_v1.0.2.yaml",
        csPs102StandardSpecification);
    csPs102StandardSpecification.generateArtifacts();

    CsVs102StandardSpecification csVs102StandardSpecification = new CsVs102StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v102/CS_v1.0.2.yaml",
        csVs102StandardSpecification);
    csVs102StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV103() {
    CsP2p103StandardSpecification csP2p103StandardSpecification =
        new CsP2p103StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PointToPoint.class.getSimpleName(),
        "./src/main/resources/standards/cs/v103/CS_v1.0.3.yaml",
        csP2p103StandardSpecification);
    csP2p103StandardSpecification.generateArtifacts();

    CsPs103StandardSpecification csPs103StandardSpecification = new CsPs103StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        PortSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v103/CS_v1.0.3.yaml",
        csPs103StandardSpecification);
    csPs103StandardSpecification.generateArtifacts();

    CsVs103StandardSpecification csVs103StandardSpecification = new CsVs103StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ServiceSchedule.class.getSimpleName(),
        "./src/main/resources/standards/cs/v103/CS_v1.0.3.yaml",
        csVs103StandardSpecification);
    csVs103StandardSpecification.generateArtifacts();
  }
}
