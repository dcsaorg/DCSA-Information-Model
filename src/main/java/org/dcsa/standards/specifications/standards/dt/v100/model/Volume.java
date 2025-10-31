package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Volume value and measurement unit")
@Data
public class Volume {

  @Schema(
      description = "Volume value expressed in the measurement `unit`",
      example = "2.4",
      format = "float")
  protected Double value;

  @Schema(
      description =
"""
Measurement unit in which the volume `value` is expressed:
- `FTQ` (Cubic foot)
- `MTQ` (Cubic meter)
- `LTR` (Litre)
""",
      example = "MTQ",
      maxLength = 10)
  protected String unit;
}
