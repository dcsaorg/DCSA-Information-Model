package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "Contains regulatory information applicable to Class 7 radioactive material consignments in accordance with IMDG Code 5.4.1.5.7.")
@Data
public class RadioactiveMaterial {

  @Schema(
      description =
          "Name of the radionuclide(s) contained in Class 7 radioactive material consignments. For mixtures, provide a general description or list the most restrictive nuclides.",
      example = "Cs-137",
      maxLength = 200)
  private String radionuclideIdentification;

  @Schema(
      description =
          "Describes the physical and chemical form of the radioactive material, or a notation that the material is special form radioactive material or low dispersible radioactive material. A generic chemical description is acceptable for chemical form.",
      example = "Solid contaminated metal debris",
      maxLength = 500)
  private String physicalChemicalForm;

  @Schema(
      description =
          "Maximum activity of the radioactive contents during transport, expressed in becquerels with an SI prefix.")
  private Activity maximumActivityDuringTransport;

  @Schema(
      description =
          "Contains additional regulatory information applicable to fissile radioactive material transported under IMDG Code Class 7 provisions.")
  private FissileMaterial fissileMaterial;

  @Schema(
      description =
          "Indicates that the radioactive material shipment must be shipped under exclusive use.")
  private Boolean isExclusiveUseShipment;

  @Schema(
      description =
          "For LSA-II, LSA-III, SCO-I, SCO-II and SCO-III, total activity of the consignment as a multiple of A2. Use 0 when A2 is unlimited.",
      example = "12.5",
      format = "float")
  private Double totalActivityA2Multiple;

  @Schema(
      description =
"""
Radiation package category for Class 7 material. Allowed values:
- `I-WHITE`
- `II-YELLOW`
- `III-YELLOW`
""",
      example = "II-YELLOW",
      maxLength = 10)
  private String radioactivePackageCategory;

  @Schema(
      description = "Transport Index (TI) assigned to the radioactive package.",
      example = "1.8",
      format = "float")
  private Double transportIndex;
}
