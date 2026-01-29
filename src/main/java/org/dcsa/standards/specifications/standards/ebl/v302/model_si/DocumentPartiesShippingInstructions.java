package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v302.model.*;
import org.dcsa.standards.specifications.standards.ebl.v302.model_td.OnBehalfOfConsignee;

@Schema(
    description =
"""
All `Parties` with associated roles.

**Condition:** `Buyer` and `Seller` are mandatory if `isCargoDeliveredInICS2Zone=true` **and** `advancedManifestFilingPerformedBy=CARRIER` and `isHouseBillOfLadingsIssued=false`
""",
    title = "Document Parties (Shipping Instructions)")
@Data
public class DocumentPartiesShippingInstructions {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private Shipper shipper;

  @Schema
  private OnBehalfOfShipper onBehalfOfShipper;

  @Schema
  private ConsigneeShipper consignee;

  @Schema
  private OnBehalfOfConsignee onBehalfOfConsignee;

  @Schema
  private EndorseeShipper endorsee;

  @Schema
  private IssueToParty issueTo;

  @Schema
  private Seller seller;

  @Schema
  private Buyer buyer;

  @Schema(
      description =
"""
List of up to 3 `Notify Parties`. The first item in the list is the **First Notify Party** (`N1`), the second item is the **Second Notify Party** (`N2`) and the last item is the **Other Notify Party** (`NI`).

**Condition:** If provided:
  - Mandatory for To Order BLs, `isToOrder=true`
  - The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 3)
  private List<NotifyParty> notifyParties;

  @Schema
  private ShippingInstructionsRequestor shippingInstructionsRequestor;

  @Schema(description = "A list of document parties that can be optionally provided in the `Shipping Instructions` and `Transport Document`.")
  private List<OtherDocumentPartyShippingInstructions> other;
}
