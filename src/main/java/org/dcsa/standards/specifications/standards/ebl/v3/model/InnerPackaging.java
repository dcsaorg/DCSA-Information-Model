package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Object for inner packaging specification
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InnerPackaging extends org.dcsa.standards.specifications.standards.dt.v100.model.InnerPackaging {}
