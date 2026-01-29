package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(
    description =
        """
A concrete surrender request related to a transport document.

The platform guarantees *all* of the following:

  1) The surrender request was submitted by the sole possessor of the eBL.
  2) Depending on the eBL type:
   * For non-negotiable ("straight") eBLs, the surrender request was submitted by either the original shipper OR the consignee.
   * For negotiable eBLs with a named titleholder, the surrender request was submitted by the named titleholder.
   * For negotiable eBLs without a named titleholder / blank eBLs, possession is sufficient for the entity surrendering the eBL.
  3) The carrier holds possession of the eBL with this surrender request  until it responds to this surrender request.
""",
    title = "Surrender Request Details")
@Data
public class SurrenderRequestDetails {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A server defined reference for a concrete surrender request. Surrender request references MUST NOT be reused.",
      example = "Z12345",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 100)
  private String surrenderRequestReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the shipping line to the `Transport Document` and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 20)
  private String transportDocumentReference;

  @Schema(
      description =
          "Additional reference that can be optionally used alongside the `transportDocumentReference` in order to distinguish between versions of the same `Transport Document`.",
      example = "Version_1",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 100)
  private String transportDocumentSubReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          """
Surrender Request codes:
- `SREQ` (Requested to Surrender for Delivery)
- `AREQ` (Requested to Surrender for Amendment)

The surrender request code determines the type of surrender request. Any parallel negotiation between the consignee and the carrier related to any of these type surrender are handled outside this API. Examples could be the shipment release related to a Surrender for Delivery or the actual contents of the amendment in a surrender related to an amendment.

Note that "Switch to paper" is considered an amendment in how it is modelled via the DCSA eBL data standard.
""",
      example = "SREQ",
      allowableValues = {"SREQ", "AREQ"})
  private String surrenderRequestCode;

  @Schema(
      description =
          """
A code defined by DCSA indicating the reason for requesting a Surrender for Amendment. Possible values are:
- `SWTP` (Switch to paper)
- `COD` (Change of destination)
- `SWI` (Switch BL)

In case no valid `reasonCode` exists for the `AREQ` (Requested to Surrender for Amendment) - it is possible to use the `comments` property below to add more information.
""",
      example = "SWTP",
      maxLength = 4)
  private String reasonCode;

  @Schema(
      description =
          "Optional free text comment associated with the surrender request transaction. In case no valid `reasonCode` exists - this property can also be used to provide a reason.",
      example = "As requested...",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 255)
  private String comments;

  @Schema(
      description =
          """
A list of one or more actions that affect an eBL, starting from its issuance onward. It is equivalent to the "back side" of a physical Bill of Lading. The type of actions recorded in the endorsement chain as defined by the DCSA standard are listed below:

- **Issue:** The act of issuing an eBL to the `Issue to` party, meaning the designated recipient of the action (typically the shipper or their on behalf of party).
- **Endorse:** The act of transferring the rights and obligations associated with the eBL to a new endorsee, meaning the designated recipient of the action, allowing them to claim or deal with the goods. The newly appointed endorsee may **NOT** further endorse the eBL to another party. Only applicable to negotiable eBL (`isToOrder=true`).
- **Sign:** The act of visibly marking the actor's possession of the eBL within the chain. This action has no designated recipient and can only be performed while the actor is the current possessor of the eBL, similar to how a party may sign a physical Bill of Lading while in their possession.
- **Request Surrender for Amendment:** The act of surrendering an eBL so that the carrier can issue an amended version. If the request is accepted, the original eBL is voided, and the amended eBL must be reissued with a new endorsement chain. This action is also used when switching the eBL to a physical document (“switch to paper”), which is treated as part of the amendment process in the DCSA standard.
- **Request Surrender for Delivery:** The act of surrendering an eBL to the carrier to request delivery of the goods.
- **Blank Endorse:** The act of transferring the rights and obligations associated with the eBL in blank, meaning that the endorsement does not specify a recipient. A party in possession of a blank endorsed eBL is allowed to claim or deal with the goods. Only applicable to negotiable eBL (`isToOrder=true`).
- **Endorse to Order:** The act of transferring the rights and obligations associated with the eBL to a new endorsee, meaning the designated recipient of the action, allowing them to claim or deal with the goods. The newly appointed endorsee can further endorse the eBL to another party. Only applicable to negotiable eBL (`isToOrder=true`).
- **Transfer:** The act of transferring the possession of the eBL to the recipient.
- **Surrendered:** The act of confirming that the eBL has been surrendered, meaning that its lifecycle is completed.

**Note:** DCSA member carriers have agreed that the name `endorsementChain` is still the correct name for this list of actions.
""")
  private List<EndorsementChainLink> endorsementChain;
}
