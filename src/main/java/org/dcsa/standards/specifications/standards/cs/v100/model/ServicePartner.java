package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "The service code and voyage number as identified by the carriers that are partners in the service.")
@Data
public class ServicePartner {

  @Schema(
      description = "The carrier code based on either the SMDG or SCAC code lists.",
      pattern = "^\\S+$",
      maxLength = 4,
      example = "MAEU")
  private String carrierCode;

  @Schema(
      description = "Identifies the code list provider used for the `carrierCode`.",
      allowableValues = {"SMDG", "NMFTA"},
      example = "NMFTA")
  private String carrierCodeListProvider;

  @Schema(
      description = "The name of the service.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "Great Lion Service")
  private String carrierServiceName;

  @Schema(
      description =
          "The carrier-specific code of the service for which the schedule details are published.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 11,
      example = "FE1")
  private String carrierServiceCode;

  @Schema(
      description =
          "The identifier of an import voyage. The carrier-specific identifier of the import Voyage.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "2103S")
  private String carrierImportVoyageNumber;

  @Schema(
      description =
          "The identifier of an export voyage. The carrier-specific identifier of the export Voyage.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "2103N")
  private String carrierExportVoyageNumber;
}
