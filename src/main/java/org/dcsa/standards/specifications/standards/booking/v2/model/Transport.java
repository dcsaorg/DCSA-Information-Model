package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Transport.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Transport {

  public static final String CLASS_SCHEMA_DESCRIPTION = "A single `leg` of the `Transport Plan`";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code qualifying a specific stage of transport e.g. pre-carriage, main carriage transport or on-carriage transport
- `PRC` (Pre-Carriage)
- `MNC` (Main Carriage Transport)
- `ONC` (On-Carriage Transport)
""",
      example = "PRC",
      allowableValues = {"PRC", "MNC", "ONC"})
  private String transportPlanStage;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Sequence number of the transport plan stage",
      example = "5",
      format = "int32")
  private Integer transportPlanStageSequenceNumber;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The load location for this transport leg.")
  private LoadLocation loadLocation;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The discharge location for this transport leg.")
  private DischargeLocation dischargeLocation;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The planned date of departure.",
      example = "2021-05-17",
      format = "date")
  private String plannedDepartureDate;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The planned date of arrival.",
      example = "2021-05-19",
      format = "date")
  private String plannedArrivalDate;

  @Schema(
      description =
"""
The mode of transport as defined by DCSA. The currently supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `MULTIMODAL` (if multiple modes are used)
""",
      example = "VESSEL",
      maxLength = 50)
  private String modeOfTransport;

  @Schema(
      description = "The name of the Vessel given by the Vessel Operator and registered with IMO.",
      example = "King of the Seas",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String vesselName;

  @Schema(
      description = "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd&apos;s register code, which does not change during the lifetime of the vessel",
      example = "9321483",
      maxLength = 8,
      minLength = 7,
      pattern = "^\\d{7,8}$")
  private String vesselIMONumber;

  @Schema(
      description = "The carrier-specific code of the service for which the schedule details are published.",
      example = "FE1",
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierServiceCode;

  @Schema(
      description = "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5 digits`, followed by a checksum-character as a capital letter from `A to Z`.",
      example = "SR12345A",
      maxLength = 8,
      minLength = 8,
      pattern = "^SR\\d{5}[A-Z]$")
  private String universalServiceReference;

  @Schema(
      description = "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.",
      example = "2103N",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String carrierImportVoyageNumber;

  @Schema(
      description =
"""
A global unique voyage reference for the import Voyage, as per DCSA standard, agreed by VSA partners for the voyage. The voyage reference must match the regular expression pattern: `\\d{2}[0-9A-Z]{2}[NEWSR]`
- `2 digits` for the year
- `2 alphanumeric characters` for the sequence number of the voyage
- `1 character` for the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""",
      example = "2103N",
      maxLength = 5,
      minLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$")
  private String universalImportVoyageReference;

  @Schema(
      description = "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.",
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
      maxLength = 5,
      minLength = 5,
      pattern = "^\\d{2}[0-9A-Z]{2}[NEWSR]$")
  private String universalExportVoyageReference;
}
