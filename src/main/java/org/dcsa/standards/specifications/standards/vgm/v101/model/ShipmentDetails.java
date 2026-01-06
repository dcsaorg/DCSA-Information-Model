package org.dcsa.standards.specifications.standards.vgm.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "VGM declaration details specific to the shipment")
@Data
public class ShipmentDetails {
  @Schema(
      example = "ABC709951",
      description = "The reference of the booking for which this VGM declaration was created",
      maxLength = 35)
  private String carrierBookingReference;

  @Schema(
      example = "HHL71800000",
      description = "The reference of the transport document for which this VGM declaration was created",
      maxLength = 20)
  private String transportDocumentReference;

  @Schema(
      description =
"""
List of additional references related to the VGM declaration,
excluding the Carrier Booking Reference and the Transport Document Reference
""")
  private List<OtherReference> otherReferences;
}
