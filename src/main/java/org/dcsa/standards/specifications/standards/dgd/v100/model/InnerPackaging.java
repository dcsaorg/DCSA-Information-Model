package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Inner packaging contained inside an outer packaging/overpack.")
@Data
public class InnerPackaging {

  @Schema(
      description = "Description of the packaging.",
      example = "Woven plastic water resistant Bag",
      maxLength = 200)
  private String description;

  @Schema(
      description =
          "The `material` used for the `Inner Packaging` of the referenced `Dangerous Goods`.",
      example = "Plastic",
      maxLength = 100)
  private String material;

  @Schema(
      description = "Count of `Inner Packagings` of the referenced `Dangerous Goods`.",
      example = "20")
  private Integer quantity;
}
