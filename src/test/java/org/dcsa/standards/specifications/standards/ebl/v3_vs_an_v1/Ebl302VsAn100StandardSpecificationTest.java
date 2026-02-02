package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.ebl.v302.Ebl302VsAn100StandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class Ebl302VsAn100StandardSpecificationTest {
  @Test
  void testEBLStandardSpecification() {
    Ebl302VsAn100StandardSpecification eblVsAnStandardSpecification = new Ebl302VsAn100StandardSpecification();

    // check the LLM-imported information model against the original OpenAPI spec
    StandardSpecificationTestToolkit.verifyTypeExport(
        "TransportDocument",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblVsAnStandardSpecification);

    // export the AN-eBL mapping DO
    eblVsAnStandardSpecification.generateArtifacts();
  }
}
