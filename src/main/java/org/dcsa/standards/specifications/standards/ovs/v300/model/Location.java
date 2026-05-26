package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "General purpose object to capture location-related data, the location can be specified in **one** of the following ways: `UN Location Code`, a `Facility` or an `Address`.")
@Data
public class Location {

  @Schema(description = "The name of the location.", maxLength = 100, example = "Port of Amsterdam")
  private String locationName;

  @Schema(
      description = "Discriminator used to identify the location type interface.",
      maxLength = 4,
      example = "UNLO")
  private String locationType;

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
"""
The code used for identifying the specific facility. This code does not include the UN Location Code.

The codeList used by SMDG is the [SMDG Terminal Code List](https://smdg.org/wp-content/uploads/Codelists/Terminals/SMDG-Terminal-Code-List-v20210401.xlsx)
""",
      maxLength = 6,
      example = "ACT")
  private String facilitySMDGCode;

  @Schema(description = "An object for storing address related information.")
  private Address address;
}
