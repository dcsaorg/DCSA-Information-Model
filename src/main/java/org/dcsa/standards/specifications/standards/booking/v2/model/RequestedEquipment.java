package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.EquipmentReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.ActiveReeferSettings;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;

@Schema(description = RequestedEquipment.CLASS_SCHEMA_DESCRIPTION)
@Data
public class RequestedEquipment {

  public static final String CLASS_SCHEMA_DESCRIPTION = "If needed - it is **only** possible to specify a single Reefer setting. If multiple settings are required for the same `equipmentSizeType` then multiple `requestedEquipment` should be specified (one for each Reefer setting).";

  @Schema(
      name = "ISOEquipmentCode",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Unique code for the different equipment size and type used to transport commodities. The code can refer to one of ISO size type (e.g. 22G1) or ISO type group (e.g. 22GP) following the [ISO 6346](https://www.iso.org/standard/83558.html) standard.",
      example = "22RT",
      maxLength = 4,
      pattern = "^\\S(?:.*\\S)?$")
  private String isoEquipmentCode;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Number of requested equipment units.",
      example = "3",
      minimum = "1",
      format = "int32")
  private Integer units;

  @Schema(
      description = "A list of date and time and locations of the customer facility where the container(s) will be loaded. If multiple Container positioning locations (`CPO`) are provided (multi-stop), the first location is where the empty container will be stuffed first. The order in which the `CPO` locations should be visited is implicitly defined by the shipper based on the date and time provided per location.\n\n**Condition:** Only applicable to carrier haulage service at origin (`Receipt type at origin = 'SD'`).")
  private List<ContainerPositioning> containerPositionings;

  @Schema(description = "The date and time and location for the empty container pick-up.\n\n**Condition:** Only applicable to merchant haulage service at origin (`Receipt type at origin = 'CY'`).")
  private EmptyContainerPickup emptyContainerPickup;

  @Schema(description = "A list of equipments to be used by the shipper if known at the time of booking")
  private List<EquipmentReference> equipmentReferences;

  @Schema(description = "The weight of an empty container (gross container weight).\n\n**Condition:** In case of Shipper Owned Containers (`SOC`) this is a required property")
  private TareWeight tareWeight;

  @Schema(description = "The gross weight of the cargo and any packaging.")
  private CargoGrossWeightReq cargoGrossWeight;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates whether the container is shipper owned (`SOC`).",
      example = "true")
  private Boolean isShipperOwned;

  @Schema(
      description = "If the equipment is a Reefer Container then setting this attribute will indicate that the container should be treated as a `DRY` container.\n\n**Condition:** Only applicable if `ISOEquipmentCode` shows a Reefer type.",
      example = "false")
  private Boolean isNonOperatingReefer;

  @Schema(description = "Active reefer settings for temperature-controlled containers.")
  private ActiveReeferSettings activeReeferSettings;

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;

  @Schema(description = "A list of `Commodities`")
  private List<Commodity> commodities;
}
