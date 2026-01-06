package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Location;

@Data
@Schema(description = "Consolidates the information required to pick up the container.")
public class PickupInformation {

  @Schema(
      example = "780379",
      description =
"""
A pickup number is a generated number assigned to each import container individually.
A trucker picking up a container must present the correct pickup number.
If the pickup number is incorrect, the trucker will have his pickup request denied.
""",
      maxLength = 50)
  protected String pickupNumber;

  @Schema(
      description =
"""
The equipment handling facility where container is to be picked up by the consignee or the appointed logistics partner.
""")
  private Location pickupLocation;
}
