package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = ConfirmedEquipment.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ConfirmedEquipment {

  public static final String CLASS_SCHEMA_DESCRIPTION = "The confirmed equipments for the booking";

  @Schema(
      name = "ISOEquipmentCode",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Unique code for the different equipment size and type used to transport commodities. The code can refer to one of ISO size type (e.g. 22G1) or ISO type group (e.g. 22GP) following the [ISO 6346](https://www.iso.org/standard/83558.html) standard.",
      example = "22G1",
      maxLength = 4,
      pattern = "^\\S(?:.*\\S)?$")
  private String isoEquipmentCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Number of confirmed equipment units",
      example = "3",
      minimum = "1",
      format = "int32")
  private Integer units;

  @Schema(
      description = "A list of date and time and locations of the customer facility where the container(s) will be loaded. If multiple Container positioning locations (`CPO`) are provided (multi-stop), the first location is where the empty container will be stuffed first. The order in which the `CPO` locations should be visited is implicitly defined by the shipper based on the date and time provided per location (all times are estimated).\n\n**Condition:** Only applicable to carrier haulage service at origin (`Receipt type at origin = 'SD'`).")
  private List<ContainerPositioningEstimated> containerPositionings;

  @Schema(description = "The date and time and location for the empty container pick-up.\n\n**Condition:** Only applicable to merchant haulage service at origin (`Receipt type at origin = 'CY'`).")
  private EmptyContainerPickup emptyContainerPickup;
}
