package org.dcsa.standards.specifications.standards.core.v103.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.types.AddressLine;

@Schema(description = "An object for storing address-related information.")
@Data
public class Address {

  @Schema(description = "The name of the street.", example = "Ruijggoordweg", maxLength = 70)
  protected String street;

  @Schema(description = "The number of the street.", example = "100", maxLength = 50)
  protected String streetNumber;

  @Schema(description = "The floor of the street number.", example = "N/A", maxLength = 50)
  protected String floor;

  @Schema(description = "The post code of the address.", example = "1047 HM", maxLength = 10)
  protected String postCode;

  @Schema(
      name = "POBox",
      description =
          "A numbered box at a post office where a person or business can have mail or parcels delivered.",
      example = "123",
      maxLength = 20)
  protected String poBox;

  @Schema(description = "The name of the city.", example = "Amsterdam", maxLength = 35)
  protected String city;

  @Schema(description = "The name of the state/region.", example = "North Holland", maxLength = 65)
  protected String stateRegion;

  @Schema(
      description =
          "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      example = "NL",
      maxLength = 2)
  protected String countryCode;

  @Schema(
      description =
          "Unstructured address lines, used as a fallback when structured address fields are unavailable.")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
