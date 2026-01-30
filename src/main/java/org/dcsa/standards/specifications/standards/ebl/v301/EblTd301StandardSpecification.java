package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v3.model_td.TransportDocument;

public class EblTd301StandardSpecification extends Ebl301StandardSpecification {

  public EblTd301StandardSpecification() {
    super("Transport Document", "td");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(TransportDocument.class.getSimpleName());
  }
}
