package org.dcsa.standards.specifications.standards.tnt.v300.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dcsa.standards.specifications.standards.tnt.v300.types.IotEventCode;

@Schema(description = "IoT-specific details")
@Data
public class IotDetails {

  @Schema() private IotEventCode iotEventCode;
}
