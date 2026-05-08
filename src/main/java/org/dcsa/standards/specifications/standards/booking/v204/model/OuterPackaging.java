package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.OuterPackaging
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OuterPackaging
    extends org.dcsa.standards.specifications.standards.booking.v2.model.OuterPackaging {

  @Schema(
    description =
"""
A code identifying the `Outer Packaging/Overpack`. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)

**Condition:** only applicable to dangerous goods if the `IMO packaging code` is not available.
""",
    example = "5H",
    minLength = 2,
    maxLength = 2,
    pattern = "^[A-Z0-9]{2}$")
  protected String packageCode;

  @Schema(
      description = "Description of the `Outer Packaging/Overpack`.",
      example = "Drum, steel",
      maxLength = 100)
  protected String description;

  @Schema(
      description =
"""
Specifies the number of `Outer Packagings/Overpacks` associated with this `Commodity`.

**Condition:** In case this `OuterPackaging` includes `Dangerous Goods` the `numberOfPackages` is mandatory to provide
""",
      example = "18",
      minimum = "1",
      maximum = "99999999",
      format = "int32")
  protected Integer numberOfPackages;
}
