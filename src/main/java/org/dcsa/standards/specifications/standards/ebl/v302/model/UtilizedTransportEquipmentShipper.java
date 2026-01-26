package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;
import org.dcsa.standards.specifications.standards.dt.v100.model.Seal;

@Schema(
    description = "Specifies the container (`Equipment`), `Seals` and `References`",
    title = "Utilized Transport Equipment (Shipper)")
@Data
public class UtilizedTransportEquipmentShipper {

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `UtilizedTransportEquipment`

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(
      maxItems = 50,
      schema =
          @Schema(
              description =
                  "The identifying details of a package or the actual markings that appear on the package(s). This information is provided by the customer.",
              example = "Made in China",
              maxLength = 35))
  private List<String> shippingMarks;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of `Seals`")
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

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;

  @Schema(
      description = "Indicates whether the container is shipper owned (SOC).",
      example = "false")
  private Boolean isShipperOwned;

  @Schema
  private Equipment equipment;

  @Schema(
      description =
"""
The unique identifier for the equipment, which should follow the BIC ISO Container Identification Number where possible.
According to [ISO 6346](https://www.iso.org/standard/83558.html), a container identification code consists of a 4-letter prefix and a 7-digit number (composed of a 3-letter owner code, a category identifier, a serial number, and a check-digit).

If a container does not comply with [ISO 6346](https://www.iso.org/standard/83558.html), it is suggested to follow [Recommendation #2: Containers with non-ISO identification](https://smdg.org/documents/smdg-recommendations) from SMDG.
""",
      example = "APZU4812090",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 11)
  private String equipmentReference;
}
