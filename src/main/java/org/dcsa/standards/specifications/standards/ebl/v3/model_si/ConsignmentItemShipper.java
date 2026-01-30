package org.dcsa.standards.specifications.standards.ebl.v3.model_si;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.ReferenceConsignmentItem;
import org.dcsa.standards.specifications.standards.dt.v100.types.DescriptionOfGoodsLine;
import org.dcsa.standards.specifications.standards.dt.v100.types.ShippingMark;
import org.dcsa.standards.specifications.standards.ebl.v3.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.ebl.v3.types.HSCode;

@Schema(
    description =
        "Defines a list of `CargoItems` belonging together and the associated `Booking`. A `ConsignmentItem` can be split across multiple containers (`UtilizedTransportEquipment`) by referencing multiple `CargoItems`",
    title = "Consignment Item (Shipper)")
@Data
public class ConsignmentItemShipper {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "The associated booking number provided by the carrier for this `Consignment Item`.",
      example = "ABC709951",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 35)
  private String carrierBookingReference;

  @Schema(
      description =
          "A unique reference to the commodity object assigned by the carrier in the booking confirmation. The reference must be provided by the shipper as part of the `Shipping Instructions` for the carrier to link this consignment item to the commodity. A commodity reference is only unique in the context of a booking.",
      example = "COM-001",
      pattern = "^\\S(?:.*\\S)?$",
      maxLength = 100)
  private String commoditySubReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
"""
A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 150)
  private List<DescriptionOfGoodsLine> descriptionOfGoods;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      name = "HSCodes",
      description = "A list of `HS Codes` that apply to this `consignmentItem`")
  @ArraySchema(minItems = 1)
  private List<HSCode> hsCodes;

  @Schema(description = "A list of `National Commodity Codes` that apply to this `commodity`")
  private List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`

**Condition:** The order of the items in this array **MUST** be preserved as by the provider of the API.
""")
  @ArraySchema(maxItems = 50)
  private List<ShippingMark> shippingMarks;

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of all `cargoItems`")
  @ArraySchema(minItems = 1)
  private List<CargoItemShipper> cargoItems;

  @Schema private ExportLicenseShipper exportLicense;

  @Schema private ImportLicenseShipper importLicense;

  @Schema(description = "A list of `References`")
  private List<ReferenceConsignmentItem> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;
}
