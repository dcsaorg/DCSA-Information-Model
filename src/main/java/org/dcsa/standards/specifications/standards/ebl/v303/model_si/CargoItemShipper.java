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

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.CargoItemShipper
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CargoItemShipper
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.CargoItemShipper {

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `cargoItem`. The `extendedNationalCommodityCodes` support up to 16 character codes.

**Note:** If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedNationalCommodityCode> extendedNationalCommodityCodes;

  @Schema(
      description = "Link to the **House Bill of Lading** this `cargoItem` is connected to.",
      example = "ABC123",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 20)
  private String houseBillOfLadingReference;

  @Schema(
      name = "nationalCommodityCodes",
      description =
"""
A list of `National Commodity Codes` that apply to this `cargoItem`

**DEPRECATED:** This property is not to be used any more, please use `extendedNationalCommodityCodes` instead. If both `nationalCommodityCodes` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCodes` must be ignored.
""")
  private List<NationalCommodityCode> renamed_nationalCommodityCodes;
}
