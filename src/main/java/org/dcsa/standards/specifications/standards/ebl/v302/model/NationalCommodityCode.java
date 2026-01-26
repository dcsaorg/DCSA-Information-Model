package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.ebl.v302.types.NationalCommodityCodeValue;

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

  @Schema(
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
      example = "BR",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
