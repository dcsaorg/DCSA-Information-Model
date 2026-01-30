package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.DangerousGoods;
import org.dcsa.standards.specifications.standards.ebl.v3.model_iss.Party;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Address;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IssuanceRequest;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.SupportingDocument;
import org.dcsa.standards.specifications.standards.ebl.v302.model_iss.TransportDocument;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.City;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.OtherDocumentParty;

public class EblIss302StandardSpecification extends Ebl302StandardSpecification {

  public EblIss302StandardSpecification() {
    super("Issuance", "iss");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.1
            Consignee.class,
            Endorsee.class,
            // 3.0.2
            Address.class,
            City.class,
            DangerousGoods.class,
            DocumentParties.class,
            IdentifyingCode.class,
            IssuanceRequest.class,
            IssueToParty.class,
            NotifyParty.class,
            OtherDocumentParty.class,
            Party.class,
            Shipper.class,
            SupportingDocument.class,
            TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(IssuanceRequest.class.getSimpleName());
  }
}
