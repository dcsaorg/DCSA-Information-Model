package org.dcsa.standards.specifications.standards.cs.v102.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "The Location specifying where the place of arrival is located.")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaceOfArrival
    extends org.dcsa.standards.specifications.standards.cs.v101.model.PlaceOfArrival {

  @Schema(
      description =
"""
The unique reference for the arrival transport call. It's the vessel operator's responsibility to provide the Transport Call Reference, other parties are obliged to pick it up and use it. It can take the form of Port Call References as defined in OVS Definitions Document, or alternatively a reference as defined by the vessel operator.

**Note:** This property takes precedence over the `transportCallReference` in the Vessel-Transport or Barge-Transport objects. If both this property and a `transportCallReference` in the Vessel-Transport or Barge-Transport objects are provided- the `transportCallReference` in those objects must be ignored.
""",
      maxLength = 100,
      example = "SR11111X-9321483-2107W-NLAMS-ACT-1-1")
  private String transportCallReference;
}
