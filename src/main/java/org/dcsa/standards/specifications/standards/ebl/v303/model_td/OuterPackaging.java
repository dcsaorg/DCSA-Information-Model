package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v101.model.OuterPackaging
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OuterPackaging
    extends org.dcsa.standards.specifications.standards.dt.v101.model.OuterPackaging {

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
  protected Integer numberOfPackages;

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
}
