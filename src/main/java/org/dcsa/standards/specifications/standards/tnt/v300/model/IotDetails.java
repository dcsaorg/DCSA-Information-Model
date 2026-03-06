package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.tnt.v300.types.IotEquipmentTechnologyCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.IotEventCode;
import org.dcsa.standards.specifications.standards.tnt.v300.types.PowerStatusCode;

@Schema(description = "IoT-specific details")
@Data
public class IotDetails {

  @Schema() private IotEventCode iotEventCode;

  @Schema() private IotEquipmentTechnologyCode iotEquipmentTechnologyCode;

  @Schema protected PowerStatusCode powerStatus;

  @Schema(maxLength = 100, description = "Brand name of the IoT device")
  private String deviceVendor;

  @Schema(maxLength = 100, description = "Model name of the IoT device")
  private String deviceModel;
}
