package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.core.v103.model.Seal;
import org.dcsa.standards.specifications.standards.dt.v101.types.ShippingMark;

@Schema(description = UtilizedTransportEquipment.CLASS_SCHEMA_DESCRIPTION)
@Data
public class UtilizedTransportEquipment {

  public static final String CLASS_SCHEMA_DESCRIPTION =
      "Specifies the container (`equipment`), total `weight`, `volume`, possible `ActiveReeferSettings`, `seals` and `references`.";

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "The container or transport equipment used.")
  protected Equipment equipment;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Indicates whether the container is shipper owned (SOC).",
      example = "true")
  protected Boolean isShipperOwned;

  @Schema(
      description =
"""
If the equipment is a Reefer Container then setting this attribute will indicate that the container should be treated as a `DRY` container.

**Condition:** Only applicable if `ISOEquipmentCode` shows a Reefer type.
""",
      example = "false")
  protected Boolean isNonOperatingReefer;

  @Schema(description = "Active reefer settings applied to this equipment.")
  protected ActiveReeferSettings activeReeferSettings;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `UtilizedTransportEquipment`

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 50)
  protected List<ShippingMark> shippingMarks;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of `Seals`")
  @ArraySchema(minItems = 1)
  protected List<Seal> seals;

  @Schema(description = "A list of `References`")
  protected List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  protected List<CustomsReference> customsReferences;
}
