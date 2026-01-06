package org.dcsa.standards.specifications.standards.vgm.v101;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.vgm.v101.model.VGMDeclaration;
import org.junit.jupiter.api.Test;

class VGM101StandardSpecificationTest {
  @Test
  void testVGMStandardSpecification() {
    VGMStandardSpecification vgmStandardSpecification = new VGMStandardSpecification();
    vgmStandardSpecification.generateArtifacts();

    StandardSpecificationTestToolkit.verifyTypeExport(
        VGMDeclaration.class.getSimpleName(),
        "./generated-resources/standards/vgm/v101/vgm-v1.0.1-openapi.yaml",
        vgmStandardSpecification);
  }
}
