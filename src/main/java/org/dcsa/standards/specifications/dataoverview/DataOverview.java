package org.dcsa.standards.specifications.dataoverview;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dcsa.standards.specifications.constraints.SchemaConstraint;

@Slf4j
public class DataOverview {
  private final LegendMetadata legendMetadata;
  private final AttributesHierarchicalSheet attributesHierarchicalSheet;
  private final AttributesNormalizedSheet attributesNormalizedSheet;
  private final QueryParametersSheet queryParametersSheet;
  private final QueryFiltersSheet queryFiltersSheet;

  public DataOverview(
      LegendMetadata legendMetadata,
      Map<String, Map<String, List<SchemaConstraint>>> constraintsByClassAndField,
      Map<String, Schema<?>> schemas,
      List<String> rootTypeNames,
      List<Parameter> queryParameters,
      Map<Boolean, List<List<Parameter>>> requiredAndOptionalFilters,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    this.legendMetadata = legendMetadata;
    AttributesData attributesData =
        new AttributesData(constraintsByClassAndField, schemas, rootTypeNames);
    attributesHierarchicalSheet =
        oldDataValuesBySheetClass.containsKey(AttributesHierarchicalSheet.class)
            ? new AttributesHierarchicalSheet(
                attributesData,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew)
            : null;
    attributesNormalizedSheet =
        oldDataValuesBySheetClass.containsKey(AttributesNormalizedSheet.class)
            ? new AttributesNormalizedSheet(
                attributesData,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew)
            : null;
    queryParametersSheet =
        oldDataValuesBySheetClass.containsKey(QueryParametersSheet.class)
            ? new QueryParametersSheet(
                queryParameters,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew)
            : null;
    queryFiltersSheet =
        oldDataValuesBySheetClass.containsKey(QueryFiltersSheet.class)
            ? new QueryFiltersSheet(
                requiredAndOptionalFilters,
                oldDataValuesBySheetClass,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass,
                swapOldAndNew)
            : null;
  }

  @SneakyThrows
  public void exportToExcelFile(String excelFilePath) {
    try (Workbook workbook = new XSSFWorkbook()) {
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
}
