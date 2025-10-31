package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.model.DocumentReference;
import org.dcsa.standards.specifications.standards.core.v100.types.FormattedDate;

@Schema(description = "Shipment-specific details")
@Data
public class ShipmentDetails {

  @Schema() private DocumentReference documentReference;

  @Schema(description = "The reason this event was sent")
  private String reason;

  @Schema(
      description =
"""
Date taken in consideration for the tariff applicability, that differs depending on the trade.
It can vary per carrier.
""")
  private FormattedDate priceCalculationDate;
}
