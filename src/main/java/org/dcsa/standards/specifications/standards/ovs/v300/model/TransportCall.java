package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(
    description =
"""
A transportCall in the schedule. A transportCall can be either just a Port or further specified as a terminalCall.

The order of the list is the sequence of the list
""")
@Data
public class TransportCall {

  @Schema(
      description =
"""
The unique reference that can be used to link different `transportCallReferences` to the same port visit. The reference is provided by the port to uniquely identify a port call
""",
      maxLength = 50,
      example = "NLAMS1234589")
  private String portVisitReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The unique reference for a transport call. It’s the vessel operator’s responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.
""",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLAMS-ACT-1-1")
  private String transportCallReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.",
      maxLength = 50,
      example = "2103N")
  private String carrierImportVoyageNumber;

  @Schema(
      description =
          "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.",
      maxLength = 50,
      example = "2103S")
  private String carrierExportVoyageNumber;

  @Schema(
      description =
"""
A global unique voyage reference for the import Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      example = "2103N")
  private String universalImportVoyageReference;

  @Schema(
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      example = "2103N")
  private String universalExportVoyageReference;

  @Schema(
      description =
"""
General purpose object to capture location-related data, the location can be specified in **one** of the following ways: `UN Location Code`, a `Facility` or an `Address`.
""")
  private Location location;

  @Schema(
      description =
"""
The set of codes in `Status Code` are ONLY meant to communicate any change / exception to the published schedule. This is not required in case of normal schedule. Possible values are:

- OMIT (Omit)
- BLNK (Blank)
- ADHO (Ad Hoc)
- PHOT (Phase Out)
- PHIN (Phase In)
- SLID (Sliding)
- ROTC (Rotation Change)
- CUTR (Cut and Run)

More details can be found on [GitHub](https://github.com/dcsaorg/DCSA-OpenAPI/blob/master/ovs/v3/reference-data/portcallstatuscodes-v300.csv)
""",
      example = "OMIT")
  private String statusCode;

  @Schema() private List<Timestamp> timestamps;
}
