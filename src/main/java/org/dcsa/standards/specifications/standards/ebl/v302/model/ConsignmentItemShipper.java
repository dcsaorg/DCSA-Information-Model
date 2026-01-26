package org.dcsa.standards.specifications.standards.ebl.v302.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.dt.v100.model.ReferenceConsignmentItem;

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
  @ArraySchema(
      maxItems = 150,
      schema =
          @Schema(
              description = "A line describing the cargo",
              example = "blue shoes size 47",
              pattern = "^\\S(?:.*\\S)?$",
              maxLength = 35))
  private List<String> descriptionOfGoods;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "A list of `HS Codes` that apply to this `consignmentItem`")
  @ArraySchema(
      minItems = 1,
      schema =
          @Schema(
              description =
"""
Used by customs to classify the product being shipped. The type of HS code depends on country and customs requirements. The code must be at least 6 and at most 10 digits.

More information can be found here: [HS Nomenclature](https://www.wcoomd.org/en/topics/nomenclature/instrument-and-tools).
""",
              example = "851713",
              pattern = "^\\d{6,10}$",
              minLength = 6,
              maxLength = 10))
  private List<String> HSCodes;

  @Schema(description = "A list of `National Commodity Codes` that apply to this `commodity`")
  private List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`

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

  @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of all `cargoItems`")
  @ArraySchema(minItems = 1)
  private List<CargoItemShipper> cargoItems;

  @Schema
  private ExportLicenseShipper exportLicense;

  @Schema
  private ImportLicenseShipper importLicense;

  @Schema(description = "A list of `References`")
  private List<ReferenceConsignmentItem> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;
}
