package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = CargoGrossWeight.CLASS_SCHEMA_DESCRIPTION)
@Data
public class CargoGrossWeight {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The estimated grand total gross weight of the cargo, including packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.\n\n**Condition:** Mandatory if not provided on `Requested Equipment` level.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The estimated grand total gross weight of the cargo, including packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.",
      example = "36000",
      minimum = "0",
      exclusiveMinimum = true,
      format = "float")
  private Double value;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unit of measure which can be expressed in imperial or metric terms:
- `KGM` (Kilograms)
- `LBR` (Pounds)
""",
      example = "KGM",
      allowableValues = {"KGM", "LBR"})
  private String unit;
}
