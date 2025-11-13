package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.model.Weight;

@Schema(description = CargoItem.CLASS_SCHEMA_DESCRIPTION)
@Data
public class CargoItem {

  public static final String CLASS_SCHEMA_DESCRIPTION = "A cargoItem is the smallest unit used in stuffing. It cannot be split across containers.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.
According to [ISO 6346](https://www.iso.org/standard/83558.html), a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with [ISO 6346](https://www.iso.org/standard/83558.html), it is suggested to follow [Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations) from SMDG.
""",
      example = "APZU4812090",
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$")
  protected String equipmentReference;

  @Schema(
      description =
"""
Gross weight of the cargo item including packaging being carried in the container.
Excludes the tare weight of the container.
""")
  protected Weight cargoGrossWeight;

  @Schema(
      description =
"""
Gross volume of the cargo item, calculated by multiplying the width, height, and length of the packaged cargo.
""")
  protected Volume cargoGrossVolume;

  @Schema(
      description =
"""
Net weight of the cargo item, excluding packaging and excluding the tare weight of the container.
""")
  protected Weight cargoNetWeight;

  @Schema(
      description =
"""
Net volume of the cargo item, calculated by multiplying the width, height, and length of the cargo, excluding packaging.
""")
  protected Volume cargoNetVolume;

  @Schema(description = "Export license applicable to this cargo item.")
  protected ExportLicense exportLicense;

  @Schema(description = "Import license applicable to this cargo item.")
  protected ImportLicense importLicense;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Packaging information for this cargo item.")
  protected OuterPackaging outerPackaging;

  @Schema(description = "A list of `Customs references`")
  protected List<CustomsReference> customsReferences;
}
