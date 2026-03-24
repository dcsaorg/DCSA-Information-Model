package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.model.ServiceCodeOrReference;
import org.dcsa.standards.specifications.standards.core.v200.model.VoyageNumberOrReference;
import org.dcsa.standards.specifications.standards.core.v200.types.ModeOfTransport;

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

  @Schema(
    description =
"""
The maximum `transportCallSequenceNumber` value to be expected within the set of events emitted as a result of the transport plan change that has triggered this event.
""")
  private Integer transportCallSequenceNumberMax;

  @Schema() private ModeOfTransport modeOfTransport;

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
