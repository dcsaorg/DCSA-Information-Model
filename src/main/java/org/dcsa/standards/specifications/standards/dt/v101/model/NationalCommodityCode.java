package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = NationalCommodityCode.CLASS_SCHEMA_DESCRIPTION)
@Data
public class NationalCommodityCode {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "The national commodity classification code linked to a country with a value. Examples: NCM (Brazil), HTS (US), TARIC (EU).";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The national commodity classification code, which can be one of the following values defined by DCSA:
- `NCM` (Nomenclatura Comum do Mercosul)
- `HTS` (Harmonized Tariff Schedule)
- `SCHEDULE_B` ( Schedule B)
- `TARIC` (Integrated Tariff of the European Communities)
- `CN` (Combined Nomenclature)
- `CUS` (Customs Union and Statistics)
""",
      example = "NCM",
      maxLength = 10,
      pattern = "^\\S(?:.*\\S)?$")
  private String type;

  @Schema(
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
""",
      example = "BR",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
