package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
      org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class PartyAddress extends org.dcsa.standards.specifications.standards.ebl.v3.model.PartyAddress {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
      example = "NL",
      minLength = 2,
      maxLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
