package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "A line of the address to be displayed on the transport document.",
    example = "Strawinskylaan 4117",
    maxLength = 35)
public class DisplayedName {}
