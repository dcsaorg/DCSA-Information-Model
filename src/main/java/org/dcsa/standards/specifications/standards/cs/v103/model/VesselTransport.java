package org.dcsa.standards.specifications.standards.cs.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "Transport mode Vessel.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VesselTransport
    extends org.dcsa.standards.specifications.standards.cs.v102.model.VesselTransport {

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`

- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      minLength = 5,
      maxLength = 5,
      example = "2103N")
  private String universalExportVoyageReference;

  @Schema(
      description =
"""
A global unique voyage reference for the import Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`

- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      minLength = 5,
      maxLength = 5,
      example = "2103N")
  private String universalImportVoyageReference;

  @Schema() private org.dcsa.standards.specifications.standards.cs.v102.model.Vessel vessel;
}
