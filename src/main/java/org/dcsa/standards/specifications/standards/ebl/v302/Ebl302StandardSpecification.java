package org.dcsa.standards.specifications.standards.ebl.v302;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v301.Ebl301StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.model.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model.Shipper;
import org.dcsa.standards.specifications.standards.ebl.v302.model.Address;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CustomsReference;
import org.dcsa.standards.specifications.standards.ebl.v302.model.GeoCoordinate;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PartyAddress;
import org.dcsa.standards.specifications.standards.ebl.v302.model.PlaceOfIssue;
import org.dcsa.standards.specifications.standards.ebl.v302.model.TaxLegalReference;

public class Ebl302StandardSpecification extends Ebl301StandardSpecification {

  public Ebl302StandardSpecification(String moduleName, String modulePrefix) {
    super(moduleName, modulePrefix, "3.0.2");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(
            // 3.0.1
            NotifyParty.class,
            Shipper.class,
            // 3.0.2
            Address.class,
            CustomsReference.class,
            GeoCoordinate.class,
            IdentifyingCode.class,
            IssueToParty.class,
            NationalCommodityCode.class,
            PartyAddress.class,
            PlaceOfIssue.class,
            TaxLegalReference.class));
  }
}
