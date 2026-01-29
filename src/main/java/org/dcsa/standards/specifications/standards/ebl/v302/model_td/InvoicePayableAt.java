package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Location where payment of ocean freight and charges for the main transport will take place by the customer. Can be a UN Location Code or free-text name.")
@Data
public class InvoicePayableAt {

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
      minLength = 5,
      maxLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;

  @Schema(description = "The name of the location where payment will be rendered by the customer.", example = "DCSA Headquarters", maxLength = 35)
  private String freeText;
}
