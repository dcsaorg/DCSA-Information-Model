package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.Transport
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Transport
    extends org.dcsa.standards.specifications.standards.booking.v2.model.Transport {

  @Schema(
      description =
"""
The mode of transport as defined by DCSA. The currently supported values include:
- `VESSEL` (Vessel)
- `RAIL` (Rail)
- `TRUCK` (Truck)
- `BARGE` (Barge)
- `RAIL_TRUCK`(Rail and truck)
- `BARGE_TRUCK`(Barge and truck)
- `BARGE_RAIL`(Barge and rail)
- `MULTIMODAL` (if multiple modes are used)
""",
      example = "VESSEL",
      maxLength = 50)
  private String modeOfTransport;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The planned date of arrival.

**Note:** In case no date is available, the date `1970-01-01` should be used.
""",
      example = "2021-05-19",
      format = "date")
  private String plannedArrivalDate;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The planned date of departure.

**Note:** In case no date is available, the date `1970-01-01` should be used.
""",
      example = "2021-05-17",
      format = "date")
  private String plannedDepartureDate;
}
