package org.dcsa.standards.specifications.standards.vgm.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(type = "string", example = "SM1", description = "Method used to determine the VGM")
@AllArgsConstructor
public enum VGMMethodCode implements EnumBase {
  SM1("SOLAS verification method 1  (Scaled)"),
  SM2("SOLAS verification method 2 (Calculated)");

  private final String valueDescription;
}
