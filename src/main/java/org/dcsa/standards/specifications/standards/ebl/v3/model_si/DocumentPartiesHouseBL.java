package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description =
"""
All `Parties` with associated roles for this `House Bill of Lading`.

**Condition:** `Buyer` and `Seller` are mandatory if `isCargoDeliveredInICS2Zone=true` (on House B/L level) **and** `advancedManifestFilingPerformedBy=CARRIER`
""",
    title = "Document Parties (House B/L)")
@Data
public class DocumentPartiesHouseBL {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private ShipperHBL shipper;

  @Schema
  private ConsigneeHBL consignee;

  @Schema
  private NotifyPartyHBL notifyParty;

  @Schema
  private SellerHBL seller;

  @Schema
  private BuyerHBL buyer;

  @Schema(description = "A list of document parties that can be optionally provided in the `Shipping Instructions` and `Transport Document`.")
  private List<OtherDocumentPartyHBL> other;
}
