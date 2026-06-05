package org.dcsa.standards.specifications.standards.ebl.v303.model_sur;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v302.model_sur.EndorsementChainLink;

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
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class SurrenderRequestDetails
    extends org.dcsa.standards.specifications.standards.ebl.v302.model_sur.SurrenderRequestDetails {

  @Schema(
      name = "endorsementChain",
      description =
"""
A list of one or more actions that affect an eBL, starting from its issuance onward. It is equivalent to the "back side" of a physical Bill of Lading. The type of actions recorded in the endorsement chain as defined by the DCSA standard are listed below:

- **Issue:** The act of issuing an eBL to the `Issue to` party, meaning the designated recipient of the action (typically the shipper or their on behalf of party).
- **Endorse:** The act of transferring the rights and obligations associated with the eBL to a new endorsee, meaning the designated recipient of the action, allowing them to claim or deal with the goods. Only applicable to negotiable eBL (`isToOrder=true`).
- **Sign:** The act of visibly marking the actor's possession of the eBL within the chain. This action has no designated recipient and can only be performed while the actor is the current possessor of the eBL, similar to how a party may sign a physical Bill of Lading while in their possession.
- **Request Surrender for Amendment:** The act of surrendering an eBL so that the carrier can issue an amended version. If the request is accepted, the original eBL is voided, and the amended eBL must be reissued with a new endorsement chain. This action is also used when switching the eBL to a physical document (“switch to paper”), which is treated as part of the amendment process in the DCSA standard.
- **Request Surrender for Delivery:** The act of surrendering an eBL to the carrier to request delivery of the goods.
- **Blank Endorse:** The act of transferring the rights and obligations associated with the eBL in blank, meaning that the endorsement does not specify a recipient. A party in possession of a blank endorsed eBL is allowed to claim or deal with the goods. Only applicable to negotiable eBL (`isToOrder=true`).
- **Endorse to Order:** The act of transferring the rights and obligations associated with the eBL to a new endorsee, meaning the designated recipient of the action, allowing them to claim or deal with the goods. The newly appointed endorsee can further endorse the eBL to another party. Only applicable to negotiable eBL (`isToOrder=true`).
- **Transfer:** The act of transferring the possession of the eBL to the recipient.
- **Surrendered:** The act of confirming that the eBL has been surrendered, meaning that its lifecycle is completed.
""")
  private List<EndorsementChainLink> renamed_endorsementChain;
}
