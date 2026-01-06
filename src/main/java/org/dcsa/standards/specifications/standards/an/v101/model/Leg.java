package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.an.v101.types.TransportPlanStageCode;
import org.dcsa.standards.specifications.standards.core.v101.model.ClassifiedDate;
import org.dcsa.standards.specifications.standards.core.v101.model.Location;
import org.dcsa.standards.specifications.standards.core.v101.types.ModeOfTransportCode;

@Data
@Schema(description = "Details of one specific leg of the transport plan")
public class Leg {

  @Schema() private TransportPlanStageCode transportPlanStage;

  @Schema(
      type = "integer",
      format = "int32",
      description = "Sequence number of the transport plan stage",
      example = "5")
  protected int transportPlanStageSequenceNumber;

  @Schema(description = "The location where the cargo is loaded for this specific leg of the transport plan.")
  private Location loadLocation;

  @Schema(description = "The location where the cargo is discharged for this specific leg of the transport plan.")
  private Location dischargeLocation;

  @Schema(description = "The departure date from the load location for this specific leg of the transport plan.")
  private ClassifiedDate departureDate;

  @Schema(description = "The arrival date at the discharge location for this specific leg of the transport plan.")
  private ClassifiedDate arrivalDate;

  @Schema() ModeOfTransportCode modeOfTransport;

  @Schema() private VesselVoyage vesselVoyage;
}
