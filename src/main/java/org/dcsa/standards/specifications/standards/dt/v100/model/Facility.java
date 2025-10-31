package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Facility.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Facility {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Facility location expressed using a facility code. Requires a code and a provider (SMDG or BIC).";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The code used for identifying the specific facility. This code does not include the UN Location Code.
The definition of the code depends on the `facilityCodeListProvider`. As code list providers maintain multiple codeLists the following codeList is used:
- `SMDG` (the codeList used is the [SMDG Terminal Code List](https://smdg.org/documents/smdg-code-lists/))
- `BIC` (the codeList used is the [BIC Facility Codes](https://www.bic-code.org/facility-codes/))
""",
      example = "ADT",
      maxLength = 6,
      pattern = "^\\S(?:.*\\S)?$")
  protected String facilityCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The provider used for identifying the facility Code. Some facility codes are only defined in combination with an `UN Location Code`
- `BIC` (Requires a UN Location Code)
- `SMDG` (Requires a UN Location Code)
""",
      example = "SMDG",
      enumAsRef = true)
  protected String facilityCodeListProvider;
}
