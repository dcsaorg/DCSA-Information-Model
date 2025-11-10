package org.dcsa.standards.specifications.standards.core.v101.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
  type = "string",
  maxLength = 2,
  example = "NL",
  description =
"""
The 2 characters for the country code using [ISO 3166-1 alpha-2](https://www.iso.org/obp/ui/#iso:pub:PUB500001:en)

When the country code is required but not applicable, use the code `ZZ`.
""")
public class CountryCode {}
