package org.dcsa.standards.specifications.dataoverview;

public record LegendMetadata(
    String currentStandardName,
    String currentStandardVersion,
    String baselineStandardName,
    String baselineStandardVersion,
    int sheetCount) {}
