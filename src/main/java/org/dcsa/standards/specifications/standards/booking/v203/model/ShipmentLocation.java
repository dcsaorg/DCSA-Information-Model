package org.dcsa.standards.specifications.standards.booking.v203.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.ShipmentLocation
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShipmentLocation
    extends org.dcsa.standards.specifications.standards.booking.v2.model.ShipmentLocation {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Links to the Location Type Code defined by DCSA. Possible values are:
- `PRE` (Place of Receipt)
- `POL` (Port of Loading)
- `POD` (Port of Discharge)
- `PDE` (Place of Delivery)
- `PCF` (Pre-carriage From)
- `OIR` (Onward In-land Routing)
- `ORI` (Origin of goods)
- `IEL` (Container intermediate export stop off location)
- `PTP` (Prohibited transshipment port)
- `RTP` (Requested transshipment port)
- `FCD` (Full container drop-off location)
- `ROU` (Routing Reference)

**Note:** Use `ROU` when `routingReference` is used in order to comply with the need to add at least 1 `shipmentLocation` to a Booking request.
""",
      example = "PRE",
      maxLength = 3)
  private String locationTypeCode;
}
