package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.DangerousGoods;

@Schema(description = OuterPackaging.CLASS_SCHEMA_DESCRIPTION)
@Data
public class OuterPackaging {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Outer packaging or overpack specification, such as palletized or crated groupings of packages.";

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

  @Schema(
      description =
"""
Specifies the number of outer packagings/overpacks associated with this `Commodity`.

**Condition:** In case this `OuterPackaging` includes `Dangerous Goods` the `numberOfPackages` is mandatory to provide
""",
      example = "18",
      minimum = "1",
      maximum = "99999999",
      format = "int32")
  protected Integer numberOfPackages;

  @Schema(
      description = "Description of the outer packaging/overpack.",
      example = "Drum, steel",
      maxLength = 100)
  protected String description;

  @Schema(description = "A list of `Dangerous Goods` related to the `Commodity`")
  protected List<DangerousGoods> dangerousGoods;
}
