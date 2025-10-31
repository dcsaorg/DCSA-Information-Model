package org.dcsa.standards.specifications.standards.core.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    maxLength = 10,
    example = "RAMP",
    description =
"""
Code identifying a specific type of facility
""")
@AllArgsConstructor
public enum FacilityTypeCode implements EnumBase {
  BORD("Border"),
  CLOC("Customer location"),
  COFS("Container freight station"),
  OFFD("Off-dock storage"),
  DEPO("Depot"),
  INTE("Inland terminal"),
  POTE("Port terminal"),
  RAMP("Ramp"),
  WAYP("Waypoint");

  private final String valueDescription;
}
