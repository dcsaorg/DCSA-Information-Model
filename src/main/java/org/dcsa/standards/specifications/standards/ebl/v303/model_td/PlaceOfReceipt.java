package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;

@Schema(
    description =
"""
An object to capture `Place of Receipt` location specified as: the location where the cargo is handed over by the shipper, or his agent, to the shipping line. This indicates the point at which the shipping line takes on responsibility for carriage of the container.

**Condition:** Only when pre-carriage is done by the carrier.

The location can be specified in **any** of the following ways: `UN Location Code`, `Facility`, `Address` (structured address), `AddressLines` (unstructured address) or a `Geo Coordinate`.

**Condition:** It is expected that if a location is specified in multiple ways (e.g. both as an `Address` and as a `Facility`) that both ways point to the same location.

**Condition:** When communicating with providers **or** consumers implementing API **v3.0.2 or earlier**, a sender implementing API **v3.0.3 or later MUST NOT** use `addressLines` as the only property to identify the location. Recipients implementing earlier versions **MAY ignore** this property.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class PlaceOfReceipt
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.PlaceOfReceipt {

  @Schema(
      description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.

**Condition:** When communicating with providers **or** consumers implementing API **v3.0.2 or earlier**, a sender implementing API **v3.0.3 or later MUST NOT** use `addressLines` as the only property to identify the location. Recipients implementing earlier versions **MAY ignore** this property.
""")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;

  @Schema(maxLength = 100, example = "Building 123", description = "The name of the facility.")
  private String facilityName;
}
