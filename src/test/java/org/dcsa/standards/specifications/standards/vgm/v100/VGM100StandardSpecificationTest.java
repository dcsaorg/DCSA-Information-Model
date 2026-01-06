package org.dcsa.standards.specifications.standards.vgm.v100;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;
import org.junit.jupiter.api.Test;

class VGM100StandardSpecificationTest {
  @Test
  void testVGMStandardSpecification() {
    VGMStandardSpecification vgmStandardSpecification = new VGMStandardSpecification();
    vgmStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
        VGMDeclaration.class.getSimpleName(),
        "./generated-resources/standards/vgm/v100/vgm-v1.0.0-openapi.yaml",
        vgmStandardSpecification);
  }
}
