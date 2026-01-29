package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
An object to capture `Place of Final Delivery` location specified as: Identification of the seaport, freight terminal or other place at which the goods are handed over from the `Ocean Transport Intermediary` (OTI) issuing the `House Bill of Lading` to the `Consignee`.

**Condition:** Mandatory if different from `Place of Delivery` at the `Master Transport Document` level.

**Condition:** The location can be specified either using `UN Location Code` and/or (`Location Name` together with `Country Code`), all three properties can be specified.
""",
    title = "Place of Final Delivery")
@Data
public class PlaceOfFinalDelivery {

  @Schema(
      description =
"""
The name of the location.

**Condition:** Mandatory to provide in case `UN Location Code` is not provided
""",
      example = "Port of Amsterdam",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String locationName;

  @Schema(
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Condition:** Mandatory to provide in case `UN Location Code` is not provided

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
      example = "NL",
      pattern = "^[A-Z]{2}$",
      minLength = 2,
      maxLength = 2)
  private String countryCode;

  @Schema(
      name = "UNLocationCode",
      description =
"""
The UN Location code specifying where the place is located. The pattern used must be

- 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
- 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used

More info can be found here: [UN/LOCODE](https://unece.org/trade/cefact/UNLOCODE-Download)
""",
      example = "NLAMS",
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$",
      minLength = 5,
      maxLength = 5)
  private String unLocationCode;
}
