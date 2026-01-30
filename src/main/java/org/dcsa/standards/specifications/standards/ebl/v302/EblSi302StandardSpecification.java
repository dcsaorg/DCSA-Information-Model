package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.ConsigneeShipper;
import org.dcsa.standards.specifications.standards.ebl.v301.model_si.EndorseeShipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.AdvanceManifestFiling;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.DocumentPartiesShippingInstructions;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.PlaceOfAcceptance;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.PlaceOfFinalDelivery;
import org.dcsa.standards.specifications.standards.ebl.v302.model_si.ShippingInstructions;

public class EblSi302StandardSpecification extends Ebl302StandardSpecification {

  public EblSi302StandardSpecification() {
    super("Shipping Instructions", "si");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.1
            ConsigneeShipper.class,
            EndorseeShipper.class,
            // 3.0.2
            AdvanceManifestFiling.class,
            DocumentPartiesShippingInstructions.class,
            PlaceOfAcceptance.class,
            PlaceOfFinalDelivery.class,
            ShippingInstructions.class));
  }

  @Override
  protected List<String> getRootTypeNames() {
    return List.of(ShippingInstructions.class.getSimpleName());
  }
}
