package org.dcsa.standards.specifications.standards.ovs.v302.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "A timestamp for a port.")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Timestamp
    extends org.dcsa.standards.specifications.standards.ovs.v301.model.Timestamp {

  @Schema(
      description =
"""
The code to identify the specific type of facility. The following values are supported:
- `PBPL` (Pilot Boarding Place)
- `BRTH` (Berth)

If no value is provided, `BRTH` is the default value.
""",
      maxLength = 4,
      defaultValue = "BRTH",
      example = "BRTH")
  private String facilityTypeCode;
}
