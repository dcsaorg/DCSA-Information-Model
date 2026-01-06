package org.dcsa.standards.specifications.standards.an.v100;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Test;

class AN100StandardSpecificationTest {
  @Test
  void testANStandardSpecification() {
    ANStandardSpecification anStandardSpecification = new ANStandardSpecification();
    anStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
      "ArrivalNotice",
      "./generated-resources/standards/an/v100/an-v1.0.0-openapi.yaml",
      anStandardSpecification);
  }
}
