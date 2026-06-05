package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.dt.v100.model.Volume;

@Schema(
    description =
"""
The estimated net total volume of the cargo.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CargoNetVolume extends Volume {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The estimated net total volume of the cargo.
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
- `FTQ` (Cubic foot)
- `MTQ` (Cubic meter)
""",
      example = "MTQ",
      enumAsRef = true)
  protected String unit;
}
