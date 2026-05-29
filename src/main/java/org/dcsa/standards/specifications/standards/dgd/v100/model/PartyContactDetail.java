package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Party contact details")
@Data
public class PartyContactDetail {

  @Schema(description = "Contact email address", example = "jane.doe@example.com", maxLength = 100)
  private String email;

  @Schema(description = "Contact name", example = "Jane Doe", maxLength = 100)
  private String name;

  @Schema(
      description =
"""
Contact phone number, including an international phone number format
as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).
""",
      example = "+14155552671",
      maxLength = 30)
  private String phone;
}
