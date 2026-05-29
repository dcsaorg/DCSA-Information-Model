package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Service schedule containing vessel schedules.")
@Data
public class ServiceSchedule {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the service.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50,
      example = "Great Lion Service")
  private String carrierServiceName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The carrier-specific code of the service for which the schedule details are published.",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 11,
      example = "FE1")
  private String carrierServiceCode;

  @Schema(
      description =
          "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service.",
      pattern = "^SR\\d{5}[A-Z]$",
      minLength = 8,
      maxLength = 8,
      example = "SR12345A")
  private String universalServiceReference;

  @ArraySchema(minItems = 1)
  @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
  private List<VesselSchedule> vesselSchedules;
}
