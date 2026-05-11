package org.dcsa.standards.specifications.standards.ebl.v303.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_si.OuterPackagingHBL
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OuterPackagingHBL
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.OuterPackagingHBL {

  @Schema(
    requiredMode = Schema.RequiredMode.REQUIRED,
    description =
"""
A code identifying the `Outer Packaging/Overpack`. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)
""",
    example = "5H",
    pattern = "^[A-Z0-9]{2}$",
    minLength = 2,
    maxLength = 2)
  private String packageCode;

  @Schema(
    description =
"""
Specifies the number of `Outer Packagings/Overpacks` associated with this `Cargo Item`.

**Condition:** Mandatory if `packageCode` is **NOT** one of the following values:

- `VY` (Bulk, solid, fine particles ("powders"))
- `VS` (Bulk, scrap metal)
- `VR` (Bulk, solid, granular particles ("grains"))
- `VQ` (Bulk, liquefied gas (at abnormal temperature/pressure))
- `VO` (Bulk, solid, large particles ("nodules"))
- `VL` (Bulk, liquid)
- `NG` (Unpacked or unpackaged, multiple units)
- `NF` (Unpacked or unpackaged, single unit)
- `NE` (Unpacked or unpackaged)
- `VG` (Bulk, gas (at 1031 mbar and 15°C))
""",
    example = "18",
    minimum = "1",
    maximum = "99999999",
    format = "int32")
  private Integer numberOfPackages;
}
