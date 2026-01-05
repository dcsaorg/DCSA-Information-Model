package org.dcsa.standards.specifications.standards.core.v103.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    maxLength = 50,
    example = "VESSEL",
    description = "The mode of transport as defined by DCSA")
@AllArgsConstructor
public enum ModeOfTransportCode implements EnumBase {
  VESSEL("Vessel"),
  RAIL("Rail"),
  TRUCK("Truck"),
  BARGE("Barge"),
  MULTIMODAL("Multimodal");

  private final String valueDescription;
}
