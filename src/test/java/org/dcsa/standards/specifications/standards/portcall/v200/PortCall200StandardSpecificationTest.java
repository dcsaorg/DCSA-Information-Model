package org.dcsa.standards.specifications.standards.portcall.v200;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.portcall.v200.model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PortCall200StandardSpecificationTest {
  @Test
  void testPortCallStandardSpecification() {
    PortCallStandardSpecification portCallStandardSpecificationStandardSpecification =
        new PortCallStandardSpecification();
    portCallStandardSpecificationStandardSpecification.generateArtifacts();

    String yamlFilePath =
        "./generated-resources/standards/portcall/v200/port-call-v2.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        Event.class.getSimpleName(),
        yamlFilePath,
        portCallStandardSpecificationStandardSpecification);

    Assertions.assertEquals(
        "FCC6877230D6F99CA26DDF82FF2004D4C88B44D644C82C86843234267BF00BCD",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath).toUpperCase());
  }
}
