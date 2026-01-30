package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = "The weight of the cargo item including packaging, excluding the tare weight of the container.")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CargoGrossWeight extends Weight {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The weight of the cargo item including packaging being carried in the container. Excludes the tare weight of the container. A maximum of 3 decimals should be provided.
""",
      example = "2400",
      minimum = "0",
      exclusiveMinimum = true,
      format = "float")
  protected Double value;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unit of measure which can be expressed in imperial or metric terms:
- `KGM` (Kilograms)
- `LBR` (Pounds)
""",
      example = "KGM",
      enumAsRef = true)
  protected String unit;
}
