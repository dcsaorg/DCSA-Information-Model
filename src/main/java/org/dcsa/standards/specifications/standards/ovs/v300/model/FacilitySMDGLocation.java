package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "An interface used to express a location using a `Facility` by the `SMDG` code list. The `facilitySMDGCode` does not contain the `UNLocationCode` - this should be provided in the `UnLocationCode` attribute.")
@Data
public class FacilitySMDGLocation {

  @Schema(
      description = "The name of the location.",
      maxLength = 100,
      example = "Port of Amsterdam")
  private String locationName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Discriminator used to identify this as a `Facility Location` interface only using `SMDG` code list.",
      maxLength = 4,
      example = "FACS")
  private String locationType;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      name = "UNLocationCode",
      description =
"""
The UN Location code specifying where the place is located. The pattern used must be

 - 2 characters for the country code using [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
 - 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used

More info can be found here: [UN/LOCODE](https://en.wikipedia.org/wiki/UN/LOCODE)
""",
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$",
      minLength = 5,
      maxLength = 5,
      example = "NLAMS")
  private String unLocationCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The code used for identifying the specific facility. This code does not include the UN Location Code.

The codeList used by SMDG is the [SMDG Terminal Code List](https://smdg.org/wp-content/uploads/Codelists/Terminals/SMDG-Terminal-Code-List-v20210401.xlsx)
""",
      maxLength = 6,
      example = "ACT")
  private String facilitySMDGCode;
}

