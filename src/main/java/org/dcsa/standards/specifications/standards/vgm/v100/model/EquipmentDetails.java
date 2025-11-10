package org.dcsa.standards.specifications.standards.vgm.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;
import org.dcsa.standards.specifications.standards.core.v101.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v101.types.IsoEquipmentCode;
import org.dcsa.standards.specifications.standards.core.v101.model.Seal;

import java.util.List;

@Schema(description = "VGM declaration details specific to the equipment")
@Data
public class EquipmentDetails {

  @Schema() private EquipmentReference equipmentReference;

  @Schema(name = "ISOEquipmentCode")
  private IsoEquipmentCode isoEquipmentCode;

  @Schema(description = "Indicates whether the container is owned by the shipper (SOC)")
  private Boolean isShipperOwned;

  @Schema() private List<Seal> seals;

  @Schema(
      description =
"""
Gross weight of the cargo including packaging being carried in the container.
Excludes the tare weight of the container.
""")
  private Weight cargoGrossWeight;

  @Schema(description = "The tare weight of the container")
  private Weight tareWeight;
}
