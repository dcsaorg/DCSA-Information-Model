package org.dcsa.standards.specifications.standards.ebl.v303.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "A line describing the cargo for filing purposes",
    example = "Smartphones for wireless networks",
    maxLength = 35)
public class DescriptionOfGoodsForCustomsLine {}
