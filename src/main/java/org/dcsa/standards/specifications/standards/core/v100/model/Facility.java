package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
Facility identified by a code as defined by a code list provider.
""")
@Data
public class Facility {

  @Schema(
      description =
"""
Code identifying a specific facility, as defined by the `facilityCodeListProvider`.
""",
      example = "ADT",
      maxLength = 6)
  protected String facilityCode;

  @Schema(
      description =
"""
Code list provider (each with its relevant code list) defining the `facilityCode`:
- `SMDG` (using [SMDG Terminal Code List](https://smdg.org/documents/smdg-code-lists/smdg-terminal-code-list/))
- `BIC` (using [BIC Facility Codes](https://www.bic-code.org/facility-codes/))
""",
      example = "SMDG",
      maxLength = 10)
  protected String facilityCodeListProvider;
}
