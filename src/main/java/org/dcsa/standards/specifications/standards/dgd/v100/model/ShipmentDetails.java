package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DG declaration details specific to the shipment")
@Data
public class ShipmentDetails {

  @Schema(
      description = "The reference of the booking for which this DG declaration was created",
      example = "ABC709951",
      maxLength = 35)
  private String carrierBookingReference;

  @Schema(
      description =
          "The reference of the transport document for which this DG declaration was created",
      example = "HHL71800000",
      maxLength = 20)
  private String transportDocumentReference;

  @Schema(
      description =
          "The shipper's own internal reference number for cross-referencing with their systems.",
      example = "SHP-2026-NL-8821",
      maxLength = 100)
  private String shipperReference;

  @Schema(
      description =
          "The reference number assigned by the freight forwarder handling the shipment on behalf of the shipper.",
      example = "FF-20260401-003",
      maxLength = 100)
  private String freightForwarderReference;
}
