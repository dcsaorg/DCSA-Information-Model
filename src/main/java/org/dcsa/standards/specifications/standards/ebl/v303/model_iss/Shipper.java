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
The party by whom or in whose name or on whose behalf a contract of carriage of goods by sea has been concluded with a carrier, or the party by whom or in whose name, or on whose behalf, the goods are actually delivered to the carrier in relation to the contract of carriage by sea.

**Condition:** If a `displayedAddress` is provided, it must be included in the `Transport Document` instead of the `address` or `addressLines`.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Shipper extends org.dcsa.standards.specifications.standards.ebl.v303.model_td.Shipper {

  @Schema private List<IdentifyingCode> identifyingCodes;
}
