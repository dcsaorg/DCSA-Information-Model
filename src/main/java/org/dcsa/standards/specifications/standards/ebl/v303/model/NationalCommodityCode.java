package org.dcsa.standards.specifications.standards.ebl.v303.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
The national commodity classification code, which can be one of the following values defined by DCSA:
- `NCM` (Nomenclatura Comum do Mercosul)
- `HTS` (Harmonized Tariff Schedule)
- `SCHEDULE_B` (Schedule B)
- `TARIC` (Integrated Tariff of the European Communities)
- `CN` (Combined Nomenclature)
- `CUS` (Customs Union and Statistics)
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NationalCommodityCode
    extends org.dcsa.standards.specifications.standards.ebl.v302.model.NationalCommodityCode {

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
}
