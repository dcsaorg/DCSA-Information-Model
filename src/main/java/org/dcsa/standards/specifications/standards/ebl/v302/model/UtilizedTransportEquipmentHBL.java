package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;

@Schema(
    description = "Specifies the container (`Equipment`), `Seals` and `References`",
    title = "Utilized Transport Equipment (House B/L)")
@Data
public class UtilizedTransportEquipmentHBL {

  @Schema(description = "A list of `Seals`")
  @ArraySchema(minItems = 1)
  private List<Seal> seals;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indication if the container is `EMPTY` or `LADEN`.

- `EMPTY` (Empty)
- `LADEN` (Laden)
""",
      example = "LADEN",
      allowableValues = {"LADEN", "EMPTY"})
  private String emptyIndicatorCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates whether the container is shipper owned (SOC).",
      example = "false")
  private Boolean isShipperOwned;

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

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      name = "ISOEquipmentCode",
      description =
          "Unique code for the different equipment size and type used to transport commodities. The code can refer to one of ISO size type (e.g. 22G1) or ISO type group (e.g. 22GP) following the [ISO 6346](https://www.iso.org/standard/83558.html) standard.",
      example = "22G1",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 4)
  private String isoEquipmentCode;
}
