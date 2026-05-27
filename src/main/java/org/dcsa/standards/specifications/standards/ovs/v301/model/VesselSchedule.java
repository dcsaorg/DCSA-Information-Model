package org.dcsa.standards.specifications.standards.ovs.v301.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Vessel schedule information including transport calls.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VesselSchedule
    extends org.dcsa.standards.specifications.standards.ovs.v300.model.VesselSchedule {

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
      name = "MMSINumber",
      description =
"""
Maritime Mobile Service Identities (MMSIs) are nine-digit numbers used by maritime digital selective calling (DSC), automatic identification systems (AIS) and certain other equipment to uniquely identify a ship or a coast radio station.

**Condition:** If `isDummyVessel` is `false` - at least one of `vesselIMONumber` or `MMSINumber` **MUST** be specified in order to identify the `Vessel`. It is also acceptable to provide both properties.  If `isDummyVessel` is `true` it is optional to provide this property.

**Condition:** It is only possible to use this property in case the requester is on version `3.0.1` or later or `vesselIMONumber` is also provided. In case the requester is on version `3.0.0` (indicated by the `APIVersion` header being blank or containing the value `3`) - this property cannot be used unless `vesselIMONumber` is also present.
""",
      pattern = "^\\d{9}$",
      minLength = 9,
      maxLength = 9,
      example = "278111222")
  private String mmsiNumber;

  @Schema(
      description =
"""
The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel

**Condition:** If `isDummyVessel` is `false` - at least one of `vesselIMONumber` or `MMSINumber` **MUST** be specified in order to identify the `Vessel`. It is also acceptable to provide both properties. If `isDummyVessel` is `true` it is optional to provide this property.

**Condition:** Please note that if the requester is on a version `3.0.0` (indicated by the `APIVersion` being blank or containing the value `3`) and it is not a dummy vessel (`isDummyVessel: false`) then this property **MUST** be present.
""",
      pattern = "^\\d{7}$",
      maxLength = 7,
      example = "9321483")
  private String vesselIMONumber;

  @Schema(
      description =
"""
The name of the Vessel given by the Vessel Operator and registered with IMO.

**Note:** In case the vessel is a "dummy vessel" (`isDummyVessel='true'`) then the following recommendations should be followed:

Dummy vessel names should begin with the **SMDG Operating Carrier Code** to ensure uniqueness across carriers.
The suffix can be **alphanumeric** and up to **10 characters long**, allowing each carrier to use internal naming conventions (e.g., `MSKTBN1`, `CMATEMP01`, `MSCX01A`).

This approach:
- Ensures consistency and uniqueness across carriers
- Allows flexibility in local implementations
- Avoids the need to assign dummy IMO numbers
- Maintains the role of the dummy vessel name as a **stable and persistent identifier** throughout the planning process
""",
      maxLength = 35,
      example = "King of the Seas")
  private String vesselName;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Is this a dummy vessel? In case no vessel has been assigned yet - this property can be set to `true` indicating that the `vesselIMONumber`/`MMSINumber` does not exist.

When `true`, the `vesselName` must be used as a unique identifier for the dummy vessel. See the `vesselName` field description for the recommended naming convention.
""")
  private Boolean isDummyVessel;
}
