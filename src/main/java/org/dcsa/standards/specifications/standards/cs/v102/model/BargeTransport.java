package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "Transport mode Barge.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BargeTransport
    extends org.dcsa.standards.specifications.standards.cs.v100.model.BargeTransport {

  @Schema(
      deprecated = true,
      description =
"""
The unique reference for a transport call. It's the vessel operator's responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.

**Deprecated:** Please use the `transportCallReference` (in **Departure** object) or `transportCallReference` (in **Arrival** object) in order to specify what the **TransportCall** is linked to. If provided in either `Arrival` or `Departure` object - this property must be ignored.
""",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLAMS-ACT-1-1")
  private String transportCallReference;
}
