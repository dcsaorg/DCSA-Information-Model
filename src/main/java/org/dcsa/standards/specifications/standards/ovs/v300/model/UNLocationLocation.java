package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An interface used to express a location using a `Un Location Code`.")
@Data
public class UNLocationLocation {

  @Schema(
      description = "The name of the location.",
      pattern = "^\\S+(\\s+\\S+)*$",
      maxLength = 100,
      example = "Port of Amsterdam")
  private String locationName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Discriminator used to identify this as a `UNLocation` location interface.",
      maxLength = 4,
      example = "UNLO")
  private String locationType;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
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
}

