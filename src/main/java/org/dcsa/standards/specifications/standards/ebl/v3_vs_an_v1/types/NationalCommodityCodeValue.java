package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "The value of the `National Commodity Code`",
    example = "1515",
    maxLength = 10,
    pattern = "^\\S(?:.*\\S)?$")
public class NationalCommodityCodeValue {}
