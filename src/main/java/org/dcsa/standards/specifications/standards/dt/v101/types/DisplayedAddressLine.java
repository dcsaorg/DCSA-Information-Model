package org.dcsa.standards.specifications.standards.dt.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "A single address line",
    example = "Strawinskylaan 4117",
    maxLength = 35)
public class DisplayedAddressLine {}
