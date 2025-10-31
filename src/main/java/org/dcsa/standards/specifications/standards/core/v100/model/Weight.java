package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Weight.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Weight {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Weight value and measurement unit";

  @Schema(
      description = "Weight value expressed in the measurement `unit`",
      example = "2.4",
      format = "float")
  protected Double value;

  @Schema(
      description =
"""
Measurement unit in which the weight `value` is expressed:
- `KGM` (Kilograms)
- `LBR` (Pounds)
- `GRM` (Grams)
- `ONZ` (Ounce)
""",
      example = "KGM",
      maxLength = 10)
  protected String unit;
}
