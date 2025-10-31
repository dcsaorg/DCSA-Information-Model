package org.dcsa.standards.specifications.standards.dt.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = EmergencyContactDetails.CLASS_SCHEMA_DESCRIPTION)
@Data
public class EmergencyContactDetails {

  public static final String CLASS_SCHEMA_DESCRIPTION = "24 hr emergency contact details";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Name of the Contact person during an emergency.",
      example = "Henrik Larsen",
      maxLength = 255)
  protected String contact;

  @Schema(
      description = "Name of the third party vendor providing emergency support",
      example = "GlobeTeam",
      maxLength = 255)
  protected String provider;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Phone number for the contact. Phone **MUST** include an international phone number format as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).
""",
      example = "+45 70262970",
      maxLength = 30,
      pattern = "^\\S(?:.*\\S)?$")
  protected String phone;

  @Schema(
      description =
          "Contract reference for the emergency support provided by an external third party vendor.",
      example = "12234",
      maxLength = 255)
  protected String referenceNumber;
}
