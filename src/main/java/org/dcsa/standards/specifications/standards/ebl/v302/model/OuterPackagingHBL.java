package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
"""
Object for outer packaging/overpack specification. Examples of overpacks are a number of packages stacked on to a pallet and secured by strapping or placed in a protective outer packaging such as a box or crate to form one unit for the convenience of handling and stowage during transport.
""",
    title = "Outer Packaging (House B/L)",
    requiredProperties = {"packageCode"})
@Data
public class OuterPackagingHBL {

  @Schema(
      description =
"""
A code identifying the outer packaging/overpack. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)
""",
      example = "5H",
      pattern = "^[A-Z0-9]{2}$",
      minLength = 2,
      maxLength = 2)
  private String packageCode;

  @Schema(
      description =
"""
Specifies the number of outer packagings/overpacks associated with this `Cargo Item`.

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

  @Schema(
      description =
"""
The identifying details of a package or the actual markings that appear on the package(s). This information is provided by the customer.
""",
      example = "Made in China",
      maxLength = 512)
  private String shippingMarks;

  @Schema(
      name = "UNNumber",
      description =
"""
United Nations Dangerous Goods Identifier (UNDG) assigned by the UN Sub-Committee of Experts on the Transport of Dangerous Goods and shown in the IMO IMDG.
""",
      example = "1463",
      minLength = 4,
      maxLength = 4,
      pattern = "^\\d{4}$")
  protected String unNumber;
}
