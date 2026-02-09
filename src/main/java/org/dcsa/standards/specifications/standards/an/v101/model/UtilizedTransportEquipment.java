package org.dcsa.standards.specifications.standards.an.v101.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.dcsa.standards.specifications.generator.ClearSchemaConstraints;
import org.dcsa.standards.specifications.standards.dt.v101.types.ShippingMark;

@Schema(
    description =
"""
Specifies the container (equipment) information, possible ActiveReeferSettings, seals, references, and other related details.
""")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@ClearSchemaConstraints
public class UtilizedTransportEquipment
    extends org.dcsa.standards.specifications.standards.dt.v101.model.UtilizedTransportEquipment {

  @Schema(
      description =
"""
If the equipment is a Reefer Container then setting this attribute will indicate that the container should be treated as a `DRY` container.
""",
      example = "false")
  protected Boolean isNonOperatingReefer;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `UtilizedTransportEquipment`
""")
  @ArraySchema(maxItems = 50)
  protected List<ShippingMark> shippingMarks;

  @Schema() private PickupInformation pickupInformation;

  @Schema() private ReturnInformation returnInformation;

  @Schema(description = "A list of `Charges`")
  private List<Charge> charges;
}
