package org.dcsa.standards.specifications.dataoverview;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class DataOverview {
  private final LegendMetadata legendMetadata;
  private final AttributesHierarchicalSheet attributesHierarchicalSheet;
  private final AttributesNormalizedSheet attributesNormalizedSheet;
  private final QueryParametersSheet queryParametersSheet;
  private final QueryFiltersSheet queryFiltersSheet;

  public DataOverview(
      LegendMetadata legendMetadata,
      Map<String, Schema<?>> schemas,
      List<String> rootTypeNames,
      List<Parameter> queryParameters,
      Map<Boolean, List<List<Parameter>>> requiredAndOptionalFilters,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    this.legendMetadata = legendMetadata;
    boolean hasBaseline = legendMetadata.hasBaseline();
    AttributesData attributesData =
        new AttributesData(schemas, rootTypeNames);
    attributesHierarchicalSheet =
        oldDataValuesBySheetClass.containsKey(AttributesHierarchicalSheet.class)
            ? new AttributesHierarchicalSheet(
                attributesData,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew,
                hasBaseline)
            : null;
    attributesNormalizedSheet =
        oldDataValuesBySheetClass.containsKey(AttributesNormalizedSheet.class)
            ? new AttributesNormalizedSheet(
                attributesData,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew,
                hasBaseline)
            : null;
    queryParametersSheet =
        oldDataValuesBySheetClass.containsKey(QueryParametersSheet.class)
            ? new QueryParametersSheet(
                queryParameters,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew,
                hasBaseline)
            : null;
    queryFiltersSheet =
        oldDataValuesBySheetClass.containsKey(QueryFiltersSheet.class)
            ? new QueryFiltersSheet(
                requiredAndOptionalFilters,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew,
                hasBaseline)
            : null;
  }

  @SneakyThrows
  public void exportToExcelFile(String excelFilePath) {
    try (XSSFWorkbook workbook = new XSSFWorkbook()) {
      applySensitivityLabel(workbook);
      new LegendSheet(workbook, legendMetadata).addToWorkbook();
      AtomicLong nextId = new AtomicLong(0);
      Stream.of(
              attributesHierarchicalSheet,
              attributesNormalizedSheet,
              queryParametersSheet,
              queryFiltersSheet)
          .filter(Objects::nonNull)
          .forEach(sheet -> sheet.addToExcelWorkbook(workbook, nextId::incrementAndGet));
      try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
        workbook.write(fileOut);
      }
    }
    log.info("Data Overview exported to {}", excelFilePath);
  }

  public void exportToCsvFiles(String csvFilePattern) {
    Stream.of(
            attributesHierarchicalSheet,
            attributesNormalizedSheet,
            queryParametersSheet,
            queryFiltersSheet)
        .filter(Objects::nonNull)
        .forEach(sheet -> sheet.exportToCsvFile(csvFilePattern));
  }

  private static void applySensitivityLabel(XSSFWorkbook wb) {
    String labelId = "318d35dc-279b-461b-82fb-b370dac77106";
    String prefix = "MSIP_Label_" + labelId + "_";

    POIXMLProperties.CustomProperties custom = wb.getProperties().getCustomProperties();

    custom.addProperty(prefix + "Enabled", "true");
    custom.addProperty(prefix + "SetDate", Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
    custom.addProperty(prefix + "Method", "Privileged");
    custom.addProperty(prefix + "Name", "Project");
    custom.addProperty(prefix + "SiteId", "e5d7661a-17a9-4d48-a5fe-302735ec282c");
    custom.addProperty(prefix + "ActionId", "973f9577-29d6-456c-8c9b-43c06aab57e3");
    custom.addProperty(prefix + "ContentBits", "0");
    custom.addProperty(prefix + "Tag", "10, 0, 1, 1");
  }
}
