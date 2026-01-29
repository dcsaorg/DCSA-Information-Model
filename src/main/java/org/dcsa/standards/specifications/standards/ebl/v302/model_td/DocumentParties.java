package org.dcsa.standards.specifications.standards.ebl.v302.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v302.model.*;

@Schema(description = DocumentParties.CLASS_SCHEMA_DESCRIPTION)
@Data
public class DocumentParties {

  public static final String CLASS_SCHEMA_DESCRIPTION = "All `Parties` with associated roles.";

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The Shipper party.")
  private Shipper shipper;

  @Schema(description = "The Consignee party.")
  private Consignee consignee;

  @Schema(description = "The Endorsee party.")
  private Endorsee endorsee;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The Issuing party responsible for signing the document.")
  private IssuingParty issuingParty;

  @Schema(description = "Carrier’s agent at the destination.")
  private CarriersAgentAtDestination carriersAgentAtDestination;

  @Schema(
      description =
"""
List of up to 3 `Notify Parties`. The first item in the list is the **First Notify Party** (`N1`), the second item is the **Second Notify Party** (`N2`) and the last item is the **Other Notify Party** (`NI`).

**Condition:** If provided:
  - Mandatory for To Order BLs, `isToOrder=true`
  - The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(
      schema =
          @Schema(
              description =
                  "List of up to 3 `Notify Parties`. The first item is the First Notify Party (`N1`), second is `N2`, third is `NI`. Mandatory if `isToOrder=true`. The order MUST be preserved."),
      maxItems = 3)
  private List<NotifyParty> notifyParties;

  @Schema(
      description =
"""
A list of document parties that can be optionally provided in the `Shipping Instructions` and `Transport Document`.
""")
  @ArraySchema(schema = @Schema(description = "Optional list of additional document parties."))
  private List<OtherDocumentParty> other;

  @Schema() private OnBehalfOfConsignee onBehalfOfConsignee;

  @Schema() private OnBehalfOfShipper onBehalfOfShipper;
}
