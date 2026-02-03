package org.dcsa.standards.specifications.standards.booking.v200;

import org.dcsa.standards.specifications.dataoverview.AttributesHierarchicalSheet;
import org.dcsa.standards.specifications.dataoverview.AttributesNormalizedSheet;
import org.dcsa.standards.specifications.dataoverview.DataOverviewSheet;
import org.dcsa.standards.specifications.standards.booking.v2.Booking2StandardSpecification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Booking200StandardSpecification extends Booking2StandardSpecification {

  public Booking200StandardSpecification() {
    super("2.0.0", "");
  }

  @Override
  protected Map<Class<? extends DataOverviewSheet>, List<List<String>>>
  getOldDataValuesBySheetClass() {
    return Map.ofEntries(
        Map.entry(AttributesHierarchicalSheet.class, "attributes-hierarchical"),
        Map.entry(AttributesNormalizedSheet.class, "attributes-normalized"))
      .entrySet()
      .stream()
      .collect(Collectors.toMap(Map.Entry::getKey, _ -> List.of()));
  }
}
