package org.dcsa.standards.specifications.standards.ebl.v3.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.Facility;
import org.dcsa.standards.specifications.standards.ebl.v3.model.GeoCoordinate;

@Schema(description = "Location where the cargo is handed over to the carrier. May be specified via UN Location Code, Facility, Address, or Geo Coordinate.")
@Data
public class PlaceOfReceipt {

  @Schema(description = "The name of the location.", example = "Port of Amsterdam", maxLength = 100, pattern = "^\\S(?:.*\\S)?$")
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

  @Schema(description = "The address of the location.")
  private Address address;

  @Schema(description = "Facility related to the location.")
  private Facility facility;

  @Schema(description = "Geographic coordinates of the location.")
  private GeoCoordinate geoCoordinate;
}
