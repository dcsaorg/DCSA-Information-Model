package org.dcsa.standards.specifications.standards.an.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    maxLength = 3,
    example = "WD",
    description = "Code identifying the unit in which free time is expressed")
@AllArgsConstructor
public enum FreeTimeTimeUnitCode implements EnumBase {
  CD("Calendar days"),
  WD("Working days"),
  HR("Hours");

  private final String valueDescription;
}
