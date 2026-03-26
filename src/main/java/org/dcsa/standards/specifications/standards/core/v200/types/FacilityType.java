package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    maxLength = 100,
    example = "DEPOT",
    description = "A specific type of facility")
@AllArgsConstructor
public enum FacilityType implements EnumBase {
  BORDER("Border"),
  CUSTOMER_LOCATION("Customer location"),
  DEPOT("Depot"),
  CONTAINER_FREIGHT_STATION("Container freight station"),
  INLAND_TERMINAL("Inland terminal"),
  OFF_DOCK_STORAGE("Off-dock storage"),
  PORT_TERMINAL("Port terminal"),
  RAMP("Ramp"),
  WAYPOINT("Waypoint");

  private final String valueDescription;
}
