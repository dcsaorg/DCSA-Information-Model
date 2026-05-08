package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
Vessel and voyage details of a transport leg.

**Note:** This property is not to be used in combination with `locationTypeCode='ROU'`.""")
public class VesselVoyage {

  @Schema(
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$",
      example = "King of the Seas",
      description = "The name of the Vessel given by the Vessel Operator and registered with IMO.")
  private String vesselName;

  @Schema(
      minLength = 7,
      maxLength = 8,
      pattern = "^\\d{7,8}$",
      example = "9321483",
      description =
          "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel.")
  private String vesselIMONumber;

  @Schema(
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$",
      example = "FE1",
      description =
          "The carrier-specific code of the service for which the schedule details are published.")
  private String carrierServiceCode;

  @Schema(
      minLength = 8,
      maxLength = 8,
      pattern = "^SR\\d{5}[A-Z]$",
      example = "SR12345A",
      description =
"""
A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5 digits`, followed by a checksum-character as a capital letter from `A to Z`.""")
  private String universalServiceReference;

  @Schema(
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$",
      example = "2103S",
      description =
          "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.")
  private String carrierExportVoyageNumber;

  @Schema(
      minLength = 5,
      maxLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$",
      example = "2103N",
      description =
"""
A global unique voyage reference for the export Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).""")
  private String universalExportVoyageReference;
}
