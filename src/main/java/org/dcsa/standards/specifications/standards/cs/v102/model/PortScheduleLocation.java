package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
General purpose object to capture location-related data, the location can be specified in **any** of the following ways:
  - `UNLocationCode`
  - `FacilitySMDGCode` (used to specify a location using a `facilitySMDGCode`)

  It is expected that if a location is specified in multiple ways (both as a `UNLocationCode` and as a `facilitySMDGCode`) that both ways point to the same location.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortScheduleLocation
    extends org.dcsa.standards.specifications.standards.cs.v100.model.PortScheduleLocation {

  @Schema(description = "The name of the facility.", maxLength = 100, example = "Building 123")
  private String facilityName;
}
