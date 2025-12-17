package org.dcsa.standards.specifications.standards.core.v102.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 3,
    example = "STR",
    description =
"""
Code identifying the reason for a delay,
[as defined by SMDG](https://smdg.org/documents/smdg-code-lists/delay-reason-and-port-call-activity/).
""")
public class DelayReasonCode {}
