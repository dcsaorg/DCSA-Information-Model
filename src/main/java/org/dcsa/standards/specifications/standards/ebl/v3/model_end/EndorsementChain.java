package org.dcsa.standards.specifications.standards.ebl.v3.model_end;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(
    description =
        "An endorsement Chain linked to a particular `transportDocumentReference` and potentially a `transportDocumentSubReference`.",
    title = "Endorsement Chain")
@Data
public class EndorsementChain {

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
      description =
          "The carrier code, based on SCAC code (provided by [NMFTA](https://nmfta.org/scac/)), that Issued the `transportDocumentReference`.",
      example = "MAEU",
      pattern = "^\\S+$",
      maxLength = 4)
  private String carrierSCACCode;

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
