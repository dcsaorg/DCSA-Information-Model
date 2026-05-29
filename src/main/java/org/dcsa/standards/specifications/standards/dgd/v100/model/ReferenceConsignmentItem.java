package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "A reference linked to a consignment item.")
@Data
public class ReferenceConsignmentItem {

  @Schema(
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
  private String type;

  @Schema(description = "List of `referenceValues` for a given `referenceType`.")
  private List<String> values;
}
