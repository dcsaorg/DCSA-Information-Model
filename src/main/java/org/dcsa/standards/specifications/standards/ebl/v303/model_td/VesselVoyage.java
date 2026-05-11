package org.dcsa.standards.specifications.standards.ebl.v303.model_td;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.ebl.v3.model_td.VesselVoyage
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class VesselVoyage
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.VesselVoyage {

  @Schema(
      description =
"""
Identifies the role of the vessel voyage within the Transport Document. Possible values are:
- `FIRST_SEA_GOING` (First sea-going vessel)
- `MOTHER` (Mother vessel)
""",
      example = "MOTHER",
      maxLength = 30)
  protected String role;
}
