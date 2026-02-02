package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.Charge.CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Charge extends org.dcsa.standards.specifications.standards.dt.v100.model.Charge {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The currency for the charge, using a 3-character code according to [ISO 4217](https://www.iso.org/iso-4217-currency-codes.html).
""",
      example = "DKK",
      minLength = 3,
      maxLength = 3,
      pattern = "^[A-Z]{3}$")
  protected String currencyCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
An indicator of whether a charge is prepaid (PRE) or collect (COL). When prepaid, the charge is the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charge is the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).
- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  protected String paymentTermCode;
}
