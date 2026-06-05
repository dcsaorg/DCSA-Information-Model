package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
`Export License` requirements

**Condition:** Included if the property is provided in the `Shipping Instructions.`
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExportLicense extends org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense {}
