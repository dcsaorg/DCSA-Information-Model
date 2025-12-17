package org.dcsa.standards.specifications.standards.core.v102.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 11,
    example = "APZU4812090",
    description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.

According to ISO 6346, a container identification code consists of a 4-letter prefix and a 7-digit number
(composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with ISO 6346, it is suggested to follow
[Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations)
from SMDG.
""")
public class EquipmentReference {}
