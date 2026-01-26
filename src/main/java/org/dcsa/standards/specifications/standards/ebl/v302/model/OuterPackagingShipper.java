package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
Object for outer packaging/overpack specification. Examples of overpacks are a number of packages stacked on to a pallet and secured by strapping or placed in a protective outer packaging such as a box or crate to form one unit for the convenience of handling and stowage during transport.

**Condition:** Mandatory for non-dangerous goods cargo.
""",
    title = "Outer Packaging (Shipper)")
@Data
public class OuterPackagingShipper {

  @Schema(
      description =
"""
A code identifying the outer packaging/overpack. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)

**Condition:** only applicable to dangerous goods if the `IMO packaging code` is not available.
""",
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
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Description of the outer packaging/overpack.",
      example = "Drum, steel",
      maxLength = 100)
  private String description;

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
  private String woodDeclaration;
}
