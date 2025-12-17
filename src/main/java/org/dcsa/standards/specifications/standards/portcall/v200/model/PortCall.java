package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.UNLocationCode;
import org.dcsa.standards.specifications.standards.core.v102.types.UniversallyUniqueID;

@Data
@Schema(description = "Port call information")
public class PortCall {

  @Schema(description = "Universal unique identifier of the port call")
  private UniversallyUniqueID portCallID;

  @Schema(
      maxLength = 50,
      example = "NLAMS1234589",
      description =
"""
The unique reference that can be used to link different terminal calls to the same port visit.
The reference is provided by the port to uniquely identify a port call.
""")
  private String portVisitReference;

  @Schema(name = "UNLocationCode")
  private UNLocationCode unLocationCode;

  @Schema(
      example = "false",
      description = "Flag indicating whether the port call is omitted by the carrier.")
  private Boolean isOmitted;
}
