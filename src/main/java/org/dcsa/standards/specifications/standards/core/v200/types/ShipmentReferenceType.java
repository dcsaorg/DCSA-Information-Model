package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    maxLength = 100,
    example = "FREIGHT_FORWARDERS_REFERENCE",
    description = "Type of a shipment reference")
@AllArgsConstructor
public enum ShipmentReferenceType implements EnumBase {
  CONSIGNEES_REFERENCE("Consignee’s Reference"),
  AUTOMATED_EXPORT_SYSTEM("Automated Export System"),
  BOOKING_REQUEST_ID("Booking Request ID"),
  BOOKING_PARTY_REFERENCE_NUMBER("Booking party reference number"),
  CANADIAN_EXPORT_REPORTING_NUMBER("Canadian Export Reporting System"),
  CUSTOMERS_REFERENCE("Customer’s Reference"),
  CUSTOMER_SHIPMENT_ID("Customer shipment ID"),
  DECLARACAO_UNICA_DE_EXPORTACAO("Declaração Única de Exportação"),
  EMPTY_CONTAINER_RELEASE_REFERENCE("Empty container release reference"),
  EQUIPMENT_REFERENCE("Equipment Reference"),
  FREIGHT_FORWARDERS_REFERENCE("Freight Forwarder’s Reference"),
  PURCHASE_ORDER_REFERENCE("Purchase Order Reference"),
  REGISTRO_UNICO_DEL_CONTRIBUYENTE("Registro Único del Contribuyente"),
  SHIPPERS_REFERENCE("Shipper’s Reference");

  private final String valueDescription;
}
