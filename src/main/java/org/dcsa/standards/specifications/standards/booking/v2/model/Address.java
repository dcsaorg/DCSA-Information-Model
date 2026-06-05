package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
An object for storing address related information
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Address extends org.dcsa.standards.specifications.standards.dt.v100.model.Address {

  @Schema(description = "The post code.", example = "1047 HM", maxLength = 10)
  protected String postCode;
}
