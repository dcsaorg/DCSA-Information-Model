package org.dcsa.standards.specifications.standards.ebl.v302;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Test;

@Slf4j
class EBL302StandardSpecificationTest {
  @Test
  void testEBLStandardSpecification() {
    EBL302StandardSpecification eblStandardSpecification = new EBL302StandardSpecification();

    // check the LLM-imported information model against the original OpenAPI specs
    StandardSpecificationTestToolkit.verifyTypeExport(
        "ShippingInstructions",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblStandardSpecification);
    StandardSpecificationTestToolkit.verifyTypeExport(
        "TransportDocument",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblStandardSpecification);
//    StandardSpecificationTestToolkit.verifyTypeExport(
//        "IssuanceRequest",
//        "./src/main/resources/standards/ebl/v302/EBL_ISS_v3.0.2.yaml",
//        eblStandardSpecification);

    // export the eBL DO
    eblStandardSpecification.generateArtifacts();
  }
}
