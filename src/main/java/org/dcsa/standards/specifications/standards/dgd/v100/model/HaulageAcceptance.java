package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDate;

@Schema(
    description =
        "Identifies the road transport company and driver responsible for moving the container to or from the port, and records their acceptance of the dangerous goods consignment")
@Data
public class HaulageAcceptance {

  @Schema(
      description =
          "Name of the road transport company contracted to move the container/vehicle to or from the port/terminal.",
      example = "Fast Road Haulage BV",
      maxLength = 100)
  private String haulierName;

  @Schema(
      description = "License plate of the truck or vehicle used for the road leg of transport.",
      example = "AB-123-CD",
      maxLength = 20)
  private String vehicleRegistrationNumber;

  @Schema(
      description = "Upper case name of the haulier's representative",
      example = "PETER JANSEN",
      maxLength = 100)
  private String haulierSignatory;

  @Schema(description = "Date when the hauliers acceptence was signed.")
  private FormattedDate haulierAcceptanceDate;

  @Schema(
      description = "Upper case name of the driver operating the vehicle",
      example = "BOB DRIVER",
      maxLength = 100)
  private String driverSignatory;
}
