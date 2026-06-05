package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Address;
import org.dcsa.standards.specifications.standards.ebl.v3.model.Facility;

@Schema(
    description =
"""
An object to capture `Onward Inland Routing` location specified as the end location of the inland movement that takes place after the container(s) being delivered to the port of discharge/place of delivery for account and risk of merchant (merchant haulage).

The location can be specified in **any** of the following ways: `UN Location Code`, `Facility` or an `Address`.

**Condition:** It is expected that if a location is specified in multiple ways (e.g. both as an `Address` and as a `Facility`) that both ways point to the same location.
""")
@Data
public class OnwardInlandRouting {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "End location of inland movement after delivery to port of discharge/place of delivery for merchant haulage. May be specified via UN Location Code, Facility, or Address.";

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
      minLength = 5,
      maxLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;

  @Schema(description = "Address of the inland routing location.")
  private Address address;

  @Schema(description = "Facility used for the inland routing location.")
  private Facility facility;
}
