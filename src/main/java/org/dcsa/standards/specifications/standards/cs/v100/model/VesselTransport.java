package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Transport mode Vessel.")
@Data
public class VesselTransport {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The mode of transport as defined by DCSA. For the Vessel Transport mode this needs to be `VESSEL`.",
      allowableValues = {"VESSEL"},
      example = "VESSEL")
  private String modeOfTransport;

  @Schema(
      description =
          "The unique reference that can be used to link different `transportCallReferences` to the same port visit. The reference is provided by the port to uniquely identify a port call.",
      maxLength = 50,
      example = "NLAMS1234589")
  private String portVisitReference;

  @Schema(
      description =
          "The unique reference for a transport call. It’s the vessel operator’s responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLAMS-ACT-1-1")
  private String transportCallReference;

  @Schema() private List<ServicePartner> servicePartners;

  @Schema(
      description =
          "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5` digits, followed by a checksum-character as a capital letter from `A to Z`.",
      pattern = "^SR\\d{5}[A-Z]$",
      minLength = 8,
      maxLength = 8,
      example = "SR12345A")
  private String universalServiceReference;

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

  @Schema() private Vessel vessel;
}
