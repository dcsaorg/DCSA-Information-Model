package org.dcsa.standards.specifications.standards.an.v1;

import lombok.SneakyThrows;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.an.v100.AN100StandardSpecification;
import org.dcsa.standards.specifications.standards.an.v101.AN101StandardSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AN1StandardSpecificationTest {

  @SneakyThrows
  @Test
  void testAN1StandardSpecifications() {
    buildAndCheckV100();
    buildAndCheckV101();
  }

  void buildAndCheckV100() {
    AN100StandardSpecification anStandardSpecification = new AN100StandardSpecification();
    anStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/an/v100/an-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        "ArrivalNotice", yamlFilePath, anStandardSpecification);

    Assertions.assertEquals(
        "8EAA104134F85536B97C689C5D555C2684DE3A6FF7BE3AAE0EB502C143A1063A",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
  }

  void buildAndCheckV101() {
    AN101StandardSpecification anStandardSpecification = new AN101StandardSpecification();
    anStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/an/v101/an-v1.0.1-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        "ArrivalNotice", yamlFilePath, anStandardSpecification);

    Assertions.assertEquals(
        "773306D8358EC3705DC3BDE72046D80AE70147AC6A79E817CCF425F4C1494EB6",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
  }
}
