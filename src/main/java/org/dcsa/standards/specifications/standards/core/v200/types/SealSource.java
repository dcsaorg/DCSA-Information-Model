package org.dcsa.standards.specifications.standards.core.v200.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dcsa.standards.specifications.generator.EnumBase;

@Getter
@Schema(
    type = "string",
    maxLength = 100,
    example = "CUSTOMER",
    description =
"""
The source of a seal, indicating who has affixed the seal.

Both `VETERINARY` and `PHYTOSANITARY` map to `AC` (Quarantine agency) in [UN/EDIFACT 16A 9303](https://unece.org/fileadmin/DAM/trade/untdid/d16a/tred/tred9303.htm).
""")
@AllArgsConstructor
public enum SealSource implements EnumBase {
  CARRIER("Carrier"),
  CONSOLIDATOR("Consolidator"),
  CUSTOMER("Customer"),
  CUSTOMS("Customs"),
  PHYTOSANITARY("Phytosanitary"),
  SHIPPER("Shipper"),
  TERMINAL("Terminal"),
  VETERINARY("Veterinary");

  private final String valueDescription;
}
