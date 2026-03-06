package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "SMART_CONTAINER",
    description = "Code used to indicate the type of IoT technology used by a piece of equipment")
@AllArgsConstructor
public enum IotEquipmentTechnologyCode implements EnumBase {
  SMART_CONTAINER(
      "The equipment is paired with an active IoT/telematics device. (SMDG code `SMC`)"),
  PASSIVE_TAG(
      "The equipment carries a passive identification tag (e.g. RFID/NFC). (SMDG code `SMP`)"),
  NONE(
      "The equipment is not paired with an active IoT device and carries no passive identification tag."),
  UNKNOWN("It is not known whether the equipment has IoT technology, or of what type.");

  private final String valueDescription;
}
