package org.dcsa.standards.specifications;

import org.dcsa.standards.specifications.standards.vgm.v100.VGMStandardSpecification;
import org.dcsa.standards.specifications.standards.vgm.v100.model.VGMDeclaration;
import org.junit.jupiter.api.Test;

class VGMStandardSpecificationTest {
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
