package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Schema(description = "A consignment item within a DG declaration.")
@Data
public class ConsignmentItem {

  @Schema(description = "A list of all `cargoItems`")
  private List<CargoItem> cargoItems;

  @Schema(
      description =
          "The associated booking number provided by the carrier for this `Consignment Item`.",
      example = "ABC709951",
      maxLength = 35)
  private String carrierBookingReference;

  @Schema(
      description =
"""
A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.
""")
  private List<String> descriptionOfGoods;

  @Schema(description = "A list of `References`")
  private List<ReferenceConsignmentItem> references;

  @Schema(
      description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`
""")
  private List<String> shippingMarks;
}
