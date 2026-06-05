package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
public class TransportCall {

  @Schema(
      description =
          "The unique reference that can be used to link different `transportCallReferences` to the same port visit. The reference is provided by the port to uniquely identify a port call.",
      maxLength = 50,
      example = "NLRTM1234589")
  private String portVisitReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The unique reference for a transport call. It’s the vessel operator’s responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLRTM-HPD2-1-1")
  private String transportCallReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "2103N")
  private String carrierImportVoyageNumber;

  @Schema(
      description =
          "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "2103S")
  private String carrierExportVoyageNumber;

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

  @Schema(
      description =
          "A list of cut-offs times provided by the carrier when available. A cut-off time indicates the latest date and time by which a task must be completed. For example, the latest date and time by which a container must be delivered to a terminal to be loaded on a vessel, or where certain documentation must be provided by the Shipper.")
  private List<CutOffTime> cutOffTimes;

  @Schema() private TransportCallLocation location;

  @ArraySchema(minItems = 1)
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private List<Timestamp> timestamps;
}
