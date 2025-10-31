package org.dcsa.standards.specifications.standards.core.v100.types;

import org.dcsa.standards.specifications.generator.EnumBase;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(
    type = "string",
    example = "FF",
    description = "Code used to denote the type of a shipment reference")
@AllArgsConstructor
public enum ShipmentReferenceTypeCode implements EnumBase {
  AAO("Consignee’s Reference"),
  AES("Automated Export System"),
  BID("Booking Request ID"),
  BPR("Booking party reference number"),
  CER("Canadian Export Reporting System"),
  CR("Customer’s Reference"),
  CSI("Customer shipment ID"),
  DUE("Declaração Única de Exportação"),
  ECR("Empty container release reference"),
  EQ("Equipment Reference"),
  FF("Freight Forwarder’s Reference"),
  PO("Purchase Order Reference"),
  RUC("Registro Único del Contribuyente"),
  SI("Shipper’s Reference");

  private final String valueDescription;
}
