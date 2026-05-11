package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.Reference
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Reference extends org.dcsa.standards.specifications.standards.dt.v100.model.Reference {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The reference type codes defined by DCSA. Possible values are:
- `CR` (Customer's Reference)
- `AKG` (Vehicle Identification Number)
""",
      example = "CR",
      maxLength = 3)
  protected String type;
}
