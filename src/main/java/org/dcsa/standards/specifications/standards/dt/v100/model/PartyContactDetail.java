package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "The contact details of the person to contact. It is mandatory to provide either `phone` and/or `email` along with the `name`, both can be provided.")
@Data
public class PartyContactDetail {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the contact",
      example = "Henrik",
      maxLength = 35,
      pattern = "^\\S(?:.*\\S)?$")
  protected String name;

  @Schema(
      description =
"""
Phone number for the contact. Phone **MUST** include an international phone number format as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).
""",
      example = "+45 70262970",
      maxLength = 30,
      pattern = "^\\S(?:.*\\S)?$")
  protected String phone;

  @Schema(
      description = "`E-mail` address to be used",
      example = "info@dcsa.org",
      maxLength = 100,
      pattern = "^.+@\\S+$")
  protected String email;
}
