package org.dcsa.standards.specifications.standards.ebl.v3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "The contact details of the person to contact. It is mandatory to provide either `phone` and/or `email` along with the `name`, both can be provided.",
    title = "Party Contact Detail (with phone pattern)")
@Data
public class PartyContactDetailWithPattern {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the contact",
      example = "Henrik",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String name;

  @Schema(
      description =
          "Phone number for the contact. Phone **MUST** include an international phone number format as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).",
      example = "+45 70262970",
      pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$",
      maxLength = 30)
  private String phone;

  @Schema(
      description = "`E-mail` address to be used",
      example = "info@dcsa.org",
      pattern = "^.+@\\S+$",
      maxLength = 100)
  private String email;
}
