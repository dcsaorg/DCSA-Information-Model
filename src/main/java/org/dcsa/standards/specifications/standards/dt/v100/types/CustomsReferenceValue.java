package org.dcsa.standards.specifications.standards.dt.v100.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The value of the `customsReference`",
    example = "4988470982020120017",
    maxLength = 35,
    pattern = "^\\S(?:.*\\S)?$")
public class CustomsReferenceValue {}
