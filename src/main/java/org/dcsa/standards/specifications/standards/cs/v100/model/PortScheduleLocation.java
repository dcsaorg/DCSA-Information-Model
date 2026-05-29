package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
General purpose object to capture location-related data, the location can be specified in **any** of the following ways:
  - `UNLocationCode`
  - `FacilitySMDGCode` (used to specify a location using a `facilitySMDGCode`)

  It is expected that if a location is specified in multiple ways (both as a `UNLocationCode` and as a `facilitySMDGCode`) that both ways point to the same location.
""")
@Data
public class PortScheduleLocation {

  @Schema(
      description = "An optional name for the location.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 100,
      example = "Port of Amsterdam")
  private String locationName;

  @Schema(
      name = "UNLocationCode",
      description =
"""
The UN Location code specifying where the place is located. The pattern used must be

  - 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
  - 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used

  More info can be found here: [UN/LOCODE](https://unece.org/trade/cefact/UNLOCODE-Download)
""",
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$",
      minLength = 5,
      maxLength = 5,
      example = "NLAMS")
  private String unLocationCode;

  @Schema(
      description =
          "The code used for identifying the specific facility. This code does not include the UN Location Code.\nThe codeList used by SMDG is the [SMDG Terminal Code List](https://smdg.org/documents/smdg-code-lists/)",
      maxLength = 6,
      example = "ACT")
  private String facilitySMDGCode;
}
