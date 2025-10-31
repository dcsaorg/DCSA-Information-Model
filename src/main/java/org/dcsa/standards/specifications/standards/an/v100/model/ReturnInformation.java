package org.dcsa.standards.specifications.standards.an.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.model.Location;

@Data
@Schema(description = "Groups the information regarding empty-equipment return.")
public class ReturnInformation {

  @Schema(
      example = "HLCSTD45",
      description = "Code or reference required when returning equipment to the depot.",
      maxLength = 50)
  protected String turnInReference;

  @Schema(
      description =
"""
The equipment handling facility where container is to be returned by the consignee or the appointed logistics partner.
""")
  private Location returnLocation;

  @Schema(
      maxLength = 5000,
      example = "Please place the container...",
      description = "Instructions for returning the empty equipment.")
  private String returnInstructions;
}
