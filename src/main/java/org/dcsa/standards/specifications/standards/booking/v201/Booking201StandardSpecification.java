package org.dcsa.standards.specifications.standards.booking.v201;

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
import org.dcsa.standards.specifications.standards.booking.v2.Booking2StandardSpecification;
import org.dcsa.standards.specifications.standards.booking.v201.model.Booking;
import org.dcsa.standards.specifications.standards.booking.v201.model.OtherDocumentParty;

public class Booking201StandardSpecification extends Booking2StandardSpecification {

  public Booking201StandardSpecification() {
    super("2.0.1");
  }

  @Override
  protected LegendMetadata getLegendMetadata() {
    return new LegendMetadata(standardName, standardVersion, "Booking", "2.0.0", 2);
  }

  protected String getBaselineCsvFilePath(String sheetName) {
    return "./generated-resources/standards/booking/v200/booking-v2.0.0-data-overview-%s.csv"
        .formatted(sheetName);
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
                            SpecificationToolkit.readLocalFile(
                                getBaselineCsvFilePath(entry.getValue())))
                        .stream()
                        .toList()));
  }

  @Override
  protected Stream<Class<?>> modelClassesStream() {
    return modelClassesStreamWithReplacementClasses(
        super.modelClassesStream(), Set.of(Booking.class, OtherDocumentParty.class));
  }
}
