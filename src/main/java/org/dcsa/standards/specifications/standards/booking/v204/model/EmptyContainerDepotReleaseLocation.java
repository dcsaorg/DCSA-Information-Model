package org.dcsa.standards.specifications.standards.booking.v204.model;

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
An object to capture the `Empty Container Depot Release Location`.

The location of the depot from which the empty container(s) will be released from

The location can be specified in **any** of the following ways: `Address` (structured address), `AddressLines` (unstructured address), `Facility`, `UN Location Code` or a `GeoCoordinate`.

**Condition:** It is expected that if a location is specified in multiple ways (e.g. both as an `Address` and as a `Facility`) that both ways point to the same location.

**Condition:** When communicating with providers **or** consumers implementing API **v2.0.3 or earlier**, a sender implementing API **v2.0.4 or later MUST NOT** use `addressLines` as the only property to identify the location. Recipients implementing earlier versions **MAY ignore** this property.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class EmptyContainerDepotReleaseLocation
    extends org.dcsa.standards.specifications.standards.booking.v2.model
        .EmptyContainerDepotReleaseLocation {

  @Schema(
      description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.

**Condition:** When communicating with providers **or** consumers implementing API **v2.0.3 or earlier**, a sender implementing API **v2.0.4 or later MUST NOT** use `addressLines` as the only property to identify the location. Recipients implementing earlier versions **MAY ignore** this property.
""")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;

  @Schema(maxLength = 100, example = "Building 123", description = "The name of the facility.")
  private String facilityName;
}
