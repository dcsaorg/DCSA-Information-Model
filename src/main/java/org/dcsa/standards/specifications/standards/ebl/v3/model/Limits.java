package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
Limits for the `Dangerous Goods`. The same `Temperature Unit` needs to apply to all attributes in this structure.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Limits extends org.dcsa.standards.specifications.standards.dt.v100.model.Limits {}
