package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "EXPORT",
    description = "The phase of the transport lifecycle in which the event occurs")
@AllArgsConstructor
public enum TransportPhase implements EnumBase {
  EXPORT("Considered active until the vessel departs the Port of Loading"),
  IMPORT(
      "Considered to start when the vessel arrives at the Port of Discharge;"
          + " may remain active until the empty container is returned after stripping"),
  TRANSSHIPMENT(
      "Any intermediate arrival/departure or load/discharge that the carrier chooses to classify as such");

  private final String valueDescription;
}
