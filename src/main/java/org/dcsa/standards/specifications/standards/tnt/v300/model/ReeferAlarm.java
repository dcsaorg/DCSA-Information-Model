package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v200.types.FormattedDateTime;

@Schema(description = "Reefer container alarm")
@Data
public class ReeferAlarm {

  @Schema(maxLength = 50, description = "Code of the alarm, as defined typically by the device manufacturer")
  private String code;

  @Schema(maxLength = 500, description = "Free-text description of the alarm")
  private String description;

  @Schema(description = "The date and time when the alarm became active")
  private FormattedDateTime startDateTime;

  @Schema(description = "The date and time when the alarm became inactive")
  private FormattedDateTime endDateTime;
}
