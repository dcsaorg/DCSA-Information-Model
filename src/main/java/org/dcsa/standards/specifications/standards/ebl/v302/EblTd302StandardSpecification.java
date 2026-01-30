package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.City;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.DocumentParties;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.TransportDocument;

public class EblTd302StandardSpecification extends Ebl302StandardSpecification {

  public EblTd302StandardSpecification() {
    super("Transport Document", "td");
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
            City.class,
            DocumentParties.class,
            OtherDocumentParty.class,
            TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
