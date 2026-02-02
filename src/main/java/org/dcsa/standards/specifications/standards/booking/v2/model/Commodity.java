package org.dcsa.standards.specifications.standards.booking.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import org.dcsa.standards.specifications.standards.booking.v2.types.HSCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.CustomsReference;
import org.dcsa.standards.specifications.standards.dt.v100.model.ExportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.ImportLicense;
import org.dcsa.standards.specifications.standards.dt.v100.model.NationalCommodityCode;
import org.dcsa.standards.specifications.standards.dt.v100.model.OuterPackaging;
import org.dcsa.standards.specifications.standards.dt.v100.model.Reference;

@Schema(description = Commodity.CLASS_SCHEMA_DESCRIPTION)
@Data
public class Commodity {

  public static final String CLASS_SCHEMA_DESCRIPTION = "Type of goods, defined by its commodity type";

  @Schema(
      description =
"""
A unique reference for this commodity object assigned by the carrier in the booking confirmation. The reference must be provided by the shipper as part of the `Shipping Instructions` for the carrier to link the consignment item to this commodity. A commodity reference is only unique in the context of a booking.

**Condition:** Mandatory to provide for `CONFIRMED` bookings
""",
      maxLength = 100,
      pattern = "^\\S(?:.*\\S)?$")
  private String commoditySubReference;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "High-level description of goods to be shipped which allow the carrier to confirm acceptance and commercial terms. To be replaced by \"description of goods\" upon submission of `Shipping Instructions`",
      example = "Mobile phones",
      maxLength = 550,
      pattern = "^\\S(?:.*\\S)?$")
  private String commodityType;

  @Schema(name = "HSCodes", description = "A list of `HS Codes` that apply to this `commodity`")
  private List<HSCode> hsCodes;

  @Schema(description = "A list of `National Commodity Codes` that apply to this `commodity`")
  private List<NationalCommodityCode> nationalCommodityCodes;

  @Schema(description = "The estimated grand total gross weight of the cargo, including packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.\n\n**Condition:** Mandatory if not provided on `Requested Equipment` level.")
  private CargoGrossWeight cargoGrossWeight;

  @Schema(description = "The estimated grand total volume of the cargo.")
  private CargoGrossVolume cargoGrossVolume;

  @Schema(description = "The estimated grand total net weight of the cargo, excluding packaging items being carried, which can be expressed in imperial or metric terms, as provided by the shipper.")
  private CargoNetWeight cargoNetWeight;

  @Schema(description = "The estimated net total volume of the cargo.")
  private CargoNetVolume cargoNetVolume;

  @Schema(description = "Export license information.")
  private ExportLicense exportLicense;

  @Schema(description = "Import license information.")
  private ImportLicense importLicense;

  @Schema(description = "Object for outer packaging/overpack specification. Examples of overpacks are a number of packages stacked on to a pallet and secured by strapping or placed in a protective outer packaging such as a box or crate to form one unit for the convenience of handling and stowage during transport.\n\n**Condition:** Mandatory for DG (Dangerous Goods) cargo.")
  private OuterPackaging outerPackaging;

  @Schema(description = "A list of `References`")
  private List<Reference> references;

  @Schema(description = "A list of `Customs references`")
  private List<CustomsReference> customsReferences;
}
