package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.booking.v204.model.ExtendedNationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v302.model.NationalCommodityCode;

@Schema(
    description =
"""
A `cargoItem` is the smallest unit used by stuffing. A `cargoItem` cannot be split across containers.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CargoItem extends org.dcsa.standards.specifications.standards.ebl.v3.model.CargoItem {

  @Schema(
      name = "nationalCommodityCodes",
      description =
"""
A list of `National Commodity Codes` that apply to this `cargoItem`

**DEPRECATED:** This property is not to be used any more, please use `extendedNationalCommodityCodes` instead. If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  private List<NationalCommodityCode> renamed_nationalCommodityCodes;

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `cargoItem`. The `extendedNationalCommodityCodes` support up to 16 character codes.

**Note:** If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedNationalCommodityCode> extendedNationalCommodityCodes;
}
