package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Measurement from one temperature probe of a reefer container")
@Data
public class TemperatureProbeMeasurement {

  @Schema(
      description = "Temperature measured by the probe, expressed in `temperatureUnit`",
      example = "-12.3",
      format = "float")
  protected Double measuredTemperatureValue;

  @Schema(name = "USDAProbeNumber", example = "2", description = "USDA reefer probe number (1-3)")
  private Integer usdaProbeNumber;

  @Schema(
      name = "COAProbeUDMKey",
      example = "P20",
      description =
"""
COA UDM reefer probe key:
- `P19` (USDA probe 1)
- `P20` (USDA probe 2)
- `P21` (USDA probe 3)
- `P22` (Cargo probe)
""")
  private String coaProbeUdmKey;

  @Schema(
      example = "Pulp probe B",
      description = "Label identifying the probe other than through a USDA number or COA UDM key")
  private String probeLabel;
}
