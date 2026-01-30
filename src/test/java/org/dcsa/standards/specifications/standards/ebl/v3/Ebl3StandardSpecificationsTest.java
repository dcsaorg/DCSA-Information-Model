package org.dcsa.standards.specifications.standards.ebl.v3;

import lombok.extern.slf4j.Slf4j;
import org.dcsa.standards.specifications.standards.StandardSpecificationTestToolkit;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v3.model_sur.SurrenderRequestDetails;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v300.EblIss300StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v300.EblSi300StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v300.EblSur300StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v300.EblTd300StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.EblIss301StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.EblSi301StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.EblSur301StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.EblTd301StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v302.EblEnd302StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v302.EblIss302StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v302.EblSi302StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v302.EblSur302StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v302.EblTd302StandardSpecification;
import org.junit.jupiter.api.Test;

@Slf4j
class Ebl3StandardSpecificationsTest {

  @Test
  void testEbl3StandardSpecifications() {
    // in this order, for correct diff calculation
    buildAndCheckV300();
    buildAndCheckV301();
    buildAndCheckV302();
  }

  private static void buildAndCheckV300() {
    EblSi300StandardSpecification eblSi300StandardSpecification =
        new EblSi300StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ShippingInstructions.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v300/EBL_v3.0.0.yaml",
        eblSi300StandardSpecification);
    eblSi300StandardSpecification.generateArtifacts();

    EblTd300StandardSpecification eblTd300StandardSpecification =
        new EblTd300StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        TransportDocument.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v300/EBL_v3.0.0.yaml",
        eblTd300StandardSpecification);
    eblTd300StandardSpecification.generateArtifacts();

    EblIss300StandardSpecification eblIss300StandardSpecification =
        new EblIss300StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        IssuanceRequest.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v300/EBL_ISS_v3.0.0.yaml",
        eblIss300StandardSpecification);
    eblIss300StandardSpecification.generateArtifacts();

    EblSur300StandardSpecification eblSur300StandardSpecification =
        new EblSur300StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        SurrenderRequestDetails.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v300/EBL_SUR_v3.0.0.yaml",
        eblSur300StandardSpecification);
    eblSur300StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV301() {
    EblSi301StandardSpecification eblSi301StandardSpecification =
        new EblSi301StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ShippingInstructions.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v301/EBL_v3.0.1.yaml",
        eblSi301StandardSpecification);
    eblSi301StandardSpecification.generateArtifacts();

    EblTd301StandardSpecification eblTd301StandardSpecification =
        new EblTd301StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        TransportDocument.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v301/EBL_v3.0.1.yaml",
        eblTd301StandardSpecification);
    eblTd301StandardSpecification.generateArtifacts();

    EblIss301StandardSpecification eblIss301StandardSpecification =
        new EblIss301StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        IssuanceRequest.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v301/EBL_ISS_v3.0.1.yaml",
        eblIss301StandardSpecification);
    eblIss301StandardSpecification.generateArtifacts();

    EblSur301StandardSpecification eblSur301StandardSpecification =
        new EblSur301StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        SurrenderRequestDetails.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v301/EBL_SUR_v3.0.1.yaml",
        eblSur301StandardSpecification);
    eblSur301StandardSpecification.generateArtifacts();
  }

  private static void buildAndCheckV302() {
    EblSi302StandardSpecification eblSi302StandardSpecification =
        new EblSi302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        ShippingInstructions.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblSi302StandardSpecification);
    eblSi302StandardSpecification.generateArtifacts();

    EblTd302StandardSpecification eblTd302StandardSpecification =
        new EblTd302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        TransportDocument.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_v3.0.2.yaml",
        eblTd302StandardSpecification);
    eblTd302StandardSpecification.generateArtifacts();

    EblIss302StandardSpecification eblIss302StandardSpecification =
        new EblIss302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        IssuanceRequest.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_ISS_v3.0.2.yaml",
        eblIss302StandardSpecification);
    eblIss302StandardSpecification.generateArtifacts();

    EblEnd302StandardSpecification eblEnd302StandardSpecification =
        new EblEnd302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        EndorsementChain.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_END_v3.0.2.yaml",
        eblEnd302StandardSpecification);
    eblEnd302StandardSpecification.generateArtifacts();

    EblSur302StandardSpecification eblSur302StandardSpecification =
        new EblSur302StandardSpecification();
    StandardSpecificationTestToolkit.verifyTypeExport(
        SurrenderRequestDetails.class.getSimpleName(),
        "./src/main/resources/standards/ebl/v302/EBL_SUR_v3.0.2.yaml",
        eblSur302StandardSpecification);
    eblSur302StandardSpecification.generateArtifacts();
  }
}
