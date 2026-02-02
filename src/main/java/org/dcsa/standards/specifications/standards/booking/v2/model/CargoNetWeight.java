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
public class CargoNetWeight extends Weight {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The estimated grand total net weight of the cargo, excluding packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.
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
