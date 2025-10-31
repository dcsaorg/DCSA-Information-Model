package org.dcsa.standards.specifications.standards.portcall.v200.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
    description =
"""
Forecasted units of containers to move for loading and discharging, empty or laden,
covering various types such as Dry, reefer, Out-of-Gauge (OOG), and units of containers to restow at a terminal.
These can be detailed at varying levels of granularity.

The moves forecast of the VSA partner carriers on the vessel can be specified using:
- one optional `MovesForecast` object (with a specified `carrierCode`) for each VSA partner carrier on the vessel,
representing the moves forecast for that specific carrier;
- one optional `MovesForecast` object (without a `carrierCode`) representing the aggregated moves forecast
of all the VSA partner carriers on the vessel for which a dedicated moves forecast was not provided.
""")
public class MovesForecast {

  @Schema(
      maxLength = 4,
      example = "MAEU",
      description =
"""
SMDG or SCAC code of the carrier for which the moves forecast is provided.

If left unspecified, this is the aggregated moves forecast is for all the VSA partner carriers on the vessel
for which a dedicated moves forecast was not provided.
""")
  private String carrierCode;

  @Schema(
      maxLength = 5,
      example = "SMDG",
      description =
"""
The code list provider defining the `carrierCode`:
- `SMDG` (Ship Message Design Group)
- `NMFTA` (National Motor Freight Traffic Association)
""")
  private String carrierCodeListProvider;

  @Schema(description = "The number of container to restow.")
  private ContainerCountBySize restowUnits;

  @Schema(description = "The number of container to load.")
  private ContainerCountByTypeAndSize loadUnits;

  @Schema(description = "The number of container to discharge.")
  private ContainerCountByTypeAndSize dischargeUnits;
}
