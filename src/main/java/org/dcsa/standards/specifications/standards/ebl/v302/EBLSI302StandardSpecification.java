package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.ShippingInstructions;

public class EBLSI302StandardSpecification extends EBL302StandardSpecification {

  public EBLSI302StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(
        ShippingInstructions.class.getSimpleName());
  }
}
