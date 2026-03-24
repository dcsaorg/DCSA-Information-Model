package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.tnt.v300.types.IotEquipmentTechnology;
import org.dcsa.standards.specifications.standards.tnt.v300.types.PowerStatus;

@Schema(description = "IoT-specific details")
@Data
public class IotDetails {

  @Schema() private IotEquipmentTechnology iotEquipmentTechnology;

  @Schema protected PowerStatus powerStatus;

  @Schema(maxLength = 100, description = "Brand name of the IoT device")
  private String deviceVendor;

  @Schema(maxLength = 100, description = "Model of the IoT device")
  private String deviceModel;
}
