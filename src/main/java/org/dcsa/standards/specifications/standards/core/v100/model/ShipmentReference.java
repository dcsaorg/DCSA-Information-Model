package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.types.ShipmentReferenceTypeCode;

@Data
@Schema(
    description =
"""
References provided by the shipper or freight forwarder at the time of booking or at the time of providing shipping instructions.
Carriers share it back when providing track and trace event updates, some are also printed on the B/L.
Customers can use these references to track shipments in their internal systems.

In addition to the References provided by the shipper or freight forwarder,
the carrier can provide an extra type called `EQ` which is a reference to an Equipment.
""")
public class ShipmentReference {

  @Schema() private ShipmentReferenceTypeCode typeCode;

  @Schema(description = "The actual reference value", example = "123e4567e89b", maxLength = 100)
  private String reference;
}
