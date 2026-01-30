package org.dcsa.standards.specifications.standards.ebl.v3.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The content of the clause.",
    example = "It is not allowed to...",
    pattern = "^\\S(?:.*\\S)?$",
    maxLength = 20000)
public class CarrierClause {}
