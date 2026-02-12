package org.dcsa.standards.specifications.standards.vgm.v1;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.vgm.v100.VGMStandardSpecification;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VGM1StandardSpecificationTest {
  @Test
  void testVGMStandardSpecification() {
    buildAndCheckV100();
    buildAndCheckV101();
  }

  void buildAndCheckV100() {
    VGMStandardSpecification vgmStandardSpecification = new VGMStandardSpecification();
    vgmStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/vgm/v100/vgm-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        VGMDeclaration.class.getSimpleName(), yamlFilePath, vgmStandardSpecification);

    Assertions.assertEquals(
        "D805AF293C04C5CBD9DDD3A264D10A0864020B633A130835A3EB0334473AE30B",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
  }

  void buildAndCheckV101() {
    org.dcsa.standards.specifications.standards.vgm.v101.VGMStandardSpecification
        vgmStandardSpecification =
            new org.dcsa.standards.specifications.standards.vgm.v101.VGMStandardSpecification();
    vgmStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/vgm/v101/vgm-v1.0.1-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        org.dcsa.standards.specifications.standards.vgm.v101.model.VGMDeclaration.class
            .getSimpleName(),
        yamlFilePath,
        vgmStandardSpecification);

    Assertions.assertEquals(
        "7E17D19D5B9761999166009693D2C088EC0E2BFC6CD96A007BC044E76981102E",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath));
  }
}
