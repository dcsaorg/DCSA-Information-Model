package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.Reference.CLASS_SCHEMA_DESCRIPTION)
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
- `ECR` (Empty container release reference)
- `AKG` (Vehicle Identification Number)
- `AEF` (Customer Load Reference)
""",
    example = "CR",
    maxLength = 3)
  protected String type;
}
