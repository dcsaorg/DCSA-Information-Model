package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v100.model.ServiceCodeOrReference;
import org.dcsa.standards.specifications.standards.core.v100.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.core.v100.types.ModeOfTransportCode;

@Data
@Schema(description = "Transport call information")
public class TransportCall {

  @Schema(
      description =
"""
Carrier-defined reference of a `TransportCall`.

If the mode of transport is Vessel and the facility is a Port or Terminal,
this reference should be considered a terminal call reference.
""")
  private String transportCallReference;

  @Schema(
      description =
"""
Transport operator's key that uniquely identifies each individual transport call.
This key is essential to distinguish between two separate calls at the same location within one voyage.
""")
  private Integer transportCallSequenceNumber;

  @Schema() private ModeOfTransportCode modeOfTransport;

  @Schema(
      description =
"""
The unique reference that can be used to link different `transportCallReferences` to the same port visit.
The reference is provided by the port to uniquely identify a port call.
""")
  private String portVisitReference;

  @Schema() private ServiceCodeOrReference serviceCodeOrReference;

  @Schema(description = "Export voyage number or reference")
  private VoyageNumberOrReference exportVoyageNumberOrReference;

  @Schema(description = "Import voyage number or reference")
  private VoyageNumberOrReference importVoyageNumberOrReference;

  @Schema() private VesselTransport vesselTransport;

  @Schema() private RailTransport railTransport;

  @Schema() private TruckTransport truckTransport;
}
