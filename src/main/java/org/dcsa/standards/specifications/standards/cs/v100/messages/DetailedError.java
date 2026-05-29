package org.dcsa.standards.specifications.standards.cs.v100.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "A detailed description of what has caused the error.")
@Data
public class DetailedError {

  @Schema(
      description =
"""
The detailed error code returned.

  - `7000-7999` Technical error codes
  - `8000-8999` Functional error codes
  - `9000-9999` API provider-specific error codes

[Error codes as specified by DCSA](https://developer.dcsa.org/standard-error-codes).
""",
      format = "int32",
      minimum = "7000",
      maximum = "9999",
      example = "7003")
  private Integer errorCode;

  @Schema(
      description = "The name of the property causing the error.",
      maxLength = 100,
      example = "facilityCode")
  private String property;

  @Schema(
      description =
          "The value of the property causing the error serialised as a string exactly as in the original request.",
      maxLength = 500,
      example = "SG SIN WHS")
  private String value;

  @Schema(
      description =
          "A path to the property causing the error, formatted according to [JSONpath](https://github.com/json-path/JsonPath).",
      maxLength = 500,
      example = "$.location.facilityCode")
  private String jsonPath;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A standard short description corresponding to the `errorCode`.",
      maxLength = 100,
      example = "invalidData")
  private String errorCodeText;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "A long description corresponding to the `errorCode` with additional information.",
      maxLength = 5000,
      example = "Spaces not allowed in facility code")
  private String errorCodeMessage;
}
