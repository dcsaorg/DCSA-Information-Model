package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
An object used to express a location using a `Facility`. The facility can be expressed using one of `BIC` code or `SMDG` code. The `facilityCode` does not contain the `UNLocationCode` - this should be provided in the `UnLocationCode` attribute.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Facility extends org.dcsa.standards.specifications.standards.dt.v100.model.Facility {

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
}
