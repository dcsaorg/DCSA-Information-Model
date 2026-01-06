package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Weight;

@Schema(description = Equipment.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Equipment {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Used for storing cargo during transport. Equipment size/type is defined by the ISO 6346 code. Common sizes include 20'/40'/45' containers.";

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
      name = "ISOEquipmentCode",
      description =
"""
Unique code for the different equipment size and type used to transport commodities. The code can refer to one of ISO size type (e.g. 22G1) or ISO type group (e.g. 22GP) following the [ISO 6346](https://www.iso.org/standard/83558.html) standard.
""",
      example = "22G1",
      maxLength = 4,
      pattern = "^\\S(?:.*\\S)?$")
  protected String isoEquipmentCode;

  @Schema(description = "The tare weight of the container.")
  protected Weight tareWeight;
}
