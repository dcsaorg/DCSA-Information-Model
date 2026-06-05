package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
public class VesselSchedule {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The carrier who is in charge of the vessel operation based on the SMDG code.
If not available at the moment of sharing the schedule, use `TBD` (To be defined).
In the case an operator is still using a code not from SMDG, use the available code.
""",
      maxLength = 10,
      example = "HLC")
  private String vesselOperatorSMDGLinerCode;

  @Schema(
      description =
"""
The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel

Condition: If the vessel is not dummy, there needs to be an IMO. If the vessel is dummy, the IMO is optional.
""",
      pattern = "^\\d{7}$",
      maxLength = 7,
      example = "9321483")
  private String vesselIMONumber;

  @Schema(
      description = "The name of the Vessel given by the Vessel Operator and registered with IMO.",
      maxLength = 35,
      example = "King of the Seas")
  private String vesselName;

  @Schema(
      description =
"""
A unique alphanumeric identity that belongs to the vessel and is assigned by the International Telecommunication Union (ITU). It consists of a threeletter alphanumeric prefix that indicates nationality, followed by one to four characters to identify the individual vessel. For instance, vessels registered under Denmark are assigned the prefix ranges 5PA-5QZ, OUAOZZ, and XPA-XPZ. The Call Sign changes whenever a vessel changes its flag.
""",
      maxLength = 10,
      example = "NCVV")
  private String vesselCallSign;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Is this a dummy vessel. In case no vessel has been asigned yet - this property can be set to `true` indicating that the vesselIMONumber does not exist.
""")
  private Boolean isDummyVessel;

  @Schema() private List<TransportCall> transportCalls;
}
