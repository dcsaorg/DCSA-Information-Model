package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "Service schedule containing vessel schedules.")
@Data
public class ServiceSchedule {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the service.",
      maxLength = 50,
      example = "Great Lion Service")
  private String carrierServiceName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The carrier-specific code of the service for which the schedule details are published.",
      maxLength = 11,
      example = "FE1")
  private String carrierServiceCode;

  @Schema(
      description =
          "A global unique service reference, as per DCSA standard, agreed by VSA partners for the service. The service reference must match the regular expression pattern: `SR\\d{5}[A-Z]`. The letters `SR` followed by `5 digits`, followed by a checksum-character as a capital letter from `A to Z`.",
      pattern = "^SR\\d{5}[A-Z]$",
      maxLength = 8,
      example = "SR12345A")
  private String universalServiceReference;

  @Schema() private List<VesselSchedule> vesselSchedules;
}
