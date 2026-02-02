package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = AdvanceManifestFiling.CLASS_SCHEMA_DESCRIPTION)
@Data
public class AdvanceManifestFiling {

  public static final String CLASS_SCHEMA_DESCRIPTION = "An Advance Manifest Filing defined by a Manifest type code in combination with a country code.\n\nA small list of **potential** examples:\n\n| manifestTypeCode | countryCode | Description |\n|-----------------------|:-------------:|-------------|\n|ACI|EG|Advance Cargo Information in Egypt|\n|ACE|US|Automated Commercial Environment in the United States|\n|AFR|JP|Cargo Summary Notification (CSN)|";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The Manifest type code as defined by the provider.",
      example = "ACE",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String manifestTypeCode;

  @Schema(
      description = "The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)",
      example = "US",
      maxLength = 2,
      minLength = 2,
      pattern = "^[A-Z]{2}$")
  private String countryCode;
}
