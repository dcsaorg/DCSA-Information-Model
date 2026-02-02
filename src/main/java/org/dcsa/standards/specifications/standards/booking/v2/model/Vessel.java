package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = Vessel.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Vessel {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Vessels related to this booking request.\n\n**Condition:** Mandatory if `carrierExportVoyageNumber` is provided and `carrierServiceCode` or `carrierServiceName` are blank.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The name of the Vessel given by the Vessel Operator and registered with IMO.",
      example = "King of the Seas",
      maxLength = 50,
      pattern = "^\\S(?:.*\\S)?$")
  private String name;

  @Schema(
      description = "The unique reference for a registered Vessel. The reference is the International Maritime Organisation (IMO) number, also sometimes known as the Lloyd&apos;s register code, which does not change during the lifetime of the vessel",
      example = "9321483",
      maxLength = 8,
      minLength = 7,
      pattern = "^\\d{7,8}$")
  private String vesselIMONumber;
}
