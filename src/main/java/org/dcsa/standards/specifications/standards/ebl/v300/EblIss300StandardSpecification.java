package org.dcsa.standards.specifications.standards.ebl.v300;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v3.model.*;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.OtherDocumentParty;

public class EblIss300StandardSpecification extends Ebl300StandardSpecification {

  public EblIss300StandardSpecification() {
    super("Issuance", "iss");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    // replace the eBL IssueToParty with the identically named but different eBL ISS IssueToParty
    return Stream.concat(
        super.modelClassesStream()
            .filter(
                modelClass ->
                    !modelClass.equals(
                            org.dcsa.standards.specifications.standards.dt.v100.model.Address.class)
                        && !modelClass.equals(DangerousGoods.class)
                        && !modelClass.equals(DocumentParties.class)
                        && !modelClass.equals(IdentifyingCode.class)
                        && !modelClass.equals(IssueToParty.class)
                        && !modelClass.equals(NotifyParty.class)
                        && !modelClass.equals(OtherDocumentParty.class)
                        && !modelClass.equals(Party.class)
                        && !modelClass.equals(Shipper.class)),
        Stream.of(
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Address.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.DangerousGoods.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.DocumentParties.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IdentifyingCode.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssueToParty.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.NotifyParty.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.OtherDocumentParty.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Party.class,
            org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Shipper.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(IssuanceRequest.class.getSimpleName());
  }
}
