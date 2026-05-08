package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.booking.v204.types.ExtendedNationalCommodityCodeValue;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v202.model.NationalCommodityCode
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExtendedNationalCommodityCode
    extends org.dcsa.standards.specifications.standards.booking.v202.model.NationalCommodityCode {

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
"""
The national commodity classification code, which can be one of the following values defined by DCSA:
- `NCM` (Nomenclatura Comum do Mercosul)
- `HTS` (Harmonized Tariff Schedule)
- `SCHEDULE_B` (Schedule B)
- `TARIC` (Integrated Tariff of the European Communities)
- `CN` (Combined Nomenclature)
- `CUS` (Customs Union and Statistics)
""",
    example = "NCM",
    maxLength = 10,
    pattern = "^\\S(?:.*\\S)?$")
  private String type;

  @Schema(
      name = "values",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `national commodity codes` values supporting up to 16 characters.")
  @ArraySchema(minItems = 1)
  private List<ExtendedNationalCommodityCodeValue> extendedValues;
}
