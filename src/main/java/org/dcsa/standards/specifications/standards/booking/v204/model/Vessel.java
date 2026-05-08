package org.dcsa.standards.specifications.standards.booking.v204.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.booking.v2.model.Vessel
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Vessel extends org.dcsa.standards.specifications.standards.booking.v2.model.Vessel {

  @Schema(
      description =
          "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code, which does not change during the lifetime of the vessel",
      example = "9321483",
      maxLength = 8,
      minLength = 7,
      pattern = "^\\d{7,8}$")
  private String vesselIMONumber;
}
