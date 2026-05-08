package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.booking.v2.types.HSCode;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedHSCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.Commodity
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Commodity
    extends org.dcsa.standards.specifications.standards.booking.v2.model.Commodity {

  @Schema(
      name = "HSCodes",
      description =
"""
A list of `HS Codes` that apply to this `commodity`

**DEPRECATED:** This property is not to be used any more, please use `extendedHSCodes` instead. If both `HSCodes` and `extendedHSCodes` are provided then `extendedHSCodes` takes precedence and `HSCodes` must be ignored.
""")
  private List<HSCode> hsCodes;

  @Schema(
      description =
"""
A list of `HS Codes` that apply to this `commodity`. The `extendedHSCodes` support up to 12 character codes.

**Note:** If both `HSCodes` and `extendedHSCodes` are provided then `extendedHSCodes` takes precedence and `HSCodes` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedHSCode> extendedHSCodes;

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `commodity`

**DEPRECATED:** This property is not to be used any more, please use `extendedNationalCommodityCodes` instead. If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  private List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `commodity`. The `extendedNationalCommodityCodes` support up to 16 character codes.

**Note:** If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedNationalCommodityCode> extendedNationalCommodityCodes;
}
