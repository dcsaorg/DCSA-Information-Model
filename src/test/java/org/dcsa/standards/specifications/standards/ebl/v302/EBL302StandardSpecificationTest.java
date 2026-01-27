package org.dcsa.standards.specifications.standards.ebl.v302;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Test;

@Slf4j
class EBL302StandardSpecificationTest {
  /**
   * Checks the LLM-imported information model against the original OpenAPI specs.
   */
  @Test
  void testEBLStandardSpecification() {
    EBLSI302StandardSpecification eblSi302StandardSpecification = new EBLSI302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        "ShippingInstructions",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblSi302StandardSpecification);
    eblSi302StandardSpecification.generateArtifacts();

    EBLTD302StandardSpecification eblTd302StandardSpecification = new EBLTD302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        "TransportDocument",
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblTd302StandardSpecification);
    eblTd302StandardSpecification.generateArtifacts();
  }
}
