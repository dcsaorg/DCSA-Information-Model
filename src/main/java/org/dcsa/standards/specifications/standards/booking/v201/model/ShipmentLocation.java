package org.dcsa.standards.specifications.standards.booking.v201.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
Maps the relationship between `Shipment` and `Location`, e.g., the `Place of Receipt` and the `Place of Delivery` for a specific shipment. This is a reusable object between `Booking` and `Transport Document`

**Condition:** In case `routingReference` is provided - then `PRE` (Place of Receipt), `POL` (Port of Loading), `POD` (Port of Discharge) and `PDE` (Place of Delivery) MUST not be provided.
""")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShipmentLocation extends org.dcsa.standards.specifications.standards.booking.v2.model.ShipmentLocation {}
