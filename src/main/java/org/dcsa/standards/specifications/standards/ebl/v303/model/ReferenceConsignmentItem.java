package org.dcsa.standards.specifications.standards.ebl.v303.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v101.model.ReferenceConsignmentItem
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ReferenceConsignmentItem
    extends org.dcsa.standards.specifications.standards.dt.v101.model.ReferenceConsignmentItem {

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
"""
The reference type codes defined by DCSA. Possible values are:
- `CR` (Customer's Reference)
- `AKG` (Vehicle Identification Number)
- `SPO` (Shipper's Purchase Order)
- `CPO` (Consignee's Purchase Order)
""",
    example = "CR",
    maxLength = 3)
  protected String type;
}
