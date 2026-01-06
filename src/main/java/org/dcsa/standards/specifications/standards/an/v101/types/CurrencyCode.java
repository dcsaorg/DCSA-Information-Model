package org.dcsa.standards.specifications.standards.an.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    example = "EUR",
    description =
"""
Currency code as defined by ([ISO 4217](https://www.iso.org/iso-4217-currency-codes.html)).
""",
    maxLength = 3)
public class CurrencyCode {}
