package org.dcsa.standards.specifications.standards.ebl.v3.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
Used by customs to classify the product being shipped. The type of HS code depends on country and customs requirements. The code must be at least 6 and at most 10 digits.

More information can be found here: [HS Nomenclature](https://www.wcoomd.org/en/topics/nomenclature/instrument-and-tools).
""",
    example = "851713",
    minLength = 6,
    maxLength = 10,
    pattern = "^\\d{6,10}$")
public class HSCode {}
