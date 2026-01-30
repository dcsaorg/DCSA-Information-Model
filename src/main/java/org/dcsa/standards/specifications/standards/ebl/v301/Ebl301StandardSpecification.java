package org.dcsa.standards.specifications.standards.ebl.v301;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.dataoverview.LegendMetadata;
import org.dcsa.standards.specifications.generator.SpecificationToolkit;
import org.dcsa.standards.specifications.standards.ebl.v300.Ebl300StandardSpecification;
import org.dcsa.standards.specifications.standards.ebl.v301.model.IdentifyingCode;
import org.dcsa.standards.specifications.standards.ebl.v301.model.IssueToParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model.NotifyParty;
import org.dcsa.standards.specifications.standards.ebl.v301.model.Shipper;

public class Ebl301StandardSpecification extends Ebl300StandardSpecification {

  public Ebl301StandardSpecification(String moduleName, String modulePrefix) {
    super(moduleName, modulePrefix, "3.0.1");
  }

  protected Ebl301StandardSpecification(
      String moduleName, String modulePrefix, String versionNumber) {
    super(moduleName, modulePrefix, versionNumber);
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "eBL", "3.0.0", 2);
  }

  protected String getBaselineCsvFilePath(String sheetName) {
    return "./generated-resources/standards/ebl/v300/ebl-%s-v3.0.0-data-overview-%s.csv"
        .formatted(modulePrefix, sheetName);
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
      getOldDataValuesBySheetClass() {
    // there's typically more than one entry in this map
    return Map.ofEntries(
            Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
            Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"))
        .entrySet()
        .stream()
        .filter(entry -> Files.isRegularFile(Path.of(getBaselineCsvFilePath(entry.getValue()))))
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry ->
                    DataOverviewSheet.importFromString(
                            SpecificationToolkit.readLocalFile(getBaselineCsvFilePath(entry.getValue())))
                        .stream()
                        .toList()));
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(),
        Set.of(IdentifyingCode.class, IssueToParty.class, NotifyParty.class, Shipper.class));
  }
}
