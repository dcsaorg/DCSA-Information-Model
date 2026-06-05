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
"""
Defines a list of `CargoItems` belonging together in the same consignment. A `ConsignmentItem` can be split across multiple containers (`UtilizedTransportEquipment`) by referencing multiple `CargoItems`
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ConsignmentItemHBL
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.ConsignmentItemHBL {

  @Schema(
      description =
"""
A list of `National Commodity Codes` that apply to this `consignmentItem`. The `extendedNationalCommodityCodes` support up to 16 character codes.

**Note:** If both `nationalCommodityCode` and `extendedNationalCommodityCodes` are provided then `extendedNationalCommodityCodes` takes precedence and `nationalCommodityCode` must be ignored.
""")
  @ArraySchema()
  protected List<ExtendedNationalCommodityCode> extendedNationalCommodityCodes;
}
