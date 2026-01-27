package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TransportDocument;

public class EBLTD302StandardSpecification extends EBL302StandardSpecification {

  public EBLTD302StandardSpecification() {
    super("TransportDocument", "td");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
