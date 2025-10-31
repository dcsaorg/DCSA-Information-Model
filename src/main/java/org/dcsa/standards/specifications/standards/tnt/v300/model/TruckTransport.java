package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Truck transport information")
@Data
public class TruckTransport {

  @Schema(
      description =
"""
The license plate and VIN of the tractor of the truck used to transport the equipment.
""")
  private VehicleDetails tractorDetails;

  @Schema(
      description =
"""
The license plate and VIN of the trailer of the truck on which the equipment is transported,
if the trailer is separate from the truck trailer and has its own details.
""")
  private VehicleDetails trailerDetails;
}
