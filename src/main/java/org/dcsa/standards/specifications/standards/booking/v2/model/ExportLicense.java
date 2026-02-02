package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(
    description =
        org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense
            .CLASS_SCHEMA_DESCRIPTION)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExportLicense
    extends org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense {

  @Schema(
      description = "Expiry date of the `Export License` applicable to the booking.",
      example = "2024-09-21",
      format = "date")
  protected String expiryDate;

  @Schema(
    description = "Issue date of the `Export License` applicable to the booking.",
    example = "2024-09-14",
    format = "date")
  protected String issueDate;
}
