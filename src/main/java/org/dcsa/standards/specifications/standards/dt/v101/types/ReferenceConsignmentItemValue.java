package org.dcsa.standards.specifications.standards.dt.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The value of the reference.",
    example = "HHL00103004",
    maxLength = 35,
    pattern = "^\\S(?:.*\\S)?$")
public class ReferenceConsignmentItemValue {}
