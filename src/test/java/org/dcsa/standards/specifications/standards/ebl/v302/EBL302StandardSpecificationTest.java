package org.dcsa.standards.specifications.standards.ebl.v302;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Test;

@Slf4j
class EBL302StandardSpecificationTest {
  @Test
  void testEBLStandardSpecification() {
    EBL302StandardSpecification eblStandardSpecification = new EBL302StandardSpecification();

    // check the LLM-imported information model against the original OpenAPI spec
    StandardSpecificationTestToolkit.verifyTypeExport(
        "TransportDocument",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblStandardSpecification);

    // export the eBL DO
    eblStandardSpecification.generateArtifacts();
  }
}
