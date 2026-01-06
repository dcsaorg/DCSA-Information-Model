package org.dcsa.standards.specifications.standards.dt.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    type = "string",
    description =
"""
The identifying details of a package or the actual markings that appear on the package(s). This information is provided by the customer.
""",
    example = "Made in China",
    maxLength = 35)
public class ShippingMark {}
