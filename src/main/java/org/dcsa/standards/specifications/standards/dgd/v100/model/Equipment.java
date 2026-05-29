package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Equipment used for transport")
@Data
public class Equipment {

  @Schema(
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible. According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with ISO 6346, it is suggested to follow Recommendation #2: Containers with non-ISO identification from SMDG.
""",
      example = "APZU4812090",
      maxLength = 11)
  private String equipmentReference;

  @Schema(
      name = "ISOEquipmentCode",
      description =
          "Unique code for the different equipment size and type used to transport commodities. The code can refer to one of ISO size type (e.g. 22G1) or ISO type group (e.g. 22GP) following the ISO 6346 standard.",
      example = "22GP",
      maxLength = 4)
  private String isoEquipmentCode;

  @Schema(description = "The tare weight of the container")
  private org.dcsa.standards.specifications.standards.core.v200.model.Weight tareWeight;
}

