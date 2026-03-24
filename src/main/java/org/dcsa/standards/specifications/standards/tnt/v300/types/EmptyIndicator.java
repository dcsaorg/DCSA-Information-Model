package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "LADEN",
    description = "Indicator of whether the equipment is empty or laden.")
@AllArgsConstructor
public enum EmptyIndicator implements EnumBase {
  EMPTY("Empty"),
  LADEN("Laden");

  private final String valueDescription;
}
