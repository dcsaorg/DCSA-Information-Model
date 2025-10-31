package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(
    description =
"""
Object used to indicate that one or more shipment elements were replaced by one or more other elements.

Used in scenarios including the replacement of shipment references or equipments.
""")
public class ShipmentReferenceReplacement {

  @Schema(
      description =
"""
References of the shipment elements or equipments replaced by those with the `newReferences`.
""")
  private List<ShipmentReference> oldReferences;

  @Schema(
      description =
"""
References of the shipment elements or equipments replacing those with the `oldReferences`.
""")
  private List<ShipmentReference> newReferences;
}
