package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "ESTIMATED",
    description =
        "Classifier denoting whether a timestamp or event is planned, estimated or actual",
    maxLength = 10)
@AllArgsConstructor
public enum EventClassifier implements EnumBase {
  ACTUAL("Actual"),
  ESTIMATED("Estimated"),
  PLANNED("Planned");

  private final String valueDescription;
}
