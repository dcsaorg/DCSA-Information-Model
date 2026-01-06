package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = OuterPackaging.CLASS_SCHEMA_DESCRIPTION)
@Data
public class OuterPackaging {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Outer packaging or overpack specification, such as palletized or crated groupings of packages.";

  @Schema(
      description =
"""
A code identifying the outer packaging/overpack. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)

**Condition:** only applicable to dangerous goods if the `IMO packaging code` is not available.
""",
      example = "5H",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z0-9]{2}$")
  protected String packageCode;

  @Schema(
      description =
"""
The code of the packaging as per IMO.

**Condition:** only applicable to dangerous goods if specified in the [IMO IMDG code](https://www.imo.org/en/publications/Pages/IMDG%20Code.aspx). If not available, the `packageCode` as per UN recommendation 21 should be used.
""",
      example = "1A2",
      minLength = 1,
      maxLength = 5,
      pattern = "^[A-Z0-9]{1,5}$")
  protected String imoPackagingCode;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Specifies the number of outer packagings/overpacks associated with this `Cargo Item`.", example = "18", minimum = "1", maximum = "99999999", format = "int32")
  protected Integer numberOfPackages;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Description of the outer packaging/overpack.", example = "Drum, steel", maxLength = 100)
  protected String description;

  @Schema(
      description =
"""
Property to clearly indicate if the products, packaging and any other items are made of wood. Possible values include:
- `NOT_APPLICABLE` (if no wood or any other wood product such as packaging and supports are being shipped)
- `NOT_TREATED_AND_NOT_CERTIFIED` (if the wood or wooden materials have not been treated nor fumigated and do not include a certificate)
- `PROCESSED` (if the wood or wooden materials are entirely made of processed wood, such as plywood, particle board, sliver plates of wood and wood laminate sheets produced using glue, heat, pressure or a combination of these)
- `TREATED_AND_CERTIFIED` (if the wood or wooden materials have been treated and/or fumigated and include a certificate)
""",
      example = "TREATED_AND_CERTIFIED",
      maxLength = 30)
  protected String woodDeclaration;

  @Schema(description = "A list of `Dangerous Goods`")
  protected List<DangerousGoods> dangerousGoods;
}
