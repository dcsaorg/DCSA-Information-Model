package org.dcsa.standards.specifications.standards.core.v103.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

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
