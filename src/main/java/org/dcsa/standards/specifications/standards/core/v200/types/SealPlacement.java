package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", maxLength = 100, example = "DOOR", description = "The placement of a seal.")
@AllArgsConstructor
public enum SealPlacement implements EnumBase {
  DOOR("Door"),
  VENT("Vent");

  private final String valueDescription;
}
