package org.dcsa.standards.specifications.standards.vgm.v100;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VGM100StandardSpecificationTest {
  @Test
  void testVGMStandardSpecification() {
    VGMStandardSpecification vgmStandardSpecification = new VGMStandardSpecification();
    vgmStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/vgm/v100/vgm-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        VGMDeclaration.class.getSimpleName(), yamlFilePath, vgmStandardSpecification);

    Assertions.assertEquals(
        "D805AF293C04C5CBD9DDD3A264D10A0864020B633A130835A3EB0334473AE30B",
        StandardSpecificationTestToolkit.getFileHash(yamlFilePath).toUpperCase());
  }
}
