package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "ARRIVED",
    description = "Code used to denote the type of a transport event")
@AllArgsConstructor
public enum TransportEventTypeCode implements EnumBase {
  APPROACHING("Close to arriving at a certain location"),
  ARRIVED("Arrived at a certain location"),
  DEPARTED("Departed from a certain location");

  private final String valueDescription;
}
