package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v203.model.ShipmentLocation
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShipmentLocation
    extends org.dcsa.standards.specifications.standards.booking.v203.model.ShipmentLocation {

  @Schema() private VesselVoyage departingVesselVoyage;
}
