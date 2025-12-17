package org.dcsa.standards.specifications.standards.core.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.UniversalVoyageReference;

@Schema(description = "Carrier voyage number and/or universal voyage reference")
@Data
public class VoyageNumberOrReference {

  @Schema(
      type = "string",
      example = "2103S",
      description = "Carrier-specific identifier of the voyage")
  private String carrierVoyageNumber;

  @Schema() private UniversalVoyageReference universalVoyageReference;
}
