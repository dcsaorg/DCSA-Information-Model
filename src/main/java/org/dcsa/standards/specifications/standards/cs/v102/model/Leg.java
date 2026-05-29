package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.standards.cs.v101.model.CutOffTime;

@Schema(
    description =
        "Leg of the Point-to-Point routing. The property `Leg` can be conformed by as many leg as needed and this leg must be identified with a sequence number.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Leg extends org.dcsa.standards.specifications.standards.cs.v100.model.Leg {

  @Schema(
      description = "The mode of transport as defined by DCSA.",
      oneOf = {VesselTransport.class, BargeTransport.class, OtherTransport.class})
  private Object transport;

  @Schema(description = "Carbon footprint emission values for this particular leg.")
  private Footprint footprint;

  @Schema(
      description =
          "A list of cut-offs times provided by the carrier when available for this particular leg. A cut-off time indicates the latest date and time by which a task must be completed. For example, the latest date and time by which certain documentation must be provided by the Shipper.")
  private List<CutOffTime> cutOffTimes;
}
