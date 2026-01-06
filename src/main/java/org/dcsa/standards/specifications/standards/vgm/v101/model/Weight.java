package org.dcsa.standards.specifications.standards.vgm.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.core.v103.model.Weight
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class Weight extends org.dcsa.standards.specifications.standards.core.v103.model.Weight {

  @Schema(
      description =
"""
Measurement unit in which the weight `value` is expressed:
- `KGM` (Kilograms)
- `LBR` (Pounds)
""",
      example = "KGM",
      maxLength = 10)
  protected String unit;
}
