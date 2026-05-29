package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Weight;

@Schema(
    description =
        "Contains additional regulatory information applicable to fissile radioactive material transported under IMDG Code Class 7 provisions.")
@Data
public class FissileMaterial {

  @Schema(
      description =
          "Mass in grams of fissile material contained in the consignment (or mass of each fissile nuclide for mixtures when appropriate).")
  private Weight fissileMaterialMass;

  @Schema(
      description =
          "Reference to applicable fissile exception paragraph, when shipped under IMDG 2.7.2.3.5.1 to 2.7.2.3.5.6.",
      example = "2.7.2.3.5.3",
      maxLength = 100)
  private String fissileExceptionReference;

  @Schema(
      description =
          "Total mass of fissile nuclides when shipped under 2.7.2.3.5.3 to 2.7.2.3.5.5 fissile exceptions.")
  private Weight totalFissileNuclideMass;

  @Schema(
      description =
          "Reference to applicable package paragraph for fissile material, when contained in a package for which one of 6.4.11.2 (a) to (c) or 6.4.11.3 is applied",
      example = "6.4.11.2(a)",
      maxLength = 100)
  private String fissilePackageReference;

  @Schema(
      description = "Criticality Safety Index (CSI) applicable to fissile radioactive material.",
      example = "2.0",
      format = "float")
  private Double criticalitySafetyIndex;
}
