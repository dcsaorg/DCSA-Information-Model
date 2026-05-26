package org.dcsa.standards.specifications.standards.ovs.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "A timestamp for a port.")
@Data
public class Timestamp {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Identifier for type of `transportEvent`

- ARRI (Arrived)
- DEPA (Departed)

More details can be found on [GitHub](https://github.com/dcsaorg/DCSA-OpenAPI/blob/master/ovs/v3/reference-data/transporteventtypecodes-v300.csv).
""",
      allowableValues = {"ARRI", "DEPA"})
  private String eventTypeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code for the event classifier. Values can vary depending on eventType.

Possible values are:
- ACT (Actual)
- EST (Estimated)
- PLN (Planned)
""",
      allowableValues = {"PLN", "EST", "ACT"})
  private String eventClassifierCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Time in the timestamp",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String eventDateTime;

  @Schema(
      description =
          "Reason code for the delay. See SMDG [Code list DELAY](https://smdg.org/documents/smdg-code-lists/delay-reason-and-port-call-activity/) for a list of valid codes to be used for this attribute.",
      maxLength = 3,
      example = "WEA")
  private String delayReasonCode;

  @Schema(
      description =
          "Free text field to provide information as to why the `TransportEvent` was sent.",
      maxLength = 250,
      example = "Bad weather")
  private String changeRemark;
}
