package org.dcsa.standards.specifications.standards.ebl.v301.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model_sur.EndorsementChainLink;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_sur.SurrenderRequestDetails
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class SurrenderRequestDetails
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_sur.SurrenderRequestDetails {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A unique number allocated by the shipping line to the `Transport Document` and the main number used for the tracking of the status of the shipment.",
      example = "HHL71800000",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 20)
  private String transportDocumentReference;

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
""",
      example = "SWTP",
      maxLength = 4)
  private String reasonCode;

  @Schema(
      description =
"""
A list of one or more endorsement related actions that were performed on or after the issuance of the eBL. It is equivalent to the "back side" of the physical bill of lading. The type of actions recorded in the endorsement chain as defined by the DCSA standard are listed below:

 - **Issue:** The act of issuing an eBL i.e. making the eBL available to the receiver.
 - **Endorse:** The act of transferring the rights and obligations associated with the eBL to a specific named party, allowing them to claim or deal with the goods. The user in control of the eBL may endorse the eBL in their turn to another named party. Only applicable to To-Order eBL (`isToOrder=true`).
 - **Sign:** A general-purpose signature that can be used by any party to mark their possession of the eBL. Similar to how any possessor in the physical world can put a physical signature on the paper bill of lading. The endorsement chain as defined by DCSA does not record any transfer of possession of the eBL, unless a signature is added to it.
 - **Request Surrender for Amendment:** The presentation (by transfer) of the eBL to the Issuer, or another user appointed by the Issuer, by a user entitled to do so for the purpose of amending the eBL.
 - **Request Surrender for Delivery:** The presentation (by transfer) of the eBL to the Issuer, or another user appointed by the Issuer, by a user entitled to do so for the purpose of claiming delivery of the goods.

 **Note:** DCSA member carriers have agreed that the name `endorsementChain` is still the correct name for this list of actions.
""")
  private List<EndorsementChainLink> endorsementChain;
}
