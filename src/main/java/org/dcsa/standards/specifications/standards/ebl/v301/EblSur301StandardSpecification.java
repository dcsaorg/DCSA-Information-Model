package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.dt.v100.model.TaxLegalReference;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedActorParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_end.RepresentedRecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v3.model_sur.EndorsementChainLink;
import org.dcsa.standards.specifications.standards.ebl.v3.model_sur.RecipientParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model_sur.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v301.model_sur.SurrenderRequestDetails;
import org.dcsa.standards.specifications.standards.ebl.v301.model_sur.TransactionParty;

public class EblSur301StandardSpecification extends Ebl301StandardSpecification {

  public EblSur301StandardSpecification() {
    super("Surrender", "sur");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return Stream.of(
        EndorsementChainLink.class,
        IdentifyingCode.class,
        RecipientParty.class,
        RepresentedActorParty.class,
        RepresentedRecipientParty.class,
        SurrenderRequestDetails.class,
        TaxLegalReference.class,
        TransactionParty.class);
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(SurrenderRequestDetails.class.getSimpleName());
  }
}
