package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description = "Reference used by the customer or carrier to identify or track the shipment.")
@Data
public class Reference {

  @Schema(
      description =
"""
The reference type codes defined by DCSA. Possible values are:
- `CA` (Carrier's Reference)
- `CR` (Customer's Reference)
- `AKG` (Vehicle Identification Number)
- `CPR` (Canadian Pacific Railway System)
- `CNR` (Canadian National Railway Unit)
- `VBN` (V-Bond Number)
""",
      example = "CR",
      maxLength = 3)
  private String type;

  @Schema(description = "The value of the reference.", example = "HHL00103004", maxLength = 100)
  private String value;
}
