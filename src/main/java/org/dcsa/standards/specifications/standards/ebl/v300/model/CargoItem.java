package org.dcsa.standards.specifications.standards.ebl.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.CargoItem
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CargoItem
    extends org.dcsa.standards.specifications.standards.dt.v100.model.CargoItem {

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Gross weight of the cargo.")
  protected CargoGrossWeight cargoGrossWeight;

  @Schema(description = "Gross volume of the cargo.")
  protected CargoGrossVolume cargoGrossVolume;

  @Schema(description = "Net weight of the cargo.")
  protected CargoNetWeight cargoNetWeight;

  @Schema(description = "Net volume of the cargo.")
  protected CargoNetVolume cargoNetVolume;
}
