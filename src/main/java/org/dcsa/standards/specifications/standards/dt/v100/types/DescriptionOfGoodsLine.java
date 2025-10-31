package org.dcsa.standards.specifications.standards.dt.v100.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "A line describing the cargo",
    example = "blue shoes size 47",
    maxLength = 35,
    pattern = "^\\S(?:.*\\S)?$")
public class DescriptionOfGoodsLine {}
