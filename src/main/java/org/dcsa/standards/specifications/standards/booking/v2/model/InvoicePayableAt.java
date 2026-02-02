package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = InvoicePayableAt.CLASS_SCHEMA_DESCRIPTION)
@Data
public class InvoicePayableAt {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Location where payment of ocean freight and charges for the main transport will take place by the customer.\n\nThe location must be provided as a `UN Location Code`";

  @Schema(
      name = "UNLocationCode",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The UN Location code specifying where the place is located. The pattern used must be

- 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
- 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used

More info can be found here: [UN/LOCODE](https://unece.org/trade/cefact/UNLOCODE-Download)
""",
      example = "NLAMS",
      maxLength = 5,
      minLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;
}
