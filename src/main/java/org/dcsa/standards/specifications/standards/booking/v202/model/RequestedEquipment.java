package org.dcsa.standards.specifications.standards.booking.v202.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.RequestedEquipment
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class RequestedEquipment
    extends org.dcsa.standards.specifications.standards.booking.v2.model.RequestedEquipment {

  @Schema(
      format = "date-time",
      description =
          "The date and time when the loaded container(s) need to be picked up from the `Place of Receipt`, if provided.",
      example = "2025-05-16T09:14:00Z")
  private String fullContainerPickupDateTime;

  @Schema private OriginEmptyContainerPickup originEmptyContainerPickup;
}
