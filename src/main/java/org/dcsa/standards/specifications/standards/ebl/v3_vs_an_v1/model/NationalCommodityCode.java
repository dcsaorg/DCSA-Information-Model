package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.types.NationalCommodityCodeValue;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NationalCommodityCode
    extends org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode {
  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `national commodity codes` values.")
  @ArraySchema(minItems = 1)
  private List<NationalCommodityCodeValue> values;
}
