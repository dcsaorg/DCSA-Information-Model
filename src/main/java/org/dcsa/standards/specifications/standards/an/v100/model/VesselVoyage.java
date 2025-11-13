package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v101.types.CountryCode;
import org.dcsa.standards.specifications.standards.core.v101.types.UniversalServiceReference;
import org.dcsa.standards.specifications.standards.core.v101.types.UniversalVoyageReference;
import org.dcsa.standards.specifications.standards.core.v101.types.VesselIMONumber;

@Data
@Schema(description = "Vessel and voyage details of a transport leg")
public class VesselVoyage {

  @Schema(maxLength = 50, example = "King of the Seas", description = "Vessel name")
  private String vesselName;

  @Schema(
      description =
"""
The national flag of the country under which a vessel is registered,
indicated by the 2-character code defined in
[ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en).
""")
  private CountryCode vesselFlag;

  @Schema() private VesselIMONumber vesselIMONumber;

  @Schema(
      maxLength = 10,
      example = "NCVV",
      description =
"""
A unique alphanumeric identity that belongs to the vessel
and is assigned by the International Telecommunication Union (ITU).
It consists of a three-letter alphanumeric prefix that indicates nationality,
followed by one to four characters to identify the individual vessel.
""")
  private String vesselCallSign;

  @Schema(
      maxLength = 11,
      example = "FE1",
      description =
          "The carrier-specific code of the service for which the schedule details are published")
  private String carrierServiceCode;

  @Schema() private UniversalServiceReference universalServiceReference;

  @Schema(
      maxLength = 50,
      example = "2208N",
      description = "Carrier-specific identifier of the import voyage")
  private String carrierImportVoyageNumber;

  @Schema() private UniversalVoyageReference universalImportVoyageReference;

  @Schema(
      maxLength = 50,
      example = "2208N",
      description = "Carrier-specific identifier of the export voyage")
  private String carrierExportVoyageNumber;

  @Schema() private UniversalVoyageReference universalExportVoyageReference;

  @Schema(
      maxLength = 100,
      example = "NLRTM25002178",
      description =
"""
A registration number assigned by customs to the vessel before its arrival at Port of Discharge.
It can be used for customs clearance purposes in specific countries (e.g. NL, UK).
""")
  private String callReferenceNumber;
}
