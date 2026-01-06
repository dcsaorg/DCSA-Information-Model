package org.dcsa.standards.specifications.standards.an.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The content of the clause.",
    example = "It is not allowed to...",
    maxLength = 20000)
public class CarrierClause {}
