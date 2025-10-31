package org.dcsa.standards.specifications.standards.core.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.types.UniversalServiceReference;

@Schema(description = "Carrier service code and/or universal service reference")
@Data
public class ServiceCodeOrReference {

  @Schema(
      type = "string",
      example = "FE1",
      description = "Carrier-specific identifier of a service.")
  private String carrierServiceCode;

  @Schema() private UniversalServiceReference universalServiceReference;
}
