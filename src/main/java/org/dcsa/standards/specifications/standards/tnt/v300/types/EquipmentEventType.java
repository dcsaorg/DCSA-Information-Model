package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "LOADED",
    description = "The type of an equipment event")
@AllArgsConstructor
public enum EquipmentEventType implements EnumBase {
  AVAILABLE_FOR_DROP_OFF("Available for drop-off"),
  AVAILABLE_FOR_PICKUP("Available for pickup"),
  CROSSED("Crossed"),
  CUSTOMS_RELEASED("Released by customs"),
  CUSTOMS_SELECTED_FOR_INSPECTION("Selected for customs inspection"),
  CUSTOMS_SELECTED_FOR_SCAN("Selected for customs scan"),
  DISCHARGED("Discharged"),
  DROPPED_OFF("Dropped off"),
  GATED_IN("Gated in"),
  GATED_OUT("Gated out"),
  INSPECTED("Inspected"),
  LOADED("Loaded"),
  PICKED_UP("Picked up"),
  REMOVED("Removed"),
  RESEALED("Resealed"),
  STRIPPED("Stripped"),
  STUFFED("Stuffed");

  private final String valueDescription;
}
