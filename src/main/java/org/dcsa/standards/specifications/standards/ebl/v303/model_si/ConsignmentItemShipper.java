package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedHSCode;
import org.dcsa.standards.specifications.standards.ebl.v3.types.HSCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v303.types.DescriptionOfGoodsForCustomsLine;
import org.dcsa.standards.specifications.standards.ebl.v303.types.ShippingMark;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsignmentItemShipper
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ConsignmentItemShipper
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsignmentItemShipper {

  @Schema(
      description =
"""
A detailed plain-language description of the goods provided specifically for customs filing purposes. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 150)
  private List<DescriptionOfGoodsForCustomsLine> descriptionOfGoodsForCustoms;

  @Schema(
      name = "HSCodes",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
A list of `HS Codes` that apply to this `consignmentItem`.

**DEPRECATED:** Because of backward compatibility it is required to provide a value in this property even though a value is provided in the `extendedHSCodes` property. In case both properties are provided (`extendedHSCodes` and `HSCodes`) - `HSCodes` must be ignored.
""")
  @ArraySchema(minItems = 1)
  private List<HSCode> hsCodes;

  @Schema(
      description =
"""
A list of `HS Codes` that apply to this `consignmentItem`. The `extendedHSCodes` support up to 12 character codes.

**Note:** If `extendedHSCodes` is provided - it always takes precedence over `HSCodes` (which is a required property but must be ignored).
""")
  @ArraySchema()
  protected List<ExtendedHSCode> extendedHSCodes;

  @Schema(
      name = "nationalCommodityCodes",
      description =
"""
A list of `National Commodity Codes` that apply to this `consignmentItem`

**DEPRECATED:** This property is not to be used any more, please use `extendedNationalCommodityCodes` instead. If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  private List<NationalCommodityCode> renamed_nationalCommodityCodes;

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `consignmentItem`. The `extendedNationalCommodityCodes` support up to 16 character codes.

**Note:** If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedNationalCommodityCode> extendedNationalCommodityCodes;

  @Schema(
    description =
"""
A list of the `ShippingMarks` provided specifically for customs filing purposes.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 50)
  private List<ShippingMark> shippingMarksForCustoms;
}
