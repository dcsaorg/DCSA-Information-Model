package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "LADEN",
    description = "Code to denote whether the equipment is empty or laden.")
@AllArgsConstructor
public enum EmptyIndicatorCode implements EnumBase {
  EMPTY("Empty"),
  LADEN("Laden");

  private final String valueDescription;
}
