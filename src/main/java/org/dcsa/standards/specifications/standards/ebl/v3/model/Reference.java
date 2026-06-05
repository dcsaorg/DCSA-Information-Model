package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
References provided by the shipper or freight forwarder at the time of `Booking` or at the time of providing `Shipping Instructions`. Carriers share it back when providing `Track & Trace` event updates, some are also printed on the B/L. Customers can use these references to track shipments in their internal systems.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Reference extends org.dcsa.standards.specifications.standards.dt.v100.model.Reference {}
