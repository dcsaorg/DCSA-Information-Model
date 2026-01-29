package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.ActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.RecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.RepresentedRecipientParty;

public class EBLEND302StandardSpecification extends EBL302StandardSpecification {

  public EBLEND302StandardSpecification() {
    super("Endorsement Chain", "end");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        ActorParty.class,
        EndorsementChain.class,
        EndorsementChainLink.class,
        IdentifyingCode.class,
        RecipientParty.class,
        RepresentedActorParty.class,
        RepresentedRecipientParty.class,
        TaxLegalReference.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(EndorsementChain.class.getSimpleName());
  }
}
