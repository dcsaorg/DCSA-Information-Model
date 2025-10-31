package org.dcsa.standards.specifications;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.ebl.v300.EBLStandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class EBLStandardSpecificationTest {
  @Test
  void testEBLStandardSpecification() {
    EBLStandardSpecification eblStandardSpecification = new EBLStandardSpecification();

    StandardSpecificationTestToolkit.verifyTypeExport(
        "TransportDocument",
        "./src/main/resources/standards/ebl/v300/EBL_v3.0.0.yaml",
        eblStandardSpecification);

    eblStandardSpecification.generateArtifacts();
  }
}
