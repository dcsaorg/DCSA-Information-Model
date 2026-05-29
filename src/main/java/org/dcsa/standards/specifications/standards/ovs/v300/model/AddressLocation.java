package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An interface used to express a location using an `Address` object.")
@Data
public class AddressLocation {

  @Schema(
      description = "The name of the location.",
      maxLength = 100,
      example = "Port of Amsterdam")
  private String locationName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Discriminator used to identify this as an `Address` location interface.",
      maxLength = 4,
      example = "ADDR")
  private String locationType;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "An object for storing address related information.")
  private Address address;
}

