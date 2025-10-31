package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "MEAS",
    description = "Code used to denote the type of a reefer event")
@AllArgsConstructor
public enum ReeferEventTypeCode implements EnumBase {
  MEAS("Measured"),
  ADJU("Adjusted");

  private final String valueDescription;
}
