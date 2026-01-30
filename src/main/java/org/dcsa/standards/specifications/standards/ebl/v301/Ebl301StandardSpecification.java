package org.dcsa.standards.specifications.standards.ebl.v301;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.standards.ebl.v3.Ebl3StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v301.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model.Shipper;

public class Ebl301StandardSpecification extends Ebl3StandardSpecification {

  public Ebl301StandardSpecification(String moduleName, String modulePrefix) {
    super(moduleName, modulePrefix, "3.0.1");
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(IdentifyingCode.class, IssueToParty.class, NotifyParty.class, Shipper.class));
  }

  protected Ebl301StandardSpecification(
      String moduleName, String modulePrefix, String versionNumber) {
    super(moduleName, modulePrefix, versionNumber);
  }
}
