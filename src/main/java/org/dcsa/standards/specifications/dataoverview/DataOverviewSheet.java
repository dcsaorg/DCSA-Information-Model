package org.dcsa.standards.specifications.dataoverview;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTableType;

@Slf4j
public abstract class DataOverviewSheet {
  private final String sheetName;
  private final String tableName;
  private final List<String> csvHeaderTitles;
  private final List<String> excelHeaderTitles;
  private final List<Integer> excelColumnWidths;
  private final List<Boolean> excelWrapCellText;
  private final List<List<String>> csvDataValues;
  private final List<Map.Entry<DataOverviewDiffStatus, List<String>>> excelDataValues;

  protected DataOverviewSheet(
      String sheetName,
      String tableName,
      int primaryKeyColumnCount,
      List<String> headerTitles,
      List<Integer> columnWidths,
      List<Boolean> wrapCellText,
      List<List<String>> dataValues,
      Map<Class<? extends DataOverviewSheet>, List<List<String>>> oldDataValuesBySheetClass,
      Map<Class<? extends DataOverviewSheet>, Map<String, String>>
          changedPrimaryKeyByOldPrimaryKeyBySheetClass,
      boolean swapOldAndNew) {
    this.sheetName = sheetName;
    this.tableName = tableName;
    this.csvHeaderTitles = headerTitles;
    this.excelHeaderTitles = Stream.concat(Stream.of("Diff"), headerTitles.stream()).toList();
    this.excelColumnWidths = Stream.concat(Stream.of(12), columnWidths.stream()).toList();
    this.excelWrapCellText =
        Stream.concat(Stream.of(Boolean.FALSE), wrapCellText.stream()).toList();
    this.csvDataValues = dataValues;

    Map<String, List<String>> oldRowValuesByPrimaryKey =
        rowValuesByPrimaryKey(primaryKeyColumnCount, oldDataValuesBySheetClass.get(getClass()));
    Map<String, List<String>> newRowValuesByPrimaryKey =
        rowValuesByPrimaryKey(primaryKeyColumnCount, dataValues);

    excelDataValues =
        swapOldAndNew
            ? diff(
                primaryKeyColumnCount,
                newRowValuesByPrimaryKey,
                oldRowValuesByPrimaryKey,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass.get(getClass()).entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)))
            : diff(
                primaryKeyColumnCount,
                oldRowValuesByPrimaryKey,
                newRowValuesByPrimaryKey,
                changedPrimaryKeyByOldPrimaryKeyBySheetClass.get(getClass()));
  }

  @Getter
  @Accessors(chain = true)
  @Setter
  private static class DiffEntry {
    private String oldPrimaryKey;
    private String newPrimaryKey;
    private List<String> oldValues;
    private List<String> newValues;

    DiffEntry(
        String oldPrimaryKey,
        List<String> oldValues,
        Map<String, String> changedPrimaryKeyByOldPrimaryKey) {
      this.oldPrimaryKey = oldPrimaryKey;
      this.oldValues = oldValues;
      changedPrimaryKeyByOldPrimaryKey.keySet().stream()
          .filter(oldPrimaryKey::startsWith)
          .max(Comparator.comparing(String::length))
          .ifPresentOrElse(
              oldPrefix ->
                  this.newPrimaryKey =
                      "%s%s"
                          .formatted(
                              changedPrimaryKeyByOldPrimaryKey.get(oldPrefix),
                              oldPrimaryKey.substring(oldPrefix.length())),
              () -> this.newPrimaryKey = this.oldPrimaryKey);
    }

    DiffEntry(String newPrimaryKey, List<String> newValues) {
      this.newPrimaryKey = newPrimaryKey;
      this.newValues = newValues;
    }

    DataOverviewDiffStatus getDiffStatus(int primaryKeyColumnCount) {
      if (oldPrimaryKey == null) return DataOverviewDiffStatus.ADDED;
      if (newValues == null) return DataOverviewDiffStatus.REMOVED;
      if (newValues.equals(oldValues)) return DataOverviewDiffStatus.UNMODIFIED;
      if (getOldValuesThatChanged().stream().skip(primaryKeyColumnCount).allMatch(""::equals))
        return DataOverviewDiffStatus.UNMODIFIED;
      return DataOverviewDiffStatus.NEW_VALUE;
    }

    List<String> getOldValuesThatChanged() {
      AtomicInteger index = new AtomicInteger();
      return oldValues.stream()
          .map(
              oldValue ->
                  isValueChanged(oldValue, newValues.get(index.getAndIncrement())) ? "" : oldValue)
          .toList();
    }
  }

  private static boolean isValueChanged(String oldValue, String newValue) {
    return Objects.equals(oldValue, newValue)
        || Objects.equals("maxLength=%s".formatted(oldValue), newValue);
  }

  private static List<Map.Entry<DataOverviewDiffStatus, List<String>>> diff(
      int primaryKeyColumnCount,
      Map<String, List<String>> oldRowValuesByPrimaryKey,
      Map<String, List<String>> newRowValuesByPrimaryKey,
      Map<String, String> changedPrimaryKeyByOldPrimaryKey) {
    changedPrimaryKeyByOldPrimaryKey.keySet().stream()
        .filter(oldPrefix -> oldPrefix.endsWith(" /"))
        .toList()
        .forEach(
            oldPrefix ->
                changedPrimaryKeyByOldPrimaryKey.put(
                    oldPrefix.substring(0, oldPrefix.length() - 2),
                    changedPrimaryKeyByOldPrimaryKey
                        .get(oldPrefix)
                        .substring(
                            0, changedPrimaryKeyByOldPrimaryKey.get(oldPrefix).length() - 2)));

    SortedMap<String, DiffEntry> diffEntriesByNewPrimaryKey =
        new TreeMap<>(
            oldRowValuesByPrimaryKey.keySet().stream()
                .map(
                    oldPrimaryKey ->
                        new DiffEntry(
                            oldPrimaryKey,
                            oldRowValuesByPrimaryKey.get(oldPrimaryKey),
                            changedPrimaryKeyByOldPrimaryKey))
                .collect(Collectors.toMap(DiffEntry::getNewPrimaryKey, Function.identity())));

    newRowValuesByPrimaryKey
        .keySet()
        .forEach(
            newKey -> {
              DiffEntry diffEntry =
                  diffEntriesByNewPrimaryKey.computeIfAbsent(
                      newKey,
                      theNewKey ->
                          new DiffEntry(theNewKey, newRowValuesByPrimaryKey.get(theNewKey)));
              if (diffEntry.getOldPrimaryKey() != null) {
                diffEntry.setNewValues(newRowValuesByPrimaryKey.get(newKey));
              }
            });

    List<Map.Entry<DataOverviewDiffStatus, List<String>>> diffDataValues = new ArrayList<>();
    diffEntriesByNewPrimaryKey
        .values()
        .forEach(
            diffEntry -> {
              DataOverviewDiffStatus diffStatus = diffEntry.getDiffStatus(primaryKeyColumnCount);
              switch (diffStatus) {
                case ADDED:
                case UNMODIFIED:
                  diffDataValues.add(Map.entry(diffStatus, diffEntry.getNewValues()));
                  break;
                case REMOVED:
                  diffDataValues.add(Map.entry(diffStatus, diffEntry.getOldValues()));
                  break;
                default:
                  diffDataValues.add(
                      Map.entry(
                          DataOverviewDiffStatus.OLD_VALUE, diffEntry.getOldValuesThatChanged()));
                  diffDataValues.add(
                      Map.entry(DataOverviewDiffStatus.NEW_VALUE, diffEntry.getNewValues()));
                  break;
              }
            });
    return diffDataValues;
  }

  private static Map<String, List<String>> rowValuesByPrimaryKey(
      int primaryKeyColumnCount, List<List<String>> dataValues) {
    return dataValues.stream()
        .collect(
            Collectors.toMap(
                rowValues ->
                    primaryKeyColumnCount == 1
                        ? rowValues.getFirst()
                        : rowValues.stream()
                            .limit(primaryKeyColumnCount)
                            .collect(Collectors.joining(",")),
                Function.identity()));
  }

  public void addToExcelWorkbook(Workbook workbook, Supplier<Long> idSupplier) {
    Sheet sheet = workbook.createSheet(sheetName);

    Map<Boolean, Map<DataOverviewDiffStatus, XSSFCellStyle>> cellStylesByWrapAndDiffStatus =
        Stream.of(Boolean.FALSE, Boolean.TRUE)
            .collect(
                Collectors.toMap(
                    Function.identity(),
                    wrap ->
                        Arrays.stream(DataOverviewDiffStatus.values())
                            .collect(
                                Collectors.toMap(
                                    Function.identity(),
                                    diffStatus -> {
                                      XSSFCellStyle cellStyle =
                                          (XSSFCellStyle) workbook.createCellStyle();
                                      cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                                      if (wrap) {
                                        cellStyle.setWrapText(true);
                                      }
                                      var font = workbook.createFont();
                                      switch (diffStatus) {
                                        case ADDED:
                                          font.setColor(IndexedColors.GREEN.getIndex());
                                          break;
                                        case REMOVED:
                                          font.setColor(IndexedColors.RED.getIndex());
                                          font.setStrikeout(true);
                                          break;
                                        case OLD_VALUE:
                                          font.setColor(IndexedColors.LIGHT_BLUE.getIndex());
                                          font.setStrikeout(true);
                                          break;
                                        case NEW_VALUE:
                                          font.setColor(IndexedColors.BLUE.getIndex());
                                          break;
                                        case UNMODIFIED:
                                          break;
                                      }
                                      cellStyle.setFont(font);
                                      return cellStyle;
                                    }))));

    AtomicInteger rowIndexReference = new AtomicInteger(0);
    Stream.concat(
            Stream.of(Map.entry(DataOverviewDiffStatus.UNMODIFIED, excelHeaderTitles)),
            excelDataValues.stream())
        .forEach(
            diffStatusAndValues -> {
              DataOverviewDiffStatus diffStatus = diffStatusAndValues.getKey();
              List<String> rowValues = diffStatusAndValues.getValue();
              int rowIndex = rowIndexReference.getAndIncrement();
              Row row = sheet.createRow(rowIndex);
              row.setHeight((short) -1);
              AtomicInteger columnIndexReference = new AtomicInteger(0);
              Stream.concat(
                      rowIndex == 0 ? Stream.of() : Stream.of(diffStatus.getDisplayName()),
                      rowValues.stream())
                  .forEach(
                      value -> {
                        int columnIndex = columnIndexReference.getAndIncrement();
                        Cell cell = row.createCell(columnIndex);
                        cell.setCellValue(value);
                        if (rowIndex > 0) {
                          cell.setCellStyle(
                              cellStylesByWrapAndDiffStatus
                                  .get(excelWrapCellText.get(columnIndex))
                                  .get(diffStatus));
                        }
                      });
            });
    AtomicInteger columnIndexReference = new AtomicInteger(0);
    excelColumnWidths.forEach(
        width -> {
          int columnIndex = columnIndexReference.getAndIncrement();
          if (width > 0) {
            sheet.setColumnWidth(columnIndex, 256 * width);
          } else {
            sheet.autoSizeColumn(columnIndex);
          }
        });

    XSSFSheet xssfSheet = (XSSFSheet) sheet;
    XSSFTable table = xssfSheet.createTable(null);

    String tableRange =
        "A1:%s%d"
            .formatted(
                CellReference.convertNumToColString(excelHeaderTitles.size() - 1),
                1 + excelDataValues.size());

    CTTable ctTable = table.getCTTable();
    ctTable.setDisplayName(tableName); // does not support spaces
    ctTable.setName(tableName);
    ctTable.setId(idSupplier.get());
    ctTable.setTotalsRowShown(false);
    ctTable.setTotalsRowCount(0L);
    ctTable.setRef(tableRange);
    ctTable.setTableType(STTableType.WORKSHEET);

    CTTableColumns columns = ctTable.addNewTableColumns();
    columns.setCount(excelHeaderTitles.size());
    excelHeaderTitles.forEach(
        title -> {
          CTTableColumn tableColumn = columns.addNewTableColumn();
          tableColumn.setId(idSupplier.get());
          tableColumn.setName(title);
        });

    CTTableStyleInfo styleInfo = ctTable.addNewTableStyleInfo();
    styleInfo.setName("TableStyleMedium2");
    styleInfo.setShowColumnStripes(false);
    styleInfo.setShowRowStripes(true);

    CTAutoFilter autoFilter = ctTable.addNewAutoFilter();
    autoFilter.setRef(tableRange);
  }

  @SneakyThrows
  public void exportToCsvFile(String csvFilePattern) {
    CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
    csvHeaderTitles.forEach(csvSchemaBuilder::addColumn);
    CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
    CsvMapper csvMapper = new CsvMapper();
    List<Map<String, String>> csvRows =
        csvDataValues.stream()
            .map(
                dataRow ->
                    IntStream.range(0, csvHeaderTitles.size())
                        .boxed()
                        .collect(Collectors.toMap(csvHeaderTitles::get, dataRow::get)))
            .toList();
    ObjectWriter csvWriter = csvMapper.writer(csvSchema);
    StringWriter stringWriter = new StringWriter();
    csvWriter.writeValue(stringWriter, csvRows);
    String csvFilePath = csvFilePattern.formatted(sheetName.toLowerCase().replaceAll(" ", "-"));
    Files.writeString(Paths.get(csvFilePath), stringWriter.toString());
    log.info("Data overview {} exported to {}", sheetName.toLowerCase(), csvFilePath);
  }

  @SneakyThrows
  public static List<List<String>> importFromString(String csvContent) {
    try (MappingIterator<Map<String, String>> iterator =
        new CsvMapper()
            .readerFor(Map.class)
            .with(CsvSchema.builder().setUseHeader(true).build())
            .readValues(csvContent)) {
      return iterator.readAll().stream()
          .map(row -> new ArrayList<>(row.values()))
          .collect(Collectors.toList());
    }
  }
}
