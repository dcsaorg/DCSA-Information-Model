package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Seal;

@Schema(description = "Transport equipment being used.")
@Data
public class UtilizedTransportEquipment {

  @Schema(description = "The container or transport equipment used.")
  private Equipment equipment;

  @Schema(description = "Seal-related information associated with the equipment.")
  private List<Seal> seals;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `UtilizedTransportEquipment`
""")
  private List<String> shippingMarks;

  @Schema(
      description = "Reference used by the customer or carrier to identify or track the shipment.")
  private List<Reference> references;
}
