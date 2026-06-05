package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v104.types.AddressLine;

@Schema(
    description =
"""
The buyer is the last known entity to whom the goods are sold or agreed to be sold. If the goods are to be imported otherwise than in pursuance of a purchase, the details of the owner of the goods shall be provided.

**Condition:** Buyer and Seller are mandatory if `isCargoDeliveredInICS2Zone=true` and `manifestTypeCode='ENS'` and `advancedManifestFilingPerformedBy='CARRIER'` and `isHouseBillOfLadingsIssued=false`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Buyer extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.Buyer {

  @Schema(
      description =
          "Unstructured address lines, used as a fallback when structured address fields are unavailable.")
  @ArraySchema(maxItems = 5)
  protected List<AddressLine> addressLines;
}
