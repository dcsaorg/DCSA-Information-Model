package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.Facility;

@Schema(description = DischargeLocation.CLASS_SCHEMA_DESCRIPTION)
@Data
public class DischargeLocation {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An object to capture the `Discharge Location`.\n\nThe location can be specified in **any** of the following ways: `UN Location Code`, `Facility` or an `Address`.\n\n**Condition:** It is expected that if a location is specified in multiple ways (e.g. both as an `Address` and as a `Facility`) that both ways point to the same location.";

  @Schema(
      description = "The name of the location.",
      example = "Port of Amsterdam",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
  private String locationName;

  @Schema(description = "Address of the location.")
  private Address address;

  @Schema(description = "Facility information for the location.")
  private Facility facility;

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
}
