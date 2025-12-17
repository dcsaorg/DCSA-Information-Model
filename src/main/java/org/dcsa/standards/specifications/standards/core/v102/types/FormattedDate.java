package org.dcsa.standards.specifications.standards.core.v102.types;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    type = "string",
    format = "date",
    example = "2025-01-23",
    maxLength = 10,
    description =
"""
String representation of a date in yyyy-MM-dd format.
Unless otherwise specified, it is a local date in the time zone of the implicitly associated event location.
""")
public class FormattedDate {}
