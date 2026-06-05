package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Maps the relationship between `Shipment` and `Location`, e.g., the `Place of Receipt` and the `Place of Delivery` for a specific shipment.

**Condition:** In case `routingReference` is provided - then `PRE` (Place of Receipt), `POL` (Port of Loading), `POD` (Port of Discharge) and `PDE` (Place of Delivery) MUST not be provided.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShipmentLocation
    extends org.dcsa.standards.specifications.standards.booking.v203.model.ShipmentLocation {

  @Schema() private VesselVoyage departingVesselVoyage;
}
