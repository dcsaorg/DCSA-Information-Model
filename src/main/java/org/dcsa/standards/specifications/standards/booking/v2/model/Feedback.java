package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Feedback.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Feedback {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Feedback that can be provided includes, but is not limited to:\n- unsupported properties\n- changed values\n- removed properties\n- general information";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
The severity of the feedback. Possible values are:
- `INFO` (Information - "Your reefer container will use renewable energy", "This earlier / premium service is available")
- `WARN` (Warning - "I'm going to replace" / "Ignore this value" / "Use another value instead")
- `ERROR` (Error - "This must be changed!")
""",
      example = "WARN",
      maxLength = 50)
  private String severity;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
A code used to describe the feedback. Possible values are:
- `INFORMATIONAL_MESSAGE` (INFO - to be used when providing extra information)
- `PROPERTY_WILL_BE_IGNORED` (WARN - to be used for unsupported properties/values)
- `PROPERTY_VALUE_MUST_CHANGE` (ERROR - to be used when a wrong property/value is provided)
- `PROPERTY_VALUE_HAS_BEEN_CHANGED` (WARN - when something has been auto-updated without consumer intervention)
- `PROPERTY_VALUE_MAY_CHANGE` (WARN - when something is likely to change in the future)
- `PROPERTY_HAS_BEEN_DELETED` (WARN - when something has been auto-deleted without consumer intervention)
""",
      example = "PROPERTY_WILL_BE_IGNORED",
      maxLength = 50)
  private String code;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A description with additional information.",
      example = "Spaces not allowed in facility code",
      maxLength = 5000)
  private String message;

  @Schema(
      description = "A path to the property, formatted according to [JSONpath](https://github.com/json-path/JsonPath).",
      example = "$.location.facilityCode",
      maxLength = 500)
  private String jsonPath;

  @Schema(
      description = "The name of the property causing the error/warning.",
      example = "facilityCode",
      maxLength = 100)
  private String property;
}
