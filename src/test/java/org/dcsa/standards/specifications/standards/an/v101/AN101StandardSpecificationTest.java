package org.dcsa.standards.specifications.standards.an.v101;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Test;

class AN101StandardSpecificationTest {
  @Test
  void testANStandardSpecification() {
    AN101StandardSpecification anStandardSpecification = new AN101StandardSpecification();
    anStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
      "ArrivalNotice",
      "./generated-resources/standards/an/v101/an-v1.0.1-openapi.yaml",
      anStandardSpecification);
  }
}
