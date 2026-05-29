package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Activity value and measurement unit")
@Data
public class Activity {

  @Schema(
      description = "Activity value expressed in the measurement `unit`",
      example = "2.5",
      format = "float")
  private Double value;

  @Schema(
      description =
"""
Measurement unit in which the activity `value` is expressed:
- `kBq`
- `MBq`
- `GBq`
- `TBq`
- `PBq`
- `mBq`
- `µBq`
""",
      example = "TBq",
      maxLength = 10)
  private String unit;
}
