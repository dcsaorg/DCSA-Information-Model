package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "ARRI",
    description = "Code used to denote the type of a transport event")
@AllArgsConstructor
public enum TransportEventTypeCode implements EnumBase {
  ARRI("Arrived"),
  DEPA("Departed");

  private final String valueDescription;
}
