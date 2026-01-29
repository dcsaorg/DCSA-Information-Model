package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v302.model.ActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v302.model.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v302.model.RecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.RepresentedRecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.IdentifyingCode;

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
