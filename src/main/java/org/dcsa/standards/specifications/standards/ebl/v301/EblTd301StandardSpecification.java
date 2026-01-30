package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Consignee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.Endorsee;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.OtherDocumentParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model_td.TransportDocument;

public class EblTd301StandardSpecification extends Ebl301StandardSpecification {

  public EblTd301StandardSpecification() {
    super("Transport Document", "td");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            Consignee.class,
            Endorsee.class,
            OtherDocumentParty.class,
            TransportDocument.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
