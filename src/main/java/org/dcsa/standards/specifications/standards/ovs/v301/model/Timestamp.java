package org.dcsa.standards.specifications.standards.ovs.v301.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "A timestamp for a port.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Timestamp
    extends org.dcsa.standards.specifications.standards.ovs.v300.model.Timestamp {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Identifier for type of `transportEvent`
- `ARRI` (Arrived)
- `DEPA` (Departed)
""",
      allowableValues = {"ARRI", "DEPA"},
      example = "ARRI")
  private String eventTypeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code for the event classifier. Values can vary depending on eventType.

- **Actual:** The **recorded time** when the event has actually occurred.
- **Estimated:** The updated schedule time based on the **latest regional or coastal schedule (RS/CS)**. It reflects the most up-to-date estimated time as the voyage progresses and can be updated (weekly or daily) several times after the voyage start.
- **Planned:** The scheduled time based on the **long-term service plan** (LTS), published well in advance (typically 12-14 weeks) before the voyage begins, and showing when an event *is planned* to occur.

More information can be found in the OVS Definitions document. Possible value codes:
- `ACT` (Actual)
- `EST` (Estimated)
- `PLN` (Planned)
""",
      allowableValues = {"PLN", "EST", "ACT"},
      example = "EST")
  private String eventClassifierCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Time in the timestamp.",
      format = "date-time",
      example = "2025-01-14T09:21:00+01:00")
  private String eventDateTime;

  @Schema(
      description =
"""
Reason code for the delay. See SMDG [Code list DELAY](https://smdg.org/documents/smdg-code-lists/delay-reason-and-port-call-activity/) for a list of valid codes to be used for this attribute.

**Deprecated:** Use `delayReasonCodes` instead. If `delayReasonCodes` is used - then values in this property are ignored.
""",
      deprecated = true,
      maxLength = 3,
      example = "WEA")
  private String delayReasonCode;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
Possibility to add multiple delay reason codes to a `Timestamp`.

**Note:** This property takes precedence over `delayReasonCode` (if both are present - only the ones provided in this property are used).
"""),
      schema =
          @Schema(
              description =
"""
Reason code for the delay. See SMDG [Code list DELAY](https://smdg.org/documents/smdg-code-lists/delay-reason-and-port-call-activity/) for a list of valid codes to be used for this attribute.
""",
              maxLength = 3,
              example = "WEA"))
  private List<String> delayReasonCodes;

  @Schema(
      description =
          "Free text field to provide information as to why the `TransportEvent` was sent.",
      maxLength = 250,
      example = "Bad weather")
  private String changeRemark;
}
