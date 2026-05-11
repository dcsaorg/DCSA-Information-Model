package org.dcsa.standards.specifications.standards.ebl.v303;

import java.util.Set;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.standards.ebl.v302.Ebl302StandardSpecification;

public class Ebl303StandardSpecification extends Ebl302StandardSpecification {

  public Ebl303StandardSpecification(String moduleName, String modulePrefix) {
    super(moduleName, modulePrefix, "3.0.3");
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "eBL", "3.0.2", 2);
  }

  protected String getBaselineCsvFilePath(String sheetName) {
    return "./generated-resources/standards/ebl/v302/ebl-%s-v3.0.2-data-overview-%s.csv"
        .formatted(modulePrefix, sheetName);
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(super.modelClassesStream(), Set.of());
  }
}
