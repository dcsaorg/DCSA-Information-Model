package org.dcsa.standards.specifications.standards.portcall.v200;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.portcall.v200.model.Event;
import org.junit.jupiter.api.Test;

class PortCall200StandardSpecificationTest {
  @Test
  void testPortCallStandardSpecification() {
    PortCallStandardSpecification portCallStandardSpecificationStandardSpecification = new PortCallStandardSpecification();
    portCallStandardSpecificationStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
        Event.class.getSimpleName(),
        "./generated-resources/standards/portcall/v200/port-call-v2.0.0-openapi.yaml",
        portCallStandardSpecificationStandardSpecification);
  }
}
