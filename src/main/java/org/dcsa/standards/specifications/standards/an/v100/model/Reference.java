package org.dcsa.standards.specifications.standards.an.v100.model;

import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description =
"""
Reference used by the customer or carrier to identify or track the shipment.
""")
@ClearSchemaConstraints
public class Reference
    extends org.dcsa.standards.specifications.standards.dt.v100.model.Reference {

  @Schema(
      description =
"""
The reference type codes defined by DCSA. Possible values are:
- `CA` (Carrier’s Reference)
- `CR` (Customer’s Reference)
- `AKG` (Vehicle Identification Number)
- `CPR` (Canadian Pacific Railway System)
- `CNR` (Canadian National Railway Unit)
- `VBN` (V-Bond Number)
""",
      example = "CR",
      maxLength = 3)
  protected String type;
}
