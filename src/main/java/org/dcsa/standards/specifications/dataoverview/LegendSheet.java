package org.dcsa.standards.specifications.dataoverview;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class LegendSheet {

  private record RowInfo(
      XSSFCellStyle keyStyle, String key, XSSFCellStyle valueStyle, String value) {}

  private final Workbook workbook;
  private final LegendMetadata legendMetadata;

  private final XSSFCellStyle cellStyleHeaderBold;
  private final XSSFCellStyle cellStyleHeaderWrapped;

  private final XSSFCellStyle cellStyleBold;
  private final XSSFCellStyle cellStyleWrapped;

  private final XSSFCellStyle cellStyleAdded;
  private final XSSFCellStyle cellStyleRemoved;
  private final XSSFCellStyle cellStyleNewValue;
  private final XSSFCellStyle cellStyleOldValue;

  public LegendSheet(Workbook workbook, LegendMetadata legendMetadata) {
    this.workbook = workbook;
    this.legendMetadata = legendMetadata;

    cellStyleHeaderBold = applyHeaderStyle(applyBoldFont(createRegularCellStyle()));
    cellStyleHeaderWrapped = applyHeaderStyle(applyTextWrap(createRegularCellStyle()));

    cellStyleBold = applyBoldFont(createRegularCellStyle());
    cellStyleWrapped = applyTextWrap(createRegularCellStyle());

    cellStyleAdded = applyFontColor(applyBoldFont(createRegularCellStyle()), IndexedColors.GREEN);
    cellStyleRemoved =
        applyStrikethrough(
            applyFontColor(applyBoldFont(createRegularCellStyle()), IndexedColors.RED));
    cellStyleNewValue = applyFontColor(applyBoldFont(createRegularCellStyle()), IndexedColors.BLUE);
    cellStyleOldValue =
        applyStrikethrough(
            applyFontColor(applyBoldFont(createRegularCellStyle()), IndexedColors.LIGHT_BLUE));
  }

  private XSSFCellStyle createRegularCellStyle() {
    XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
    style.setVerticalAlignment(VerticalAlignment.TOP);
    return style;
  }

  private XSSFCellStyle applyHeaderStyle(XSSFCellStyle style) {
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return style;
  }

  private XSSFCellStyle applyBoldFont(XSSFCellStyle style) {
    var boldFont = workbook.createFont();
    boldFont.setBold(true);
    style.setFont(boldFont);
    return style;
  }

  private XSSFCellStyle applyTextWrap(XSSFCellStyle style) {
    style.setWrapText(true);
    return style;
  }

  private XSSFCellStyle applyFontColor(XSSFCellStyle style, IndexedColors indexedColor) {
    style.getFont().setColor(indexedColor.getIndex());
    return style;
  }

  private XSSFCellStyle applyStrikethrough(XSSFCellStyle style) {
    style.getFont().setStrikeout(true);
    return style;
  }

  public void addToWorkbook() {
    Sheet sheet = workbook.createSheet("ReadMe");
    sheet.setColumnWidth(1, 256 * 24);
    sheet.setColumnWidth(2, 256 * 96);

    AtomicInteger rowIndex = new AtomicInteger(0);
    Stream.of(
            rowInfoStreamHeader(),
            rowInfoStreamSheets(),
            Stream.of(
                    rowInfoStreamAttributesColumns(),
                    rowInfoStreamQueryParametersColumns(),
                    rowInfoStreamQueryFiltersColumns())
                .limit(legendMetadata.sheetCount())
                .flatMap(Function.identity()),
            rowInfoStreamColorCodes())
        .flatMap(Function.identity())
        .forEach(
            rowInfo -> {
              Row row = sheet.createRow(rowIndex.getAndIncrement());
              if (rowInfo == null) return;

              row.setHeight((short) -1);

              Cell keyCell = row.createCell(1);
              keyCell.setCellValue(rowInfo.key.trim());
              keyCell.setCellStyle(rowInfo.keyStyle);

              Cell valueCell = row.createCell(2);
              valueCell.setCellValue(rowInfo.value.trim());
              valueCell.setCellStyle(rowInfo.valueStyle);
            });
  }

  private Stream<RowInfo> rowInfoStreamHeader() {
    return Stream.of(
        null,
        new RowInfo(
            cellStyleHeaderBold,
            "Data Overview",
            cellStyleHeaderWrapped,
"""
The Data Overview describes the objects and attributes of the standard and version below, \
highlighting their differences with respect to the baseline standard and version below.
"""),
        new RowInfo(
            cellStyleBold, "Standard", cellStyleBold, legendMetadata.currentStandardName()),
        new RowInfo(
            cellStyleBold, "Version", cellStyleBold, legendMetadata.currentStandardVersion()),
        new RowInfo(
            cellStyleBold,
            "Baseline",
            cellStyleBold,
            "%s %s"
                .formatted(
                    legendMetadata.baselineStandardName(),
                    legendMetadata.baselineStandardVersion())),
        null);
  }

  private Stream<RowInfo> rowInfoStreamSheets() {
    return Stream.of(
            Stream.of(
                new RowInfo(
                    cellStyleHeaderBold,
                    "Sheets",
                    cellStyleHeaderWrapped,
"""
The following sheets are available in this Data Overview, each with the columns described below.
""")),
            Stream.of(
                    Map.entry(
                        "Attributes hierarchical",
                        """
                        Hierarchical view of all objects and attributes, some appearing in multiple places \
                        in the hierarchy, following the structure of an API message
                        """),
                    Map.entry(
                        "Attributes normalized",
                        """
                        Normalized view of all objects and attributes, each of them listed exactly once
                        """),
                    Map.entry(
                        "Query parameters",
                        """
                        List of all query parameters available in the GET endpoints
                        """),
                    Map.entry(
                        "Query filters",
                        """
                        List of all query filters (combinations of query parameters) available in the GET endpoints
                        """))
                .map(
                    entry ->
                        new RowInfo(
                            cellStyleBold, entry.getKey(), cellStyleWrapped, entry.getValue())),
            Stream.of((RowInfo) null))
        .flatMap(Function.identity());
  }

  private Stream<RowInfo> rowInfoStreamAttributesColumns() {
    return Stream.of(
            Stream.of(
                new RowInfo(
                    cellStyleHeaderBold,
                    "Attributes columns",
                    cellStyleHeaderWrapped,
                    "Columns of the hierarchical and normalized attributes sheets")),
            Stream.of(
                    Map.entry(
                        "Diff",
                        """
                        Indicates whether and how the object or attribute listed in this row compares in the current standard and version \
                        to the corresponding object or attribute from the baseline standard and version, \
                        using the coloring convention described the 'Color codes' section below.
                        """),
                    Map.entry(
                        "Path",
                        """
                        Indicates the full path from the root to the object or attribute in this row.
                        (Only present in the "Attributes hierarchical" sheet.)
                        """),
                    Map.entry(
                        "Object",
                        """
                        Indicates the the object in this row, or the object containing the attribute in this row.
                        (Only present in the "Attributes normalized" sheet.)
                        """),
                    Map.entry(
                        "Attribute",
                        """
                        Name of the attribute in this row.
                        (Empty for object rows.)
                        """),
                    Map.entry(
                        "Type",
                        """
                        Type of the attribute in this row.
                        """),
                    Map.entry(
                        "Required",
                        """
                        Set to "yes" if the attribute in this row is mandatory within the object containing it; blank otherwise.
                        (Empty for object rows: objects are never "required" themselves outside of a context; \
                        only specific attributes of an object type can be required within enclosing objects.)
                        """),
                    Map.entry(
                        "Size",
                        """
                        The specified minimum and maximum size constraints of an attribute, from the following full list:
                        - minItems and maxItems (only for array attributes)
                        - minLength and maxLength (only for string attributes or string array attributes, \
                        indicating the min and max length of each array item if the latter)
                        - minimum and maximum (only for numeric attributes)
                        - exclMin and exclMax (exclusive min and max values; only for numeric attributes)
                        (Empty for object rows.)
                        """),
                    Map.entry(
                        "Pattern",
                        """
                        Regular expression pattern constraint to be enforced on the attribute in this row.
                        (Only for string attributes or string array attributes, indicating the pattern of each array item if the latter.)
                        (Empty for object rows.)
                        """),
                    Map.entry(
                        "Example",
                        """
                        Example value for the object or attribute in this row.
                        """),
                    Map.entry(
                        "Description",
                        """
                        Description of the object or attribute in this row.
                        (Also includes a textual description of the constraints in standards released before Q2 2025.)
                        """),
                    Map.entry(
                        "Constraints",
                        """
                        Constraints applicable to the attribute in this row.
                        (Only applicable for constraints defined in the information model in standards released after Q2 2025.)
                        """))
                .map(
                    entry ->
                        new RowInfo(
                            cellStyleBold, entry.getKey(), cellStyleWrapped, entry.getValue())),
            Stream.of((RowInfo) null))
        .flatMap(Function.identity());
  }

  private Stream<RowInfo> rowInfoStreamQueryParametersColumns() {
    return Stream.of(
            Stream.of(
                new RowInfo(
                    cellStyleHeaderBold,
                    "Query parameters columns",
                    cellStyleHeaderWrapped,
                    "Columns of the 'Query parameters' sheet")),
            Stream.of(
                    Map.entry(
                        "Diff",
                        """
                        Indicates whether and how the query parameter in this row compares in the current standard and version \
                        to the corresponding query parameter from the baseline standard and version, \
                        using the coloring convention described the 'Color codes' section below.
                        """),
                    Map.entry("Name", "Name of the query parameter"),
                    Map.entry("Query parameters", "Type of the query parameter"),
                    Map.entry("Query filters", "Description of the query parameter"))
                .map(
                    entry ->
                        new RowInfo(
                            cellStyleBold, entry.getKey(), cellStyleWrapped, entry.getValue())),
            Stream.of((RowInfo) null))
        .flatMap(Function.identity());
  }

  private Stream<RowInfo> rowInfoStreamQueryFiltersColumns() {
    return Stream.of(
            Stream.of(
                new RowInfo(
                    cellStyleHeaderBold,
                    "Query filters columns",
                    cellStyleHeaderWrapped,
                    "Columns of the 'Query filters' sheet")),
            Stream.of(
                    Map.entry(
                        "Diff",
                        """
                        Indicates whether and how the query filter in this row compares in the current standard and version \
                        to the corresponding query parameter from the baseline standard and version, \
                        using the coloring convention described the 'Color codes' section below.
                        """),
                    Map.entry(
                        "Name",
                        """
                        The list of query parameters that together constitute this query filter
                        """),
                    Map.entry(
                        "Required",
                        """
                        Set to "yes" if the adopter must implement support for this query filter; \
                        empty if the query filter is defined but not required.
                        """))
                .map(
                    entry ->
                        new RowInfo(
                            cellStyleBold, entry.getKey(), cellStyleWrapped, entry.getValue())),
            Stream.of((RowInfo) null))
        .flatMap(Function.identity());
  }

  private Stream<RowInfo> rowInfoStreamColorCodes() {
    return Stream.of(
        null,
        new RowInfo(
            cellStyleHeaderBold,
            "Color codes",
            cellStyleHeaderWrapped,
            """
            Color codes and cell styles indicating whether and how the element in this row \
            compares in the current standard and version to the corresponding element \
            from the baseline standard and version.
            """),
        new RowInfo(
            cellStyleBold,
            "Unchanged",
            cellStyleWrapped,
            """
            This element is unchanged between the current standard and version and the baseline standard and version.
            (Empty cell in the "Diff" column; black regular text in all other columns.)
            """),
        new RowInfo(
            cellStyleAdded,
            "Added",
            cellStyleWrapped,
            """
            This element exists only in the current standard and version, but not in the baseline standard and version.
            (Green regular text in all columns.)
            """),
        new RowInfo(
            cellStyleRemoved,
            "Removed",
            cellStyleWrapped,
            """
            This element existed only in the baseline standard and version, but was removed from the current standard and version.
            (Red strikethrough text in all columns.)
            """),
        new RowInfo(
            cellStyleNewValue,
            "New value",
            cellStyleWrapped,
            """
            This element exists both in the current and in the baseline standard and version, \
            but there are differences between the current element and the baseline one.
            (Blue regular text in all columns.)
            """),
        new RowInfo(
            cellStyleOldValue,
            "Old value",
            cellStyleWrapped,
            """
            Old values of an element that has changed between the baseline and the current standard and version; \
            displayed immediately above the row containing the new values.
            (Blue strikethrough text in the columns where the old value different from the new value; \
            all other cells are empty.)
            """),
        null);
  }
}
