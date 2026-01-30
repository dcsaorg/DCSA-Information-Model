package org.dcsa.standards.specifications.standards.ebl.v3_vs_an_v1.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "A purchase order reference linked to the `Consignee`.",
    example = "HHL007",
    maxLength = 35,
    pattern = "^\\S(?:.*\\S)?$")
public class ConsigneePurchaseOrderReference {}
