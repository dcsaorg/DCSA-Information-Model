package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.ConfirmedEquipment
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ConfirmedEquipment
    extends org.dcsa.standards.specifications.standards.booking.v2.model.ConfirmedEquipment {

  @Schema private OriginEmptyContainerPickup originEmptyContainerPickup;
}
