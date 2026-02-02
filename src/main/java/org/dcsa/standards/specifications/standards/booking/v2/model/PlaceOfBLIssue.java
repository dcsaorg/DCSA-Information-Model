package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = PlaceOfBLIssue.CLASS_SCHEMA_DESCRIPTION)
@Data
public class PlaceOfBLIssue {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An object to capture where the original Transport Document (`Bill of Lading`) will be issued.\n\n**Condition:** The location can be specified as one of `UN Location Code` or `CountryCode`, but not both.";

  @Schema(
      description = "The name of the location.",
      example = "Port of Amsterdam",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
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
      example = "NLAMS",
      maxLength = 5,
      minLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;

  @Schema(
      description = "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      example = "DK",
      maxLength = 2,
      minLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
