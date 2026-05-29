package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
General purpose object to capture location-related data, the location can be specified in **any** of the following ways:
  - `address` (used to specify the location via a **structured** Address)
  - `addressLines` (used to specify a location via an **unstructured** Address)
  - `UNLocationCode`
  - `FacilitySMDGCode` (used to specify a location using a `facilitySMDGCode`)

  It is expected that if a location is specified in multiple ways (both as an `Address` and as a `Facility`) that both ways point to the same location.

  **Condition:** Providers **or** consumers on API v1.0.1 (or earlier): `addressLines` cannot be used as the only property to identify the location.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransportCallLocation
    extends org.dcsa.standards.specifications.standards.cs.v100.model.TransportCallLocation {

  @Schema(description = "The name of the facility.", maxLength = 100, example = "Building 123")
  private String facilityName;

  @ArraySchema(
      maxItems = 5,
      arraySchema =
          @Schema(
              description =
"""
Unstructured address lines, used as a fallback when structured address fields are unavailable.

**Condition:** Providers **or** consumers on API v1.0.1 (or earlier): `addressLines` cannot be used as the only property to identify the location.
"""),
      schema =
          @Schema(
              description = "One line of an unstructured `Address`.",
              maxLength = 250,
              example = "123 Example Rd"))
  private List<String> addressLines;
}
