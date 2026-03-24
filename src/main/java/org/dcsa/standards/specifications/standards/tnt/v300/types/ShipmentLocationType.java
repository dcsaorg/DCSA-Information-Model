package org.dcsa.standards.specifications.standards.tnt.v300.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "PLACE_OF_DELIVERY",
    description = "The type of a shipment location")
@AllArgsConstructor
public enum ShipmentLocationType implements EnumBase {
  FULL_CONTAINER_DROP_OFF("Full container drop-off location"),
  INTERMEDIATE_EXPORT_STOP_OFF("Container intermediate export stop-off location"),
  ONWARD_INLAND_ROUTING("Onward Inland Routing"),
  ORIGIN_OF_GOODS("Origin of goods"),
  PLACE_OF_DELIVERY("Place of Delivery"),
  PLACE_OF_RECEIPT("Place of Receipt"),
  PORT_OF_DISCHARGE("Port of Discharge"),
  PORT_OF_LOADING("Port of Loading"),
  PRE_CARRIAGE_FORM("Pre-carriage From"),
  PROHIBITED_TRANSSHIPMENT_PORT("Prohibited transshipment port"),
  REQUESTED_TRANSSHIPMENT_PORT("Requested transshipment port");

  private final String valueDescription;
}
