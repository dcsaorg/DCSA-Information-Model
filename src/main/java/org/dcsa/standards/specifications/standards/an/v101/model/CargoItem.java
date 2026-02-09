package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v101.model.CargoItem
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class CargoItem
    extends org.dcsa.standards.specifications.standards.dt.v101.model.CargoItem {

  @Schema(description = "A list of `National Commodity Codes` that apply to this `cargoItem`")
  protected List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(
      type = "integer",
      format = "int32",
      description =
"""
The order in which the carrier submitted the pre-announcement to the customs authority (which may vary by country) is,
in some cases, the same order the consignee must follow for the final customs release.
""",
      example = "12")
  protected int position;

  @Schema(description = "A list of `Charges`")
  private List<Charge> charges;
}
