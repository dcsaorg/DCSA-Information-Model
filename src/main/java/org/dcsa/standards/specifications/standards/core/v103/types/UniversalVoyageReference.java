package org.dcsa.standards.specifications.standards.core.v103.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 5,
    example = "2301W",
    description =
"""
Unique identifier of the voyage within a service, assigned by carriers as specified by DCSA.

Carriers assign a Universal Voyage Reference (UVR) to a voyage,
either individually for non-VSA services or jointly for shared services under a VSA or Alliance.

Every Universal Voyage Reference is a 5-character string with the following format:
 * the last 2 digits of the year
 * 2 alphanumeric characters for the sequence number of the voyage
 * 1 character for the initial of the direction/haul (`N`orth, `E`ast, `W`est, `S`outh or `R`oundtrip).
""")
public class UniversalVoyageReference {}
