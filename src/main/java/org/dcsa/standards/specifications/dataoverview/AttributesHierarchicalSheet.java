package org.dcsa.standards.specifications.dataoverview;

import java.util.List;
import java.util.Map;

public class AttributesHierarchicalSheet extends DataOverviewSheet {
  protected AttributesHierarchicalSheet(
      AttributesData attributesData,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    super(
        "Attributes hierarchical",
        "AttributesHierarchicalTable",
        1,
        List.of(
            "Path",
            "Attribute",
            "Type",
            "Required",
            "Size",
            "Pattern",
            "Example",
            "Description",
            "Constraints"),
        List.of(107, 33, 22, 11, 20, 17, 32, 96, 96),
        List.of(false, false, false, false, true, false, true, true, true),
        attributesData.getHierarchicalRows(),
        oldDataValuesBySheetClass,
        changedPrimaryKeyByOldPrimaryKeyBySheetClass,
        swapOldAndNew);
  }
}
