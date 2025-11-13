package org.dcsa.standards.specifications.standards.ebl.v300.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v300.types.HSCode;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.ConsignmentItem
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ConsignmentItem
    extends org.dcsa.standards.specifications.standards.dt.v100.model.ConsignmentItem {

  @Schema(description = "A list of `National Commodity Codes` that apply to this `commodity`")
  protected List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      name = "HSCodes",
      description = "A list of `HS Codes` that apply to this `consignmentItem`")
  @ArraySchema(minItems = 1)
  protected List<HSCode> hsCodes;
}
