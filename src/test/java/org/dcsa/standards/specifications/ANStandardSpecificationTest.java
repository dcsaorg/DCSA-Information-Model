package org.dcsa.standards.specifications;

import org.dcsa.standards.specifications.standards.an.v100.ANStandardSpecification;
import org.junit.jupiter.api.Test;

class ANStandardSpecificationTest {
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
