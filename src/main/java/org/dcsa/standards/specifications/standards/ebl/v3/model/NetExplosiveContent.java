package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = "The total weight of the explosive substances, without the packaging, casings, etc.")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NetExplosiveContent extends Weight {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The total weight of the explosive substances, without the packaging’s, casings, etc.
""",
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
- `GRM` (Grams)
- `ONZ` (Ounce)
""",
      example = "KGM")
  protected String unit;
}
