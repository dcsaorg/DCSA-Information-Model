package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.Volume;
import org.dcsa.standards.specifications.standards.core.v200.model.Weight;

@Schema(description = "A cargo item within a consignment item.")
@Data
public class CargoItem {

  @Schema(
      description =
          "Gross volume of the cargo item, calculated by multiplying the width, height, and length of the packaged cargo.")
  private Volume cargoGrossVolume;

  @Schema(
      description =
"""
Gross weight of the cargo item including packaging being carried in the container.
Excludes the tare weight of the container.
""")
  private Weight cargoGrossWeight;

  @Schema(
      description =
          "Net volume of the cargo item, calculated by multiplying the width, height, and length of the cargo, excluding packaging.")
  private Volume cargoNetVolume;

  @Schema(
      description =
          "Net weight of the cargo item, excluding packaging and excluding the tare weight of the container.")
  private Weight cargoNetWeight;

  @Schema(
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.
According to [ISO 6346](https://www.iso.org/standard/83558.html), a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with [ISO 6346](https://www.iso.org/standard/83558.html), it is suggested to follow [Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations) from SMDG.
""",
      example = "APZU4812090",
      maxLength = 11)
  private String equipmentReference;

  @Schema(description = "Packaging information for this cargo item.")
  private OuterPackaging outerPackaging;
}
