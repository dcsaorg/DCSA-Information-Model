package org.dcsa.standards.specifications.standards.ebl.v3.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(
    description =
      SurrenderRequestDetails.CLASS_SCHEMA_DESCRIPTION,
    title = "Surrender Request Details")
@Data
public class SurrenderRequestDetails {

  public static final String CLASS_SCHEMA_DESCRIPTION = """
A concrete surrender request related to a transport document.

The platform guarantees *all* of the following:

  1) The surrender request was submitted by the sole possessor of the eBL.
  2) Depending on the eBL type:
   * For non-negotiable ("straight") eBLs, the surrender request was submitted by either the original shipper OR the consignee.
   * For negotiable eBLs with a named titleholder, the surrender request was submitted by the named titleholder.
   * For negotiable eBLs without a named titleholder / blank eBLs, possession is sufficient for the entity surrendering the eBL.
  3) The carrier holds possession of the eBL with this surrender request  until it responds to this surrender request.
""";

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
          "A unique number allocated by the shipping line to the transport document and the main number used for the tracking of the status of the shipment.",
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
- `SREQ` (Requested to surrender for Delivery)
- `AREQ` (Requested to surrender for Amendment)

The surrender request code determines the type of surrender request. Any parallel negotiation between the consignee and the carrier related to any of these type surrender are handled outside this API. Examples could be the shipment release related to a surrender for delivery or the actual contents of the amendment in a surrender related to an amendment.

Note that "Switch to paper" is considered an amendment in how it is modelled via the DCSA eBL data standard.
""",
      example = "SREQ",
      allowableValues = {"SREQ", "AREQ"})
  private String surrenderRequestCode;

  @Schema(
      description =
          """
A code defined by DCSA indicating the reason for requesting a surrender for amendment. Possible values are:
-	`SWTP` (Switch to paper)
""",
      example = "SWTP",
      maxLength = 4)
  private String reasonCode;

  @Schema(
      description =
          "Optional free text comment associated with the surrender request transaction.",
      example = "As requested...",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 255)
  private String comments;

  @Schema(
      description =
          """
A list of one or more endorsement related actions that were performed on or after the issuance of the eBL. It is equivalent to the "back side" of the physical bill of lading. The type of actions recorded in the endorsement chain as defined by the DCSA standard are listed below:

 - **Issue:** The act of issuing an eBL i.e. making the eBL available to the receiver.
 - **Endorse:** The act of transferring the rights and obligations associated with the eBL to a specific named party, allowing them to claim or deal with the goods. The user in control of the eBL may endorse the eBL in their turn to another named party. Only applicable to To-Order eBL (`isToOrder=true`).
 - **Sign:** A general-purpose signature that can be used by any party to mark their possession of the eBL. Similar to how any possessor in the physical world can put a physical signature on the paper bill of lading. The endorsement chain as defined by DCSA does not record any transfer of possession of the eBL, unless a signature is added to it.
 - **Request surrender for amendment:** The presentation (by transfer) of the eBL to the Issuer, or another user appointed by the Issuer, by a user entitled to do so for the purpose of amending the eBL.
 - **Request surrender for delivery:** The presentation (by transfer) of the eBL to the Issuer, or another user appointed by the Issuer, by a user entitled to do so for the purpose of claiming delivery of the goods.

 **Note:** DCSA member carriers have agreed that the name `endorsementChain` is still the correct name for this list of actions.
""")
  private List<EndorsementChainLink> endorsementChain;
}
