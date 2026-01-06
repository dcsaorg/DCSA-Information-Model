package org.dcsa.standards.specifications.standards.an.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description = "Purchase order reference",
    example = "PO1234",
    maxLength = 35)
public class PurchaseOrderReference {}
