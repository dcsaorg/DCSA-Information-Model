package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;
import org.dcsa.standards.specifications.standards.dt.v100.types.ShippingMark;

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

  @Schema(
      description =
"""
Indicates whether the container is shipper owned (SOC).
""",
      example = "true")
  private Boolean isShipperOwned;

  @Schema(
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.
According to [ISO 6346](https://www.iso.org/standard/83558.html), a container identification code consists of a \
4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with [ISO 6346](https://www.iso.org/standard/83558.html), it is suggested to follow \
[Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations) from SMDG.
""",
      maxLength = 11,
      pattern = "^\\S(?:.*\\S)?$",
      example = "APZU4812090")
  private String equipmentReference;

  @Schema(
      description =
"""
Used for storing cargo in/on during transport. The equipment size/type is defined by the ISO 6346 code.
""")
  private RequiredEquipment equipment;
}
