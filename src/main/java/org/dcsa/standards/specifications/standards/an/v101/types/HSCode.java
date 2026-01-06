package org.dcsa.standards.specifications.standards.an.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
Used by customs to classify the product being shipped.
The type of HS code depends on country and customs requirements.

More information can be found here: [HS Nomenclature](https://www.wcoomd.org/en/topics/nomenclature/instrument-and-tools).
""",
    example = "851713",
    maxLength = 12)
public class HSCode {}
