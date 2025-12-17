package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v102.types.DelayReasonCode;
import org.dcsa.standards.specifications.standards.core.v102.types.FormattedDateTime;

import java.util.List;

@Data
@Schema(
    description =
"""
Timestamp and associated metadata of a port call service,
exchanged between the service provider and service consumer as part of
[the "ERP" pattern defined by the IMO](https://wwwcdn.imo.org/localresources/en/OurWork/Facilitation/FAL%20related%20nonmandatory%20documents/FAL.5-Circ.52.pdf).
""")
public class Timestamp {

  @Schema(
      maxLength = 3,
      example = "EST",
      description =
"""
Code identifying the type of this timestamp within the context of
[the "ERP" pattern defined by the IMO](https://wwwcdn.imo.org/localresources/en/OurWork/Facilitation/FAL%20related%20nonmandatory%20documents/FAL.5-Circ.52.pdf):
- `ACT` (Actual)
- `EST` (Estimated)
- `PLN` (Planned)
- `REQ` (Requested)

Timestamps of type `EST`, `PLN` and `ACT` are sent by the service provider to the service consumer.

Timestamps of type `REQ` are sent by the service consumer to the service provider.

Timestamps of type `ACT` are relevant in port call services of every type except for `MOVES`.

Timestamps of type `EST`, `REQ` and `PLN` are relevant in port call services of these types:
- `BERTH` (Berth)
- `CARGO_OPERATIONS` (Cargo operations)
- `PILOTAGE` (Pilotage)
- `TOWAGE` (Towage)
- `MOORING` (Mooring)
- `BUNKERING` (Bunkering)
- `PILOT_BOARDING_PLACE` (Pilot Boarding Place)
- `ANCHORAGE` (Anchorage)
- `SLUDGE` (Sludge)
""")
  private String classifierCode;

  @Schema(
      description =
          "The date and time when the service was provided or is expected to be provided.")
  private FormattedDateTime serviceDateTime;

  @Schema(description = "Codes identifying the reasons for a delay")
  private List<DelayReasonCode> delayReasonCodes;

  @Schema(
      maxLength = 500,
      example = "Port closed due to strike",
      description = "Free text used to provide any relevant additional information.")
  private String remark;
}
