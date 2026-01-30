package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = "Total weight of the goods carried, excluding packaging.")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NetWeight extends Weight {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Total weight of the goods carried, excluding packaging.",
      example = "2.4",
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
      example = "KGM")
  protected String unit;
}
