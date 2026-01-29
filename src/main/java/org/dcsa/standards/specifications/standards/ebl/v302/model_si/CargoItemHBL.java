package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CargoGrossWeight;

@Schema(
    description =
        "A `cargoItem` is the smallest unit used by stuffing. A `cargoItem` cannot be split across containers.",
    title = "Cargo Item (House B/L)")
@Data
public class CargoItemHBL {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.
According to [ISO 6346](https://www.iso.org/standard/83558.html), a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with [ISO 6346](https://www.iso.org/standard/83558.html), it is suggested to follow [Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations) from SMDG.
""",
      example = "APZU4812090",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 11)
  private String equipmentReference;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private CargoGrossWeight cargoGrossWeight;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private OuterPackagingHBL outerPackaging;
}
