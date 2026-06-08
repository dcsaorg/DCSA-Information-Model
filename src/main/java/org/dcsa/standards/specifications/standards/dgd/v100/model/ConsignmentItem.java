package org.dcsa.standards.specifications.standards.dgd.v100.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
A plain language description that is precise enough for Customs services to be able to identify the goods. General terms (i.e. 'consolidated', 'general cargo' 'parts' or 'freight of all kinds') or not sufficiently precise description cannot be accepted.
"""),
      schema =
          @Schema(
              description =
"""
A line describing the cargo
"""))
  private List<String> descriptionOfGoods;

  @Schema(description = "A list of `References`")
  private List<ReferenceConsignmentItem> references;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
"""
A list of the `ShippingMarks` applicable to this `consignmentItem`
"""),
      schema =
          @Schema(
              description =
"""
The identifying details of a package or the actual markings that appear on the package(s). This information is provided by the customer.
"""))
  private List<String> shippingMarks;
}
