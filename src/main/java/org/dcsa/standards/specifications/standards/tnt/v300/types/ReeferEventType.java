package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", example = "MEASURED", description = "The type of a reefer event")
@AllArgsConstructor
public enum ReeferEventType implements EnumBase {
  ADJUSTED("Adjusted"),
  MEASURED("Measured");

  private final String valueDescription;
}
