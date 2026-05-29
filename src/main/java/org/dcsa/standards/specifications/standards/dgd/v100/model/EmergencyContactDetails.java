package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "24 hr emergency contact details")
@Data
public class EmergencyContactDetails {

  @Schema(
      description = "Name of the contact person during an emergency.",
      example = "Jane Doe",
      maxLength = 100)
  private String contact;

  @Schema(
      description =
"""
Phone number for the contact, including an international phone number format
as defined in the [ITU-T recommendation E.123](https://www.itu.int/rec/T-REC-E.123/en).
""",
      example = "+14155552671",
      maxLength = 30)
  private String phone;

  @Schema(
      description = "Name of the third party vendor providing emergency support",
      example = "ACME Inc",
      maxLength = 100)
  private String provider;

  @Schema(
      description =
          "Contract reference for the emergency support provided by an external third party vendor.",
      example = "12234",
      maxLength = 100)
  private String referenceNumber;
}
