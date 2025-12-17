package org.dcsa.standards.specifications.standards.core.v102.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    maxLength = 8,
    example = "SR12345A",
    description =
"""
Globally unique identifier of a liner service, assigned by carriers as specified by DCSA.

DCSA generates and distributes batches of Universal Service References (USRs)
to member and non-member carriers upon request, for their own use or for their alliances and VSAs.

A carrier can use each of its assigned USRs either in agreement with its partners for a service under a VSA or Alliance,
or independently for one of its own non-VSA, non-Alliance services.

Every Universal Service Reference is an 8-character string with the following format:
 * the prefix "SR"
 * 5 digits
 * 1 uppercase English letter (A-Z).
""")
public class UniversalServiceReference {}
