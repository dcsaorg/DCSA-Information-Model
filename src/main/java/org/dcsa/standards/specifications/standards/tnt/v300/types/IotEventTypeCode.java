package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "DETC",
    description = "Code used to denote the type of an IoT event")
@AllArgsConstructor
public enum IotEventTypeCode implements EnumBase {
  DETC("Detected");

  private final String valueDescription;
}
