package org.dcsa.standards.specifications.standards.core.v103.types;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
  type = "string",
  maxLength = 250,
  example = "123 Example Rd",
  description = "One line of an unstructured `Address`.")
public class AddressLine {}
