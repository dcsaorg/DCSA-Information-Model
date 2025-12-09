package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "DRO",
    description = "Code used to denote the specific IoT event that has occurred")
@AllArgsConstructor
public enum IotEventCode implements EnumBase {
  DRC("Door closed"),
  DRO("Door opened"),
  LOC("Location update");

  private final String valueDescription;
}
