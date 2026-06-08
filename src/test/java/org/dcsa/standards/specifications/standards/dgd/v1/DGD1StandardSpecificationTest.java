package org.dcsa.standards.specifications.standards.dgd.v1;

import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.dgd.v100.DGD100StandardSpecification;
import org.dcsa.standards.specifications.standards.dgd.v100.model.DangerousGoodsDeclaration;
import org.junit.jupiter.api.Test;

class DGD1StandardSpecificationTest {
  @Test
  void testDGDStandardSpecification() {
    buildAndCheckV100();
  }

  void buildAndCheckV100() {
    DGD100StandardSpecification dgdStandardSpecification = new DGD100StandardSpecification();
    dgdStandardSpecification.generateArtifacts();

    String yamlFilePath = "./generated-resources/standards/dgd/v100/dgd-v1.0.0-openapi.yaml";
    StandardSpecificationTestToolkit.verifyTypeExport(
        DangerousGoodsDeclaration.class.getSimpleName(), yamlFilePath, dgdStandardSpecification);
  }
}
