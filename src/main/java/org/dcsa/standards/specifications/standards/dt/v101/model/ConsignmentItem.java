package org.dcsa.standards.specifications.standards.dt.v101.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v101.types.DescriptionOfGoodsLine;
import org.dcsa.standards.specifications.standards.dt.v101.types.ShippingMark;

@Schema(description = ConsignmentItem.CLASS_SCHEMA_DESCRIPTION)
@Data
public class ConsignmentItem {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Defines a list of `CargoItems` belonging together and the associated `Booking`. A `ConsignmentItem` can be split across multiple containers by referencing multiple `CargoItems`.";

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The associated booking number provided by the carrier for this `Consignment Item`.", example = "ABC709951", maxLength = 35, pattern = "^\\S(?:.*\\S)?$")
  protected String carrierBookingReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 150)
  protected List<DescriptionOfGoodsLine> descriptionOfGoods;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 50)
  protected List<ShippingMark> shippingMarks;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of all `cargoItems`")
  @ArraySchema(minItems = 1)
  protected List<CargoItem> cargoItems;

  @Schema(description = "Export license for the consignment item.")
  protected ExportLicense exportLicense;

  @Schema(description = "Import license for the consignment item.")
  protected ImportLicense importLicense;

  @Schema(description = "A list of `References`")
  protected List<ReferenceConsignmentItem> references;

  @Schema(description = "A list of `Customs references`")
  protected List<CustomsReference> customsReferences;
}
