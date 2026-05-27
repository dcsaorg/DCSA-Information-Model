package org.dcsa.standards.specifications.standards.ovs.v301.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
A transportCall in the schedule. A transportCall can be either just a Port or further specified as a terminalCall.

The order of the list is the sequence of the list
""")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransportCall
    extends org.dcsa.standards.specifications.standards.ovs.v300.model.TransportCall {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The identifier of an import voyage. The carrier-specific identifier of the import Voyage.

**Note:** In case the `carrierImportVoyageNumber` is not known, `9999R` should be interpreted as "no voyage number". The value `9999R` is reserved as a placeholder and **MUST NOT** be used for real voyage numbers.
""",
      maxLength = 50,
      example = "2103N")
  private String carrierImportVoyageNumber;

  @Schema(
      description =
"""
The set of codes in `Status Code` are ONLY meant to communicate any change / exception to the published schedule. This is not required in case of normal schedule. Possible values are:

- `OMIT` (Omit)
- `BLNK` (Blank)
- `ADHO` (Ad Hoc)
- `PHOT` (Phase Out)
- `PHIN` (Phase In)
- `SLID` (Sliding)
- `ROTC` (Rotation Change)
- `CUTR` (Cut and Run)

**Deprecated:** Use `statusCodes` instead. If `statusCodes` is used - then values in this property are ignored.
""",
      deprecated = true,
      example = "OMIT")
  private String statusCode;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
Possibility to add multiple status codes to a `TransportCall`.

**Note:** This property takes precedence over `statusCode` (if both are present - only the ones provided in this property are used).
"""),
      schema =
          @Schema(
              description =
"""
The set of codes are ONLY meant to communicate any change / exception to the published schedule. This is not required in case of normal schedule. Possible values are:

- `OMIT` (Omit)
- `BLNK` (Blank)
- `ADHO` (Ad Hoc)
- `PHOT` (Phase Out)
- `PHIN` (Phase In)
- `SLID` (Sliding)
- `ROTC` (Rotation Change)
- `CUTR` (Cut and Run)
- `DRYD` (Dry Dock)
- `BUNK` (Bunkering)
- `OOSV` (Out of service)
""",
              example = "OMIT"))
  private List<String> statusCodes;
}
