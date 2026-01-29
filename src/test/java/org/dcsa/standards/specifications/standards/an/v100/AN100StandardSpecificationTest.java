package org.dcsa.standards.specifications.standards.an.v100;

import lombok.SneakyThrows;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AN100StandardSpecificationTest {
  @SneakyThrows
  @Test
  void testANStandardSpecification() {
    AN100StandardSpecification anStandardSpecification = new AN100StandardSpecification();
    anStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/an/v100/an-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        "ArrivalNotice", yamlFilePath, anStandardSpecification);

    Assertions.assertEquals(
        "8EAA104134F85536B97C689C5D555C2684DE3A6FF7BE3AAE0EB502C143A1063A",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
  }
}
