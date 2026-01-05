package org.dcsa.standards.specifications.standards.core.v103.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "The contact details of a person.")
@Data
public class ContactDetails {

  @Schema(description = "Contact name", example = "Jane Doe", maxLength = 255)
  protected String name;

  @Schema(
      description =
"""
Contact phone number, including an international phone number format
as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).
""",
      example = "+14155552671",
      maxLength = 30)
  protected String phone;

  @Schema(description = "Contact email address", example = "jane.doe@example.com", maxLength = 255)
  protected String email;
}
