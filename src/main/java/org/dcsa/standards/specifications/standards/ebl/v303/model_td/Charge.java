package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v101.model.Charge.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Charge extends org.dcsa.standards.specifications.standards.dt.v101.model.Charge {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Free text field describing the charge to apply.

**DEPRECATED:** Because of backward compatibility it is required to provide a value in this property even though a value is provided in the `extendedChargeName` property. In case both properties are provided (`extendedChargeName` and `chargeName`) - `chargeName` must be ignored.
""",
      example = "Documentation fee - Destination",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  protected String chargeName;

  @Schema(
      description =
"""
Extended Free text field, now allowing up to 100 characters, describing the charge to apply.

**Note:** If `extendedChargeName` is provided - it always takes precedence over `chargeName` (which is a required property but must be ignored).
""",
      example = "Special documentation fee - Destination",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
  protected String extendedChargeName;
}
