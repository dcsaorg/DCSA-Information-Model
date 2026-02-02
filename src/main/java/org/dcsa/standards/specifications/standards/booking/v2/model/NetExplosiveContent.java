package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = "The weight of the cargo item excluding packaging and container tare weight.")
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
      example = "2400",
      minimum = "0",
      exclusiveMinimum = true,
      format = "float")
  protected Double value;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Unit of measure used to describe the `netExplosiveWeight`. Possible values are:
- `KGM` (Kilograms)
- `LBR` (Pounds)
- `GRM` (Grams)
- `ONZ` (Ounce)
""",
      example = "KGM",
      enumAsRef = true)
  protected String unit;
}
