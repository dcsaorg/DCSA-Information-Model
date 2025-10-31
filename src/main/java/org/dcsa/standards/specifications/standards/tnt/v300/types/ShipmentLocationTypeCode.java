package org.dcsa.standards.specifications.standards.tnt.v300.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "PRE",
    description = "Code used to denote the type of a shipment location")
@AllArgsConstructor
public enum ShipmentLocationTypeCode implements EnumBase {
  FCD("Full container drop-off location"),
  IEL("Container intermediate export stop-off location"),
  OIR("Onward Inland Routing"),
  ORI("Origin of goods"),
  PCF("Pre-carriage From"),
  PDE("Place of Delivery"),
  POD("Port of Discharge"),
  POL("Port of Loading"),
  PRE("Place of Receipt"),
  PTP("Prohibited transshipment port"),
  RTP("Requested transshipment port");

  private final String valueDescription;
}
