package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The buyer is the last known entity to whom the goods are sold or agreed to be sold. If the goods are to be imported otherwise than in pursuance of a purchase, the details of the owner of the goods shall be provided.

**Condition:** Buyer and Seller are mandatory if `isCargoDeliveredInICS2Zone=true` (on House B/L level) and `manifestTypeCode='ENS'` and `advancedManifestFilingPerformedBy='CARRIER'`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BuyerHBL extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.BuyerHBL {

  @Schema private AddressHBL address;
}
