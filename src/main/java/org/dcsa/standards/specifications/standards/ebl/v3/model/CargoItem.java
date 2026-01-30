package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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

  @Schema(description = "A list of `National Commodity Codes` that apply to this `cargoItem`")
  protected List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Gross weight of the cargo.")
  protected CargoGrossWeight cargoGrossWeight;

  @Schema(description = "Gross volume of the cargo.")
  protected CargoGrossVolume cargoGrossVolume;

  @Schema(description = "Net weight of the cargo.")
  protected CargoNetWeight cargoNetWeight;

  @Schema(description = "Net volume of the cargo.")
  protected CargoNetVolume cargoNetVolume;
}
