package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Object for outer packaging/overpack specification for House B/L.", title = "Outer Packaging (House B/L)")
@Data
public class OuterPackagingHBL {

  @Schema(
      description = "A code identifying the outer packaging/overpack.",
      example = "5H",
      pattern = "^[A-Z0-9]{2}$",
      minLength = 2,
      maxLength = 2)
  private String packageCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Specifies the number of outer packagings/overpacks associated with this `Cargo Item`.",
      example = "18",
      minimum = "1",
      maximum = "99999999",
      format = "int32")
  private Integer numberOfPackages;

  @Schema(
      description = "Description of the outer packaging/overpack.",
      example = "Drum, steel",
      maxLength = 100)
  private String description;
}
