package org.dcsa.standards.specifications.standards.core.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "EST",
    description = "Classifier denoting whether an event is planned, estimated or actual",
    maxLength = 10)
@AllArgsConstructor
public enum EventClassifierCode implements EnumBase {
  PLN("Planned"),
  EST("Estimated"),
  ACT("Actual")
  ;

  private final String valueDescription;
}
