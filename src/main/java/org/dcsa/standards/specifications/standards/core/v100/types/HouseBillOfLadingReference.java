package org.dcsa.standards.specifications.standards.core.v100.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 20,
    example = "HBOL001",
    description =
"""
Unique number allocated by the Ocean Transport Intermediary (OTI) to the House Bill of Lading.
""")
public class HouseBillOfLadingReference {}
