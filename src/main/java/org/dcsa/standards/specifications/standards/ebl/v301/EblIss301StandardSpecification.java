package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Party;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.Address;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v301.model_iss.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.OtherDocumentParty;

public class EblIss301StandardSpecification extends Ebl301StandardSpecification {

  public EblIss301StandardSpecification() {
    super("Issuance", "iss");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            Address.class,
            Consignee.class,
            DangerousGoods.class,
            DocumentParties.class,
            Endorsee.class,
            IdentifyingCode.class,
            IssueToParty.class,
            NotifyParty.class,
            OtherDocumentParty.class,
            Party.class,
            Shipper.class,
            TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(IssuanceRequest.class.getSimpleName());
  }
}
