package org.dcsa.standards.specifications.standards.an.v100.model;

import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.OuterPackaging
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class OuterPackaging
    extends org.dcsa.standards.specifications.standards.dt.v100.model.OuterPackaging {

  @Schema(
      description =
"""
A code identifying the outer packaging/overpack. `PackageCode` must follow the codes specified in [Recommendation N°21](https://unece.org/trade/uncefact/cl-recommendations)
""",
      example = "5H",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z0-9]{2}$")
  protected String packageCode;

  @Schema(
      name = "IMOPackagingCode",
      description =
"""
The code of the packaging as per IMO.
""",
      example = "1A2",
      minLength = 1,
      maxLength = 5,
      pattern = "^[A-Z0-9]{1,5}$")
  protected String imoPackagingCode;
}
