package org.dcsa.standards.specifications.standards.dx.v1;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.dx.v100.DX100StandardSpecification;
import org.dcsa.standards.specifications.standards.dx.v100.model.Document;
import org.junit.jupiter.api.Test;

class DX1StandardSpecificationTest {
  @Test
  void testDXStandardSpecification() {
    buildAndCheckV100();
  }

  void buildAndCheckV100() {
    DX100StandardSpecification dxStandardSpecification = new DX100StandardSpecification();
    dxStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/dx/v100/dx-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        Document.class.getSimpleName(), yamlFilePath, dxStandardSpecification);
  }
}
