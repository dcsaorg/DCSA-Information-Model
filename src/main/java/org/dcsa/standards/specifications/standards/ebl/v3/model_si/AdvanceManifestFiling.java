package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
      AdvanceManifestFiling.CLASS_SCHEMA_DESCRIPTION,
    title = "Advance Manifest Filing")
@Data
public class AdvanceManifestFiling {

  public static final String CLASS_SCHEMA_DESCRIPTION = """
    An Advance Manifest Filing defined by a Manifest type code in combination with a country code.

    A small list of **potential** examples:

    | manifestTypeCode | countryCode | Description |
    |-----------------------|:-------------:|-------------|
    |ACI|EG|Advance Cargo Information in Egypt|
    |ACE|US|Automated Commercial Environment in the United States|
    |AFR|JP|Cargo Summary Notification (CSN)|
    |ENS|NL|Entry Summary Declaration|
    """;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The Manifest type code as defined by the provider.",
      example = "ENS",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 50)
  private String manifestTypeCode;

  @Schema(
      description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
""",
      example = "NL",
      pattern = "^[A-Z]{2}$",
      minLength = 2,
      maxLength = 2)
  private String countryCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Indicates whether the shipper (`SELF`) will perform the advance manifest filing(s) for the House BL directly or if the carrier (`CARRIER`) should file them on their behalf. Allowed values are:

- `SELF` (filing done by the House filer)
- `CARRIER` (the carrier does the filing)

In case of self-filing (`SELF`), the shipper can provide their `selfFilerCode` for each manifest.

**Condition:** The `selfFilerCode` must be provided when `manifestTypeCode` is one of `ACE` (US) or `ACI` (CA) and the `advanceManifestFilingsHouseBLPerformedBy` is set to `SELF`.
""",
      example = "SELF",
      allowableValues = {"SELF", "CARRIER"})
  private String advanceManifestFilingsHouseBLPerformedBy;

  @Schema(
      description =
"""
Code identifying the party who will submit the advance manifest filing(s) for the House BL.

**Mandatory** if `manifestTypeCode` is one of `ACE` (US) or `ACI` (CA) and `advanceManifestFilingsHouseBLPerformedBy` is set to `SELF`.
""",
      example = "FLXP",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 100)
  private String selfFilerCode;

  @Schema(
      description =
"""
An identification number of the house filer responsible for submitting the `Advance Manifest Filing`.

**Condition:** Mandatory if `manifestTypeCode` is `ENS` and `advanceManifestFilingsHouseBLPerformedBy` is `SELF`.
""",
      example = "FLXP-123321",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 17)
  private String identificationNumber;
}
