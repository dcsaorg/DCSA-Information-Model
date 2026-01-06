package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = InnerPackaging.CLASS_SCHEMA_DESCRIPTION)
@Data
public class InnerPackaging {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Object for inner packaging specification.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Count of `Inner Packagings` of the referenced `Dangerous Goods`.",
      example = "20",
      format = "int32")
  protected Integer quantity;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The `material` used for the `Inner Packaging` of the referenced `Dangerous Goods`.",
      example = "Plastic",
      maxLength = 100)
  protected String material;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Description of the packaging.",
      example = "Woven plastic water resistant Bag",
      maxLength = 100)
  protected String description;
}
