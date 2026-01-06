package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.Limits.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class Limits extends org.dcsa.standards.specifications.standards.dt.v100.model.Limits {

  @Schema(
      description =
"""
The unit for **all attributes in the limits structure** in Celsius or Fahrenheit

- `CEL` (Celsius)
- `FAH` (Fahrenheit)
""",
      example = "CEL",
      maxLength = 10)
  protected String temperatureUnit;

  @Schema(
      description =
"""
Lowest temperature at which a chemical can vaporize to form an ignitable mixture in air.
""",
      example = "42.0",
      format = "float")
  protected Double flashPoint;
}
