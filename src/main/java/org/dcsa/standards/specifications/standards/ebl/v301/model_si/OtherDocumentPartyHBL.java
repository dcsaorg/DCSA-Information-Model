package org.dcsa.standards.specifications.standards.ebl.v301.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
A list of document parties that can be optionally provided in the `House B/L`.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OtherDocumentPartyHBL
    extends org.dcsa.standards.specifications.standards.ebl.v3.model_si.OtherDocumentPartyHBL {}

