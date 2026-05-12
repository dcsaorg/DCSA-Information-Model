package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedRecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model_sur.TransactionParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v303.model_end.EndorsementChain;
import org.dcsa.standards.specifications.standards.ebl.v303.model_sur.ActorParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_sur.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v303.model_sur.RecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v303.model_sur.SurrenderRequestDetails;

public class EblSur303StandardSpecification extends Ebl303StandardSpecification {

  public EblSur303StandardSpecification() {
    super("Surrender", "sur");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        // 3.0.1
        TransactionParty.class,
        // 3.0.2
        EndorsementChainLink.class,
        RepresentedActorParty.class,
        RepresentedRecipientParty.class,
        TaxLegalReference.class,
        // 3.0.3
        ActorParty.class,
        EndorsementChain.class,
        IdentifyingCode.class,
        RecipientParty.class,
        SurrenderRequestDetails.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(SurrenderRequestDetails.class.getSimpleName());
  }
}
