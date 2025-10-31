package org.dcsa.standards.specifications.standards.an.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    maxLength = 10,
    example = "MNC",
    description = "A specific stage of transport")
@AllArgsConstructor
public enum TransportPlanStageCode implements EnumBase {
  PRC("Pre-Carriage Transport"),
  MNC("Main Carriage Transport"),
  ONC("On-Carriage Transport");

  private final String valueDescription;
}
