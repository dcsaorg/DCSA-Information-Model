package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Packaging information for a cargo item.")
@Data
public class OuterPackaging {

  @Schema(
      name = "IMOPackagingCode",
      description = "The code of the packaging as per IMO.",
      example = "1A2",
      maxLength = 5)
  private String imoPackagingCode;

  @Schema(description = "A list of `Dangerous Goods`")
  private List<DangerousGoods> dangerousGoods;

  @Schema(
      description = "Description of the outer packaging/overpack.",
      example = "Drum, steel",
      maxLength = 200)
  private String description;

  @Schema(
      description =
          "Specifies the number of outer packagings/overpacks associated with this `Cargo Item`.",
      example = "18")
  private Integer numberOfPackages;

  @Schema(
      description =
          "A code identifying the outer packaging/overpack. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)",
      example = "5H",
      maxLength = 5)
  private String packageCode;
}
