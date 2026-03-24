package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    example = "BOOKING",
    maxLength = 100,
    description = "Code used to denote the type of a document reference")
@AllArgsConstructor
public enum DocumentReferenceType implements EnumBase {
  ADVANCE_MANIFEST_FILING("Advance Manifest Filing (of any subtype)"),
  ARRIVAL_NOTICE("Arrival Notice"),
  BOOKING("Booking"),
  CARGO_SURVEY("Cargo Survey"),
  CARRIER_BOOKING_REQUEST("Carrier Booking Request"),
  CERTIFICATE_OF_ANALYSIS("Certificate of Analysis"),
  CERTIFICATE_OF_ORIGIN("Certificate of Origin"),
  CONTAINER_RELEASE_ORDER("Container Release Order"),
  CONTRACT_QUOTATION("Contract Quotation"),
  CUSTOMS_CLEARANCE("Customs Clearance"),
  DANGEROUS_GOODS_DECLARATION("Dangerous Goods Declaration"),
  DELIVERY_INSTRUCTIONS("Delivery Instructions"),
  DELIVERY_ORDER("Delivery Order"),
  FUMIGATION_CERTIFICATE("Fumigation Certificate"),
  HEALTH_CERTIFICATE("Health Certificate"),
  INSPECTION_CERTIFICATE("Inspection Certificate"),
  INVOICE("Invoice"),
  OUT_OF_GAUGE_DECLARATION("Out of Gauge Declaration"),
  PHYTOSANITARY_CERTIFICATE("Phytosanitary Certificate"),
  PROOF_OF_DELIVERY("Proof of Delivery"),
  SHIPPING_INSTRUCTIONS("Shipping Instruction"),
  TRANSPORT_DOCUMENT("Transport Document"),
  TRANSPORT_ORDER("Transport Order"),
  VERIFIED_GROSS_MASS("Verified Gross Mass"),
  VETERINARY_CERTIFICATE("Veterinary Certificate");

  private final String valueDescription;
}
