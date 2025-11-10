package org.dcsa.standards.specifications.standards.core.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
Seal-related information associated with the shipment equipment.
A seal is put on a shipment equipment once it is loaded.
This seal is meant to stay on until the shipment equipment reaches its final destination.
""")
@Data
public class Seal {

  @Schema(
      description = "Identifies a seal affixed to the container.",
      example = "VET123",
      maxLength = 15)
  protected String number;

  @Schema(
      description =
"""
The source of the seal, namely who has affixed the seal.
- `CAR` (Carrier)
- `SHI` (Shipper)
- `PHY` (Phytosanitary)
- `VET` (Veterinary)
- `CUS` (Customs)

In [UN/EDIFACT 16A 9303](https://unece.org/fileadmin/DAM/trade/untdid/d16a/tred/tred9303.htm),
both VET (Veterinary) and PHY (Phytosanitary) map to AC (Quarantine agency).
""",
      example = "CUS")
  protected String source;

  @Schema(
    description =
"""
The type of seal.
- `KLP` (Keyless padlock)
- `BLT` (Bolt)
- `WIR` (Wire)
""",
    example = "WIR")
  protected String type;
}
