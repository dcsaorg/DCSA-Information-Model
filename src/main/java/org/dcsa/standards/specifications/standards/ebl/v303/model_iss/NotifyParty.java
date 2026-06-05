package org.dcsa.standards.specifications.standards.ebl.v303.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3.model.IdentifyingCode;

@Schema(
    description =
"""
The person to be notified when a shipment arrives at its destination.

**Condition:** If a `displayedAddress` is provided, it must be included in the `Transport Document` instead of the `address` or `addressLines`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyParty
    extends org.dcsa.standards.specifications.standards.ebl.v303.model_td.NotifyParty {

  @Schema private List<IdentifyingCode> identifyingCodes;
}
