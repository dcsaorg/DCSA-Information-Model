package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Addresses the seal-related information associated with the shipment equipment. A seal is put on a shipment equipment once it is loaded. This `Seal` is meant to stay on until the shipment equipment reaches its final destination.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Seal extends org.dcsa.standards.specifications.standards.dt.v100.model.Seal {}
