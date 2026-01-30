package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.model;

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
The volume of the referenced dangerous goods. Only applicable to liquids and gas.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NetVolume extends Volume {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The volume of the referenced dangerous goods.",
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
- `LTR` (Litre)
""",
      example = "MTQ")
  protected String unit;
}
