package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "DRO",
    description = "Code used to denote the specific IoT event that has occurred")
@AllArgsConstructor
public enum IotEventCode implements EnumBase {
  BREADCRUMB("Continuous geolocation update"),
  DOOR_CLOSED("Door closed"),
  DOOR_OPENED("Door opened"),
  GEOFENCE_ENTRY("Geofenced area entry"),
  GEOFENCE_EXIT("Geofenced area exit"),
  LOCATION_REACHED("Meaningful location reached"),
  MOTION_START("Motion start"),
  MOTION_STOP("Motion stop");

  private final String valueDescription;
}
