package org.dcsa.standards.specifications.standards.ebl.v302;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.ebl.v302.model.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.TransportDocument;
import org.junit.jupiter.api.Test;

@Slf4j
class EBL302StandardSpecificationTest {
  /** Checks the LLM-imported information model against the original OpenAPI specs. */
  @Test
  void testEBLStandardSpecification() {
    EBLSI302StandardSpecification eblSi302StandardSpecification =
        new EBLSI302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ShippingInstructions.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblSi302StandardSpecification);
    eblSi302StandardSpecification.generateArtifacts();

    EBLTD302StandardSpecification eblTd302StandardSpecification =
        new EBLTD302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        TransportDocument.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblTd302StandardSpecification);
    eblTd302StandardSpecification.generateArtifacts();

    EBLISS302StandardSpecification eblIss302StandardSpecification =
        new EBLISS302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        IssuanceRequest.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_ISS_v3.0.2.yaml",
        eblIss302StandardSpecification);
    eblIss302StandardSpecification.generateArtifacts();

    EBLEND302StandardSpecification eblEnd302StandardSpecification =
        new EBLEND302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        EndorsementChain.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_END_v3.0.2.yaml",
        eblEnd302StandardSpecification);
    eblEnd302StandardSpecification.generateArtifacts();
  }
}
