package org.dcsa.standards.specifications.standards.ebl.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Address where the party is located. Includes street details, postal info, and country/UN location codes.")
@Data
public class PartyAddress {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The name of the street of the party’s address.", example = "Ruijggoordweg", maxLength = 70)
  private String street;

  @Schema(description = "The number of the street of the party’s address.", example = "100", maxLength = 50)
  private String streetNumber;

  @Schema(description = "The floor of the party’s street number.", example = "2nd", maxLength = 50, pattern = "^\\S(?:.*\\S)?$")
  private String floor;

  @Schema(description = "The post code of the party’s address.", example = "1047 HM", maxLength = 10)
  private String postCode;

  @Schema(name = "POBox", description = "A numbered box at a post office where a person or business can have mail or parcels delivered.", example = "123", maxLength = 20)
  private String poBox;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The city name of the party’s address.", example = "Amsterdam", maxLength = 35, pattern = "^\\S(?:.*\\S)?$")
  private String city;

  @Schema(
      name = "UNLocationCode",
      description =
"""
The UN Location code specifying where the carrier booking office is located. The pattern used must be
- 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
- 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used
More info can be found here: [UN/LOCODE](https://unece.org/trade/cefact/UNLOCODE-Download)
""",
      example = "NLAMS",
      minLength = 5,
      maxLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;

  @Schema(description = "The state/region of the party’s address.", example = "North Holland", maxLength = 65)
  private String stateRegion;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)", example = "NL", minLength = 2, maxLength = 2, pattern = "^[A-Z]{2}$")
  private String countryCode;
}
