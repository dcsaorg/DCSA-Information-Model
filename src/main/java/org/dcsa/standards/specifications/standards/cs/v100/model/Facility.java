package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "A way to express a location using a `Facility`. The facility can either be expressed using a `BIC` code or a `SMDG` code. The `facilityCode` does not contain the `UNLocationCode` - this should be provided in the `UnLocationCode` attribute.")
@Data
public class Facility {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The code used for identifying the specific facility. This code does not include the UN Location Code.The definition of the code depends on the `facilityCodeListProvider`. As code list providers maintain multiple codeLists the following codeList is used:
  - for `SMDG` - the codeList used is the [SMDG Terminal Code List](https://smdg.org/documents/smdg-code-lists/)
  - for `BIC`  - the codeList used is the [BIC Facility Codes](https://www.bic-code.org/facility-codes/)
""",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 6,
      example = "ADT")
  private String facilityCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The provider used for identifying the facility Code. Some facility codes are only defined in combination with an `UN Location Code`.
- `BIC` (Requires a UN Location Code)
- `SMDG` (Requires a UN Location Code)
""",
      allowableValues = {"BIC", "SMDG"},
      example = "SMDG")
  private String facilityCodeListProvider;
}
