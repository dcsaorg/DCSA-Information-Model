package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Address;
import org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail;

@Schema(description = CarrierBookingOffice.CLASS_SCHEMA_DESCRIPTION)
@Data
public class CarrierBookingOffice {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The carrier office responsible for processing the booking.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the party.",
      example = "IKEA Denmark",
      maxLength = 70,
      pattern = "^\\S(?:.*\\S)?$")
  private String partyName;

  @Schema(
      name = "UNLocationCode",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The UN Location code specifying where the carrier booking office is located. The pattern used must be

- 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
- 3 characters to code a location within that country. Letters A-Z and numbers from 2-9 can be used

More info can be found here: [UN/LOCODE](https://unece.org/trade/cefact/UNLOCODE-Download)
""",
      example = "NLAMS",
      maxLength = 5,
      minLength = 5,
      pattern = "^[A-Z]{2}[A-Z2-9]{3}$")
  private String unLocationCode;

  @Schema(description = "Address of the carrier booking office.")
  private Address address;

  @Schema(description = "A list of contact details")
  @ArraySchema(minItems = 1)
  private List<PartyContactDetail> partyContactDetails;
}
