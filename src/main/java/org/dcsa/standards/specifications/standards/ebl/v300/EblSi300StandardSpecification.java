package org.dcsa.standards.specifications.standards.ebl.v300;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions;

public class EblSi300StandardSpecification extends Ebl300StandardSpecification {

  public EblSi300StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(
        ShippingInstructions.class.getSimpleName());
  }
}
