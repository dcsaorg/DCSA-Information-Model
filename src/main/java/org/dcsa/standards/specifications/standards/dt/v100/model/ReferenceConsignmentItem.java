package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.types.ReferenceConsignmentItemValue;

@Schema(description = ReferenceConsignmentItem.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ReferenceConsignmentItem {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "References for a consignment item, used by shippers or freight forwarders and shared in events or printed on documents.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The reference type codes defined by DCSA. Possible values are:
- `CR` (Customer’s Reference)
- `AKG` (Vehicle Identification Number)
- `SPO` (Shipper's Purchase Order)
- `CPO` (Consignee's Purchase Order)
""",
      example = "CR",
      maxLength = 3)
  protected String type;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "List of `referenceValues` for a given `referenceType`.")
  @ArraySchema(minItems = 1)
  protected List<ReferenceConsignmentItemValue> values;
}
