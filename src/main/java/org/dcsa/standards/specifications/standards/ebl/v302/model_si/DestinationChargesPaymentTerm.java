package org.dcsa.standards.specifications.standards.ebl.v302.model_si;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
    description =
        "An indicator of whether destination charges are prepaid (`PRE`) or collect (`COL`). When prepaid, the charges are the responsibility of the shipper or the Invoice payer on behalf of the shipper (if provided). When collect, the charges are the responsibility of the consignee or the Invoice payer on behalf of the consignee (if provided).",
    title = "Destination Charges Payment Term")
@Data
public class DestinationChargesPaymentTerm {

  @Schema(
      description =
"""
An indicator of whether the costs for inland transportation to the port, when applicable, are prepaid (`PRE`) or collect (`COL`).

- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  private String haulageChargesPaymentTermCode;

  @Schema(
      description =
"""
An indicator of whether the destination port charges are prepaid (`PRE`) or collect (`COL`).

- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  private String portChargesPaymentTermCode;

  @Schema(
      description =
"""
An indicator of whether destination charges (excluding port and haulage) are prepaid (`PRE`) or collect (`COL`).

- `PRE` (Prepaid)
- `COL` (Collect)
""",
      example = "PRE",
      allowableValues = {"PRE", "COL"})
  private String otherChargesPaymentTermCode;
}
