package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssuanceRequest;

public class EBLISS302StandardSpecification extends EBL302StandardSpecification {

  public EBLISS302StandardSpecification() {
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
                            org.dcsa.standards.specifications.standards.ebl.v302.model.Address
                                .class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model
                                .DangerousGoods.class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model
                                .DocumentParties.class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model
                                .IdentifyingCode.class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model.IssueToParty
                                .class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model.NotifyParty
                                .class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model.Party.class)
                        && !modelClass.equals(
                            org.dcsa.standards.specifications.standards.ebl.v302.model.Shipper
                                .class)),
        Stream.of(
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Address.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.DangerousGoods.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.DocumentParties.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IdentifyingCode.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IssueToParty.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.NotifyParty.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Party.class,
            org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Shipper.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(IssuanceRequest.class.getSimpleName());
  }
}
