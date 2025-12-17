package org.dcsa.standards.specifications.standards.core.v102.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 8,
    example = "9321483",
    description =
"""
The unique reference for a registered vessel, which remains unchanged throughout the entire lifetime of the vessel.
The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd's register code.
""")
public class VesselIMONumber {}
