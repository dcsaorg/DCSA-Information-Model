package org.dcsa.standards.specifications.standards.cs.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Cut Off Times.")
@Data
public class CutOffTime {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
Code for the cut-off time. Possible values are:
  - `DCO` (Documentation cut-off)
  - `VCO` (VGM cut-off)
  - `FCO` (FCL delivery cut-off)
  - `LCO` (LCL delivery cut-off) **Condition:** only when the `Receipt Type at Origin` is `CFS`
  - `PCO` (Port cut-off)
  - `ECP` (Empty container pick-up date and time)
  - `EFC` (Earliest full-container delivery date)
  - `RCO` (Reefer cut-off)
  - `DGC` (Dangerous Goods cut-off)
  - `OBC` (OOG/BB cut-off)
  - `TCO` (Transhipment cut-off)
  - `STA` (Standard booking acceptance)
  - `SPA` (Special booking acceptance)
  - `CUA` (Customs Acceptance)
  - `AFC` (Advanced filling cut-off)

More details can be found on [GitHub](https://github.com/dcsaorg/DCSA-OpenAPI/blob/master/reference-data/CutOffDateTimeCodeList-CS_v1.0.0.csv)
""",
      maxLength = 3,
      example = "DCO")
  private String cutOffDateTimeCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "Estimated cut-off time expressed in local time with offset following [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format.",
      format = "date-time",
      example = "2019-11-12T07:41:00-08:30")
  private String cutOffDateTime;
}
