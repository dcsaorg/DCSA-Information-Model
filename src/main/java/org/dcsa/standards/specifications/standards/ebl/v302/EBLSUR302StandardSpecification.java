package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.stream.Stream;

import org.dcsa.standards.specifications.standards.ebl.v302.model.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.RepresentedRecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.SurrenderRequestDetails;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.ActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.RecipientParty;

public class EBLSUR302StandardSpecification extends EBL302StandardSpecification {

  public EBLSUR302StandardSpecification() {
    super("Surrender", "sur");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        ActorParty.class,
        EndorsementChainLink.class,
        IdentifyingCode.class,
        RecipientParty.class,
        RepresentedActorParty.class,
        RepresentedRecipientParty.class,
        SurrenderRequestDetails.class,
        TaxLegalReference.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(SurrenderRequestDetails.class.getSimpleName());
  }
}
