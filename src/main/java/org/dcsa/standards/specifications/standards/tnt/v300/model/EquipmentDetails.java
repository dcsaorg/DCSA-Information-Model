package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Seal;
import org.dcsa.standards.specifications.standards.core.v103.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v103.types.IsoEquipmentCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.EmptyIndicatorCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.MovementTypeCode;

@Schema(description = "Equipment-specific details")
@Data
public class EquipmentDetails {

  @Schema() private EquipmentReference equipmentReference;

  @Schema(name = "ISOEquipmentCode")
  private IsoEquipmentCode isoEquipmentCode;

  @Schema() private EmptyIndicatorCode emptyIndicatorCode;

  @Schema() private MovementTypeCode movementType;

  @Schema() private List<Seal> seals;
}
