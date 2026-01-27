package org.dcsa.standards.specifications.standards.ebl.v302.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
  type = "string",
  minLength = 2,
  maxLength = 2,
  example = "NL",
  pattern = "^[A-Z]{2}$",
  description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)
""")
public class RoutingOfConsignmentCountryCode {}
