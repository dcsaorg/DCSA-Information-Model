package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.dcsa.standards.specifications.standards.an.v100.types.HSCode;
import org.dcsa.standards.specifications.standards.dt.v100.types.DescriptionOfGoodsLine;
import org.dcsa.standards.specifications.standards.dt.v100.types.ShippingMark;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.ConsignmentItem
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class ConsignmentItem
    extends org.dcsa.standards.specifications.standards.dt.v100.model.ConsignmentItem {

  @Schema(
      description =
"""
A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.
""")
  protected List<DescriptionOfGoodsLine> descriptionOfGoods;

  @Schema(
      name = "HSCodes",
      description =
"""
A list of `HS Codes` that apply to this `consignmentItem`.
HS codes are used by customs to classify the product being shipped.
""")
  @ArraySchema(minItems = 1)
  protected List<HSCode> hsCodes;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`
""")
  @ArraySchema(maxItems = 50)
  protected List<ShippingMark> shippingMarks;
}
