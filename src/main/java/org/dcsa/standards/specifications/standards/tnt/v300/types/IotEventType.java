package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", example = "DOOR_OPENED", description = "The type of an IoT event")
@AllArgsConstructor
public enum IotEventType implements EnumBase {
  BREADCRUMB("Periodic geolocation update"),
  DEVICE_STATUS("IoT device status update (pairing/unpairing, power status)"),
  DOOR_CLOSED("Door closed"),
  DOOR_OPENED("Door opened"),
  GEOFENCE_ENTRY("Geofenced area entry"),
  GEOFENCE_EXIT("Geofenced area exit"),
  MOTION_START("Motion start"),
  MOTION_STOP("Motion stop"),
  PAIRING("The IoT device is being paired with the equipment"),
  UNPAIRING("The IoT device is being unpaired from the equipment");

  private final String valueDescription;
}
