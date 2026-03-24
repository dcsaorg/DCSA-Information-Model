package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(type = "string", example = "ARRIVED", description = "The type of a transport event")
@AllArgsConstructor
public enum TransportEventType implements EnumBase {
  APPROACHING("Close to arriving at a certain location"),
  ARRIVED("Arrived at a certain location"),
  DEPARTED("Departed from a certain location");

  private final String valueDescription;
}
