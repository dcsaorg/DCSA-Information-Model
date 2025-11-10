package org.dcsa.standards.specifications.standards.core.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    type = "string",
    format = "uuid",
    example = "3910eb91-8791-4699-8029-8bba8cedb6f5",
    description = "Universally unique ID of an object.")
public class UniversallyUniqueID {}
