package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.Buyer;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.BuyerHBL;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.ConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.DocumentPartiesShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.EndorseeShipper;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.Seller;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.SellerHBL;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.ShippingInstructions;

public class EblSi301StandardSpecification extends Ebl301StandardSpecification {

  public EblSi301StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            Buyer.class,
            BuyerHBL.class,
            ConsigneeShipper.class,
            DocumentPartiesShippingInstructions.class,
            EndorseeShipper.class,
            Seller.class,
            SellerHBL.class,
            ShippingInstructions.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(ShippingInstructions.class.getSimpleName());
  }
}
