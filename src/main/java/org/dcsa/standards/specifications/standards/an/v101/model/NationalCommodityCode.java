package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;
import org.dcsa.standards.specifications.standards.an.v101.types.NationalCommodityCodeValue;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class NationalCommodityCode
    extends org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode {

  @Schema(description = "A list of `national commodity codes` values.")
  private List<NationalCommodityCodeValue> values;
}
