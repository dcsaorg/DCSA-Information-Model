package org.dcsa.standards.specifications.standards.an.v100.model;

import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description =
"""
The contact details of the person to contact.
Provide either phone and/or email along with the name; both may be provided.
""")
@ClearSchemaConstraints
public class PartyContactDetail
    extends org.dcsa.standards.specifications.standards.dt.v100.model.PartyContactDetail {

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
