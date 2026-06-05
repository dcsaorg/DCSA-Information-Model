package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
"""
An Advance Manifest Filing defined by a Manifest type code in combination with a country code.

A small list of **potential** examples:

| manifestTypeCode | countryCode | Description |
|-----------------------|:-------------:|-------------|
|ACI|EG|Advance Cargo Information in Egypt|
|ACE|US|Automated Commercial Environment in the United States|
|AFR|JP|Cargo Summary Notification (CSN)|
|ENS|NL|Entry Summary Declaration|
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class AdvanceManifestFiling
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.AdvanceManifestFiling {

  @Schema(
    description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

**Note:** In case the `countryCode` is unknown it is possible to use the code `ZZ`.
""",
    example = "NL",
    pattern = "^[A-Z]{2}$",
    minLength = 2,
    maxLength = 2)
  private String countryCode;
}
