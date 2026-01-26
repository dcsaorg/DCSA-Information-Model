package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Vessel and export voyage details.")
@Data
public class VesselVoyage {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The name of the first sea going Vessel on board which the cargo is loaded or intended to be loaded
""",
      example = "King of the Seas",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String vesselName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The identifier of an export voyage. The carrier-specific identifier of the export Voyage.
""",
      example = "2103S",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierExportVoyageNumber;

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      example = "2103N",
      minLength = 5,
      maxLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$")
  private String universalExportVoyageReference;
}
