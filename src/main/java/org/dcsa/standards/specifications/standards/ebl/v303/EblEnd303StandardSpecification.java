package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedRecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model_end.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v303.model_end.ActorParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_end.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v303.model_end.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model_end.RecipientParty;

public class EblEnd303StandardSpecification extends Ebl303StandardSpecification {

  public EblEnd303StandardSpecification() {
    super("Endorsement Chain", "end");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        // 3.0.2
        EndorsementChainLink.class,
        RepresentedActorParty.class,
        RepresentedRecipientParty.class,
        TaxLegalReference.class,
        // 3.0.3
        ActorParty.class,
        EndorsementChain.class,
        IdentifyingCode.class,
        RecipientParty.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(EndorsementChain.class.getSimpleName());
  }
}
