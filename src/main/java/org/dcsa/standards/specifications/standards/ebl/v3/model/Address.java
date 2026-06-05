package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(
    description =
"""
An object for storing address related information
""")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Address extends org.dcsa.standards.specifications.standards.dt.v100.model.Address {}
