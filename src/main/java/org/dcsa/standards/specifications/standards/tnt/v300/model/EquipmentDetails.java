package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v104.model.Seal;
import org.dcsa.standards.specifications.standards.core.v104.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.core.v104.types.IsoEquipmentCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.EmptyIndicatorCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.TransportPhase;

@Schema(description = "Equipment-specific details")
@Data
public class EquipmentDetails {

  @Schema() private EquipmentReference equipmentReference;

  @Schema(name = "ISOEquipmentCode")
  private IsoEquipmentCode isoEquipmentCode;

  @Schema() private EmptyIndicatorCode emptyIndicatorCode;

  @Schema() private TransportPhase transportPhase;

  @Schema() private List<Seal> seals;
}
