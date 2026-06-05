package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
An object used to express a location using a `Facility`. The facility can be expressed using one of `BIC` code or `SMDG` code. The `facilityCode` does not contain the `UNLocationCode` - this should be provided in the `UnLocationCode` attribute.
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Facility extends org.dcsa.standards.specifications.standards.dt.v100.model.Facility {}
