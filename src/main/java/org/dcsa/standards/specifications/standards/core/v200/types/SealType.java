package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", maxLength = 100, example = "BOLT", description = "The type of a seal.")
@AllArgsConstructor
public enum SealType implements EnumBase {
  KEYLESS_PADLOCK("Keyless padlock"),
  BOLT("Bolt"),
  WIRE("Wire");

  private final String valueDescription;
}
