package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Rail transport information")
@Data
public class RailTransport {

  @Schema(
      maxLength = 50,
      description =
"""
Reference of the rail car on which the equipment is transported.
""")
  private String railCarReference;

  @Schema(
      maxLength = 50,
      description =
"""
Reference of the rail service on which the equipment is transported.
""")
  private String railServiceReference;

  @Schema(
      maxLength = 100,
      description =
"""
Unique identifying number or code that is assigned to a specific departure of a rail.
(Also known as a departure reference number.)
""")
  private String departureID;
}
