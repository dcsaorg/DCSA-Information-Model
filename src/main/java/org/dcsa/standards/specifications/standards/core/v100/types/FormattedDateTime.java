package org.dcsa.standards.specifications.standards.core.v100.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    type = "string",
    format = "date-time",
    example = "2025-01-23T01:23:45Z",
    maxLength = 30,
    description =
"""
Timestamp representation [in RFC 3339 format](https://swagger.io/docs/specification/v3_0/data-models/data-types/#strings)
""")
public class FormattedDateTime {}
