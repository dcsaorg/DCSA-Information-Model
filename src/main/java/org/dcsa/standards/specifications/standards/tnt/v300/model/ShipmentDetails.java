package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.DocumentReference;
import org.dcsa.standards.specifications.standards.core.v101.model.DocumentReferenceReplacement;
import org.dcsa.standards.specifications.standards.core.v101.model.ShipmentReference;
import org.dcsa.standards.specifications.standards.core.v101.model.ShipmentReferenceReplacement;
import org.dcsa.standards.specifications.standards.core.v101.types.FormattedDate;
import org.dcsa.standards.specifications.standards.tnt.v300.types.ShipmentLocationTypeCode;

@Schema(description = "Shipment-specific details")
@Data
public class ShipmentDetails {

  @Schema(description = "Primary document reference associated with this event")
  private DocumentReference documentReference;

  @Schema(description = "List of additional document references related to this event")
  private List<DocumentReference> additionalDocumentReferences;

  @Schema(description = "List of document replacements related to this event")
  private List<DocumentReferenceReplacement> documentReferenceReplacements;

  @Schema(description = "List of shipment references related to this event")
  private List<ShipmentReference> shipmentReferences;

  @Schema(description = "List of shipment reference replacements related to this event")
  private List<ShipmentReferenceReplacement> shipmentReferenceReplacements;

  @Schema() private ShipmentLocationTypeCode shipmentLocationType;

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
