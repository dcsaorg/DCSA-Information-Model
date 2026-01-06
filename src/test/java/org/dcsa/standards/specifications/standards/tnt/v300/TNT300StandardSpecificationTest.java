package org.dcsa.standards.specifications.standards.tnt.v300;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.tnt.v300.model.Event;
import org.junit.jupiter.api.Test;

class TNT300StandardSpecificationTest {
  @Test
  void testTNTStandardSpecification() {
    TNTStandardSpecification tntStandardSpecification = new TNTStandardSpecification();
    tntStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
        Event.class.getSimpleName(),
        "./generated-resources/standards/tnt/v300/tnt-v3.0.0-openapi.yaml",
        tntStandardSpecification);
  }
}
