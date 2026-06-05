package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Object for `Outer Packaging/Overpack` specification. Examples of `Overpacks` are a number of packages stacked on to a pallet and secured by strapping or placed in a protective `Outer Packaging` such as a box or crate to form one unit for the convenience of handling and stowage during transport.

**Condition:** Mandatory for non-dangerous goods cargo.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OuterPackagingShipper
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.OuterPackagingShipper {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Description of the `Outer Packaging/Overpack`.",
      example = "Drum, steel",
      maxLength = 100)
  private String description;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Specifies the number of `Outer Packagings/Overpacks` associated with this `Cargo Item`.",
      example = "18",
      minimum = "1",
      maximum = "99999999",
      format = "int32")
  private Integer numberOfPackages;

  @Schema(
    description =
"""
A code identifying the `Outer Packaging/Overpack`. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)

**Condition:** only applicable to dangerous goods if the `IMO packaging code` is not available.
""",
    example = "5H",
    pattern = "^[A-Z0-9]{2}$",
    minLength = 2,
    maxLength = 2)
  private String packageCode;
}
