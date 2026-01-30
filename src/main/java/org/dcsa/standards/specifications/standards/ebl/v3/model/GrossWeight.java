package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = "Total weight of the goods carried, including packaging.")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class GrossWeight extends Weight {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The grand total weight of the DG cargo and weight per `UNNumber`/`NANumber` including packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.
""",
      example = "12000.3",
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
