package org.dcsa.standards.specifications.standards.ebl.v301.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
The seller is the last known entity by whom the goods are sold or agreed to be sold to the buyer. If the goods are to be imported otherwise than in pursuance of a purchase, the details of the owner of the goods shall be provided.

**Condition:** Buyer and Seller are mandatory if `isCargoDeliveredInICS2Zone=true` (on House B/L level) and `manifestTypeCode='ENS'` and `advancedManifestFilingPerformedBy='CARRIER'`.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SellerHBL extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.SellerHBL {}
