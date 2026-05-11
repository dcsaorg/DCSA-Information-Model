package org.dcsa.standards.specifications.standards.ebl.v303.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
The identifying details of a package or the actual markings that appear on the package(s) for customs filing purposes.
""",
    example = "Made in China",
    maxLength = 35)
public class ShippingMark extends org.dcsa.standards.specifications.standards.dt.v101.types.ShippingMark {}
