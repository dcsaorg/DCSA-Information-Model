package org.dcsa.standards.specifications.standards.ebl.v3.model_iss;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
The company or a legal entity issuing the `Transport Document`
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IssuingParty extends org.dcsa.standards.specifications.standards.ebl.v3.model_td.IssuingParty {}
