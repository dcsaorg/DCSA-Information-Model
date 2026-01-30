package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import org.dcsa.standards.specifications.standards.ebl.v3.model_si.ShippingInstructions;

public class EblSi301StandardSpecification extends Ebl301StandardSpecification {

  public EblSi301StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(
        ShippingInstructions.class.getSimpleName());
  }
}
