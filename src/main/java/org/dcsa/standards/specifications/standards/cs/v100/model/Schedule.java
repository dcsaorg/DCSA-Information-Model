package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Schedule information for a vessel at a port.")
@Data
public class Schedule {

  @Schema(
      description =
          "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5` digits, followed by a checksum-character as a capital letter from `A to Z`.",
      pattern = "^SR\\d{5}[A-Z]$",
      minLength = 8,
      maxLength = 8,
      example = "SR12345A")
  private String universalServiceReference;

  @ArraySchema(minItems = 1)
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private List<ServicePartnerSchedule> servicePartners;

  @Schema() private Vessel vessel;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "This property can be set to `true` when no vessel has been assigned, indicating that the `vesselIMONumber` does not exist.")
  private Boolean isDummyVessel;

  @Schema(
      description =
"""
A global unique voyage reference for the import Voyage, as per DCSA standard, agreed by VSA partners for the voyage.The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`

- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      minLength = 5,
      maxLength = 5,
      example = "2103N")
  private String universalImportVoyageReference;

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage.The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`

- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      minLength = 5,
      maxLength = 5,
      example = "2103N")
  private String universalExportVoyageReference;

  @ArraySchema(minItems = 1)
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private List<Timestamp> timestamps;

  @Schema(
      description =
          "A list of cut-offs times provided by the carrier when available. A cut-off time indicates the latest date and time by which a task must be completed. For example, the latest date and time by which a container must be delivered to a terminal to be loaded on a vessel, or where certain documentation must be provided by the Shipper.")
  private List<CutOffTime> cutOffTimes;
}
