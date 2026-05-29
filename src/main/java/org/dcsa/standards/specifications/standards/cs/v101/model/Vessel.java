package org.dcsa.standards.specifications.standards.cs.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Vessel information.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Vessel extends org.dcsa.standards.specifications.standards.cs.v100.model.Vessel {

  @Schema(
      description =
"""
Identifies the code list provider used for the operator and partner carriercodes.
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""",
      allowableValues = {"SMDG", "NMFTA"},
      example = "NMFTA")
  private String operatorCarrierCodeListProvider;
}
