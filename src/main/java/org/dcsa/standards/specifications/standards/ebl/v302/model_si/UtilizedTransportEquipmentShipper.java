package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;
import org.dcsa.standards.specifications.standards.dt.v100.types.ShippingMark;
import org.dcsa.standards.specifications.standards.ebl.v302.model.CustomsReference;

@Schema(
    description =
"""
Specifies the container (`Equipment`), `Seals` and `References`
""",
    title = "Utilized Transport Equipment (Shipper)",
    requiredProperties = {"seals"})
@Data
public class UtilizedTransportEquipmentShipper {

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `UtilizedTransportEquipment`

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 50)
  private List<ShippingMark> shippingMarks;

  @Schema(
      description =
"""
A list of `Seals`
""")
  @ArraySchema(minItems = 1)
  private List<Seal> seals;

  @Schema(
      description =
"""
Indication if the container is `EMPTY` or `LADEN`.

- `EMPTY` (Empty)
- `LADEN` (Laden)
""",
      example = "LADEN",
      allowableValues = {"LADEN", "EMPTY"})
  private String emptyIndicatorCode;

  @Schema(
      description =
"""
A list of `References`
""")
  private List<Reference> references;

  @Schema(
      description =
"""
A list of `Customs references`
""")
  private List<CustomsReference> customsReferences;
}
