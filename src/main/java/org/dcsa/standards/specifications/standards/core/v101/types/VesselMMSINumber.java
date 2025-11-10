package org.dcsa.standards.specifications.standards.core.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 9,
    example = "278111222",
    description =
"""
Maritime Mobile Service Identities (MMSIs) are nine-digit numbers used by maritime digital selective calling (DSC),
automatic identification systems (AIS) and certain other equipment to uniquely identify a ship or a coast radio station.
""")
public class VesselMMSINumber {}
