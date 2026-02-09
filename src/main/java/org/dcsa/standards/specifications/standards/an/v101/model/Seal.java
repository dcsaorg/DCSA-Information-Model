package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v101.model.Seal.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class Seal extends org.dcsa.standards.specifications.standards.dt.v101.model.Seal {

  @Schema(
      description =
"""
The source of the seal, namely who has affixed the seal.
- `CAR` (Carrier)
- `SHI` (Shipper)
- `PHY` (Phytosanitary)
- `VET` (Veterinary)
- `CUS` (Customs)

In [UN/EDIFACT 16A 9303](https://unece.org/fileadmin/DAM/trade/untdid/d16a/tred/tred9303.htm),
both VET (Veterinary) and PHY (Phytosanitary) map to AC (Quarantine agency).
""",
      example = "CUS",
      enumAsRef = true,
      maxLength = 10)
  protected String source;
}
