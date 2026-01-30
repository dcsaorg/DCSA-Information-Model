package org.dcsa.standards.specifications.standards.ebl.v300;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument;

public class EblTd300StandardSpecification extends Ebl300StandardSpecification {

  public EblTd300StandardSpecification() {
    super("Transport Document", "td");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
